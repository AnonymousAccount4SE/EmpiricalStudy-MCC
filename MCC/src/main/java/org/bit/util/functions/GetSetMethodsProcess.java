/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/10
 */

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class GetSetMethodsProcess {
    public static void main(String [] args) throws FileNotFoundException {
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\";
//        String basePath = "E:\\Workspace\\ICSE2020TrainingData\\";
//        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\consistent\\";
//        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\inconsistent\\";
//        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\";
        String basePath = "E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\inconsistent\\";
//        getPredictSucOrFailNumber(basePath);

//        getNotGetSetConsistentSuccessRSOnly(basePath);

        getICSE2020getset(basePath);
//        getICSE2020GetSetCorrectFail(basePath);

//        getSet_originalAndPredicted(basePath);
//        getSet_ICSE2020failOriginalAndPredicted(basePath);

//        getFirstTokenAndNumber(basePath);

    }

    private static void getSet_ICSE2020failOriginalAndPredicted(String basePath) throws FileNotFoundException {
        String failPath = basePath + "ConsistentFailGetSetMethodsAnalysisReport.txt";
        ArrayList<String> failList = ParserMethodNameMain.getLines(failPath);
        int getCnt=0,setCnt=0;
        int originalGetResultSet = 0,originalSetResultGet = 0;
        int others = 0;
        for(String s:failList){
            // result-firstToken
            String firstTokenOfResult = s.split(":")[0].split(" ")[0];
            // original-firstToken
            String firstTokenOfOriginal = s.split(":")[1].split(" ")[0];
            if(firstTokenOfOriginal.equals("get")&&firstTokenOfResult.equals("get")){
                getCnt++;
            }
            else if(firstTokenOfOriginal.equals("set")&&firstTokenOfResult.equals("set")){
                setCnt++;
            }
            else{
                if(firstTokenOfOriginal.equals("get")&&firstTokenOfResult.equals("set")){
                    originalGetResultSet++;
                }
                else if(firstTokenOfOriginal.equals("set")&&firstTokenOfResult.equals("get")){
                    originalSetResultGet++;
                }
                else{
                    others++;
                }
                System.out.println(s);
            }

        }
        int sum = setCnt+getCnt;
        double ratio = sum*1.0/failList.size();
//        System.out.println(failList.size());
//        System.out.println(getCnt);
//        System.out.println(setCnt);
//        System.out.println(String.valueOf(sum));
//        System.out.println(ratio);
        System.out.println("originalGetResultSet:"+originalGetResultSet);
        System.out.println("originalSetResultGet:"+originalSetResultGet);
        System.out.println("others:"+others);
    }

    private static void getFirstTokenAndNumber(String basePath) throws FileNotFoundException {
        String resultFile = basePath + "allSuccessAnalysis_Top1.txt";
        ArrayList<String> resultTokensLines = ParserMethodNameMain.getLines(resultFile);
        HashMap<String,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        System.out.println(resultTokensLines.size());
        for(int i=0;i< resultTokensLines.size();i++){
            String line = resultTokensLines.get(i);
            String name = Utils.getFirstToken(line);
                if(methodNameTokensNumberAndNumber.get(name)==null){
                    methodNameTokensNumberAndNumber.put(name,1);
                }
                else{
                    int num = methodNameTokensNumberAndNumber.get(name);
                    methodNameTokensNumberAndNumber.put(name,num+1);
                }
        }
//        System.out.println(methodNameTokensNumberAndNumber.keySet().size());
        List<Map.Entry<String,Integer>>list =  Utils.sortMap(methodNameTokensNumberAndNumber);
        for(Map.Entry<String,Integer> s:list){
            System.out.println(s.getKey()+":"+s.getValue());
        }

//        System.out.println(list);
    }

    private static void getNotGetSetConsistentSuccessRSOnly(String basePath) throws FileNotFoundException {
        String file = basePath + "consistentSuccessAnalysis_Top1.txt";
        String methodTokens = basePath + "filteredSelectedMethodTokens_1.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(file);
        ArrayList<String> methodTokensList = ParserMethodNameMain.getLines(methodTokens);
        ArrayList<Integer> notGetSetList = new ArrayList<>();
        ArrayList<String> resultList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(String line:lines){
            int indice = Utils.getIndice(line);
            String firstToken = Utils.getFirstToken(line);
            if(firstToken.equals("get")||firstToken.equals("set")){

            }
            else{
                notGetSetList.add(indice);
                System.out.println(line);
                resultList.add(line);
                sb.append(line+"\n");
            }

        }
//        FileHelper.outputToFile(basePath + "consistentSuccessNotGetSet.txt",sb,false);
        int rsOnlyCnt =0;
        int tokenLT10Cnt=0;
        System.out.println(notGetSetList.size());
        for(int i=0;i<notGetSetList.size();i++){
            String token = methodTokensList.get(notGetSetList.get(i)-3159);
//            System.out.println(token);
            List<String> tokenList = Arrays.asList(token.split(" "));
//            if(tokenList.get(0).equals("ReturnStatement")){
//                rsOnlyCnt ++;
//            }
//            else{
                if(tokenList.size()<=20){
                    tokenLT10Cnt ++;
                }
                else{

//                    System.out.println(token);
                }
//            }
        }
        System.out.println("rsOnlyCnt:"+rsOnlyCnt);
        System.out.println("tokenLT10Cnt:"+tokenLT10Cnt);
    }

    private static void getSet_originalAndPredicted(String basePath) throws FileNotFoundException {
        String resultTokens = basePath + "result.txt";
        String nameTokens = basePath + "parsedMethodNameTokens.txt";
        ArrayList<String> nameTokensLines = ParserMethodNameMain.getLines(nameTokens);
        ArrayList<String> resultTokensLines = ParserMethodNameMain.getLines(resultTokens);
        ArrayList<String> getSetListOfResult = new ArrayList<>();
        ArrayList<String> getSetListOfOriginal = new ArrayList<>();
        int same=0,notSame=0,sameNot=0;
        for(int i=0;i<nameTokensLines.size();i++){
            // result-firstToken
            String firstTokenOfResult = resultTokensLines.get(i).split(" ")[0];
            // original-firstToken
            String firstTokenOfOriginal = nameTokensLines.get(i).split(" ")[0];
            if(firstTokenOfResult.equalsIgnoreCase("get")||firstTokenOfResult.equalsIgnoreCase("set")){
                getSetListOfResult.add(resultTokensLines.get(i));
            }
            if(firstTokenOfOriginal.equalsIgnoreCase("get")||firstTokenOfOriginal.equalsIgnoreCase("set")){
                getSetListOfOriginal.add(nameTokensLines.get(i));
            }
            if((firstTokenOfOriginal.equalsIgnoreCase("get")||firstTokenOfOriginal.equalsIgnoreCase("set"))&&
                    (firstTokenOfResult.equalsIgnoreCase("get")||firstTokenOfResult.equalsIgnoreCase("set"))){
                same++;
            }
            if(!(firstTokenOfOriginal.equalsIgnoreCase("get")||firstTokenOfOriginal.equalsIgnoreCase("set"))&&
                    (firstTokenOfResult.equalsIgnoreCase("get")||firstTokenOfResult.equalsIgnoreCase("set"))){
                notSame++;
            }
            if((firstTokenOfOriginal.equalsIgnoreCase("get")||firstTokenOfOriginal.equalsIgnoreCase("set"))&&
                    !(firstTokenOfResult.equalsIgnoreCase("get")||firstTokenOfResult.equalsIgnoreCase("set"))){
                sameNot++;
            }
        }
        System.out.println("getSetListOfResult.size():"+getSetListOfResult.size());
        System.out.println("getSetListOfOriginal.size():"+getSetListOfOriginal.size());
        System.out.println("same:"+same);
        System.out.println("notSame:"+notSame);
        System.out.println("SameNot:"+sameNot);
    }

    private static void getICSE2020GetSetCorrectFail(String basePath) throws FileNotFoundException {
        String nameTokens = basePath + "correct.txt";
//        String nameTokens = basePath + "false.txt";
//        String nameTokens = basePath + "MethodsOfLT3FalseNameTokens.txt";
//        String nameTokens = basePath + "result.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(nameTokens);
        ArrayList<String> getSetList = new ArrayList<>();
        for(String line:lines){
            // result-firstToken
            String firstToken = line.split(":")[0].split(" ")[0];
            // original-firstToken
//            String firstToken = line.split(":")[1].split(" ")[0];
            if(firstToken.equalsIgnoreCase("get")||firstToken.equalsIgnoreCase("set")){
                getSetList.add(line);
            }
        }
        System.out.println(lines.size());
        System.out.println(getSetList.size());
    }

    private static void getICSE2020getset(String basePath) throws FileNotFoundException {
//        String nameTokens = basePath + "parsedMethodNameTokens.txt";
//        String nameTokens = basePath + "result.txt";
        String nameTokens = basePath + "allResult.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(nameTokens);
        ArrayList<String> getSetList = new ArrayList<>();
        for(String line:lines){
            String firstToken = line.split(" ")[0];
            if(firstToken.equalsIgnoreCase("get")||firstToken.equalsIgnoreCase("set")){
                getSetList.add(line);
            }
        }
        System.out.println(lines.size());
        System.out.println(getSetList.size());
        System.out.println(getSetList.size()*1.0/ lines.size());
    }

    private static void getPredictSucOrFailNumber(String basePath) throws FileNotFoundException {
//        private static final DecimalFormat FORMAT = new DecimalFormat("0.000");
//        String resultFile = basePath + "real_more_Top40.txt";
        String resultFile = basePath + "Top1_PredictResult.txt";
//        String resultFile = basePath + "inconsistentFailAnalysis_Top1.txt";
//        String resultFile = basePath + "inconsistentSuccessAnalysis_Top1.txt";
//        String resultFile = basePath + "consistentFailAnalysis_Top1.txt";
        FileInputStream fis1 = new FileInputStream(resultFile);
        ArrayList<StringBuilder> successArrayList = new ArrayList<>();
        ArrayList<StringBuilder> failArrayList = new ArrayList<>();

        ArrayList<String> getSetArrayList = new ArrayList<>();
        ArrayList<String> getSet0ArrayList = new ArrayList<>();
        ArrayList<String> getSet1ArrayList = new ArrayList<>();

        ArrayList<String> inconsistentSuccessArrayList = new ArrayList<>();
        ArrayList<String> consistentSuccessArrayList = new ArrayList<>();
        ArrayList<String> inconsistentFailArrayList = new ArrayList<>();
        ArrayList<String> inconsistentGetSetSuccessArrayList = new ArrayList<>();
        ArrayList<String> consistentGetSetSuccessArrayList = new ArrayList<>();

        HashMap<String,Integer> successFirstTokenMap = new HashMap<>();
        HashMap<String,Integer> failFirstTokenMap = new HashMap<>();
        Scanner scanner1 = new Scanner(fis1);
        while(scanner1.hasNext()){
            String line = scanner1.nextLine();
            String firstToken = Utils.getFirstToken(line);
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);


            if(originalTag.equals(predictedTag)&&originalTag.equals("0")){
                inconsistentSuccessArrayList.add(line);
            }
            if(originalTag.equals(predictedTag)&&originalTag.equals("1")){
                consistentSuccessArrayList.add(line);
            }
            if(!originalTag.equals(predictedTag)&&originalTag.equals("0")){
                inconsistentFailArrayList.add(line);
            }


            if(firstToken.equals("get")||firstToken.equals("set")){
                getSetArrayList.add(line);
//                System.out.println(line);
                if(originalTag.equals("0")){
                    getSet0ArrayList.add(line);
                }
                else{
                    getSet1ArrayList.add(line);
                }


                // predict successfully
                if(originalTag.equals(predictedTag)){
                    if(originalTag.equals("0")){
                        inconsistentGetSetSuccessArrayList.add(line);
                    }
                    else{
                        consistentGetSetSuccessArrayList.add(line);
                    }
//                    System.out.println(originalTag + ":"+predictedTag);
                    if(successFirstTokenMap.containsKey(firstToken)){
                        int value = successFirstTokenMap.get(firstToken);
                        successFirstTokenMap.put(firstToken,value+1);
                    }
                    else{
                        successFirstTokenMap.put(firstToken,1);
                    }
                    successArrayList.add(new StringBuilder().append(line).append("\n"));
                }
                // predict failed
                else{
                    if(failFirstTokenMap.containsKey(firstToken)){
                        int value = failFirstTokenMap.get(firstToken);
                        failFirstTokenMap.put(firstToken,value+1);
                    }
                    else{
                        failFirstTokenMap.put(firstToken,1);
                    }
                    failArrayList.add(new StringBuilder().append(line).append("\n"));
                }
            }

        }


        System.out.println("total get&set method numbers:"+getSetArrayList.size());
        System.out.println("consistent get&set method numbers:"+getSet1ArrayList.size());
        System.out.println("inconsistent get&set method numbers:"+getSet0ArrayList.size());

        System.out.println("consistent predicted success:"+consistentSuccessArrayList.size());
        System.out.println("inconsistent predicted success:"+inconsistentSuccessArrayList.size());

        System.out.println("consistent get set predicted success:"+consistentGetSetSuccessArrayList.size());
        System.out.println("inconsistent get set predicted success:"+inconsistentGetSetSuccessArrayList.size());
//        System.out.println(failFirstTokenMap);
    }
}
