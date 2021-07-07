/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/23
 */

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.util.*;

public class OOV {
    public static void main(String [] args) throws FileNotFoundException {

//        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\";
//        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\";
//        String basePath2 = "E:\\Workspace\\ICSE2020TrainingData\\";
//        String basePath2 = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
//        IsFalseOOV_20(basePath1,basePath2);
//        IsCorrectOOV_20(basePath1,basePath2);


//        String basePath3 = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\";
//        String basePath4 = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
//        IsCorrectOOV_19(basePath3,basePath4);
//        IsFalseOOV_19(basePath3,basePath4);

        //get consistent samples oov number
//        getOOVNumber(basePath1,basePath2);
        //get consistent false oov number
//        HashSet<String> oovTokenSet = getOOVTokenSet(basePath1,basePath2);
//        getFalseOOVNumber(basePath1,oovTokenSet);


//        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\";
        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2020\\1_1\\";
//        String basePath1 = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TestData\\";
//        String basePath2 = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
//        testOriginalData(basePath1,basePath2);
        String basePath2 = "E:\\Workspace\\ICSE2020TrainingDataLT94Tokens\\";
//        String basePath2 = "E:\\Workspace\\ICSE2020TrainingDataAllTokens\\";
//        String basePath2 = "E:\\Workspace\\ICSE2020\\TrainingData\\";

//        OOV_20(basePath1,basePath2);

//        HashSet<String> oovTokenSet = getOOVTokenSet(basePath1,basePath2);
//        getFalseOOVNumber(basePath1,oovTokenSet);

        HashSet<String> voc = getVocabulary(basePath2);
        System.out.println(voc.size());
        getFalseOOVNumber1(basePath1,voc);

//        String basePath1 = "D:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\notReal\\PositiveItems\\TokensLT94\\";
//        String basePath1 = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TestData\\";
//        String basePath2 = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
//
//        OOV_19(basePath1,basePath2);

//        OOV_WhySucceed(basePath1,basePath2);

//        String basePath = "D:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\notReal\\1_1\\";
        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\";
//        nameAndContextTokenCooccur(basePath);
//        nameAndContextTokenCooccur_1(basePath2);

//        getOccurringNumber(basePath2);


//        getToStringFailInfo(basePath);
    }

    private static HashSet<String> getVocabulary(String basePath2) throws FileNotFoundException {
//        ArrayList<String> trainingNames = ParserMethodNameMain.getLines(basePath2 + "parsedMethodNameTokens.txt");
        ArrayList<String> trainingNames = ParserMethodNameMain.getLines(basePath2 + "parsedMethodContextTokens.txt");
//        ArrayList<String> trainingNames = ParserMethodNameMain.getLines(basePath2 + "TrainingData\\ParsedMethodContextTokens_1.txt");
        HashSet<String> set = new HashSet<>();
        for(String name:trainingNames){
            String [] splitArray = name.split(" ");
            for(String token:splitArray){
                set.add(token);
            }
        }
        return set;
    }

    private static void getToStringFailInfo(String basePath) throws FileNotFoundException {
        String failFile = basePath + "allGeneratedFalse.txt";
//        String failFile = basePath + "allGeneratedCorrect.txt";
        ArrayList<String> failList = ParserMethodNameMain.getLines(failFile);
        int cnt = 0, cnt1 = 0;
        for(String line:failList){
            String testName = line.split(":")[1];
            String predictedName = line.split(":")[0];
            if(testName.equals("to string")){
//                System.out.println(line);
                cnt1++;
                if(predictedName.contains("get ")){
                   cnt++;
                }
            }
        }
        System.out.println(cnt1);
        System.out.println(cnt);
        System.out.println(cnt*1.0/cnt1);
    }

    private static void getOccurringNumber(String basePath) throws FileNotFoundException {
//        String nameFile = basePath + "allMethodNameTokens.txt";
        String nameFile = basePath + "parsedMethodNameTokens.txt";
        ArrayList<String> nameList = ParserMethodNameMain.getLines(nameFile);
        int cnt = 0;
        for(String name:nameList){
            if(name.equals("remove")){
                cnt++;
            }
        }
        System.out.println(cnt);
    }


    private static void nameAndContextTokenCooccur_1(String basePath) throws FileNotFoundException {
//        String nameFile = basePath + "1_1_name.txt";
//        String contextFile = basePath + "1_1_context.txt";
//        String nameFile = basePath + "allMethodNameTokens.txt";
//        String contextFile = basePath + "allMethodContextTokens.txt";
//        String nameFile = basePath + "allGeneratedCorrect.txt";
//        String contextFile = basePath + "allGeneratedCorrectContext.txt";
        String nameFile = basePath + "parsedMethodNameTokens.txt";
        String contextFile = basePath + "parsedMethodContextTokens.txt";
        ArrayList<String> nameList = ParserMethodNameMain.getLines(nameFile);
        ArrayList<String> contextList = ParserMethodNameMain.getLines(contextFile);
        StringBuilder sb = new StringBuilder();
        int cnt = 0; int cnt1= 0; int cnt2 = 0;
        for(int i =0; i<nameList.size();i++){
            int mark = 0;
//            String name = nameList.get(i).split(":")[1];
            String name = nameList.get(i).split(":")[0];
//            if(name.equals("to string")){
//                cnt1++;
//            }
            if(name.contains("get")){
                cnt2++;
            }
            String context = contextList.get(i);
            List<String> nameTokens = Arrays.asList(name.split(" "));
            for(String nameToken:nameTokens){
//                if(context.contains("string")&&context.contains(" to ")){
////                    System.out.println(name+":"+context);
//
//                }
                if(context.contains("get")){
//                    System.out.println(name+":"+context);

                }
                else{
                    mark = 1;
                }
            }
            if(mark==0){
                sb.append(name+":"+context+"\n");
//                if(name.equals("to string")){
                if(name.contains("get")){
                    System.out.println(name+":"+context);
                    cnt++;
                }
            }
        }
//        System.out.println(cnt1);
        System.out.println(cnt);
//        System.out.println(cnt*1.0/cnt1);

        System.out.println(cnt2);
        System.out.println(cnt*1.0/cnt2);
//        FileHelper.outputToFile(basePath + "NotCoOccurMethodsAndContext.txt",sb,false);
    }

    private static void nameAndContextTokenCooccur(String basePath) throws FileNotFoundException {
//        String nameFile = basePath + "1_1_name.txt";
//        String contextFile = basePath + "1_1_context.txt";
        String nameFile = basePath + "allMethodNameTokens.txt";
        String contextFile = basePath + "allMethodContextTokens.txt";
        ArrayList<String> nameList = ParserMethodNameMain.getLines(nameFile);
        ArrayList<String> contextList = ParserMethodNameMain.getLines(contextFile);
        int cnt = 0;
        for(int i =0; i<nameList.size();i++){
            int mark = 0;
            String name = nameList.get(i);
            String context = contextList.get(i);
            List<String> nameTokens = Arrays.asList(name.split(" "));
            for(String nameToken:nameTokens){
                if(context.contains(nameToken)){
//                    System.out.println(name+":"+context);
                }
                else{
                    System.out.println(name+":"+context);
                    mark = 1;
                    break;
                }
            }
            if(mark==0){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void OOV_WhySucceed(String basePath1, String basePath2) throws FileNotFoundException {
        String successFile = basePath1 + "consistentSuccessAnalysis_Top1.txt";
        String tokensFile = basePath1 + "filteredSelectedMethodTokens_1.txt";
        String trainingFile = basePath2 + "TrainingMethodBodyTokens.txt";
        ArrayList<String> successList = ParserMethodNameMain.getLines(successFile);
        ArrayList<String> tokensList = ParserMethodNameMain.getLines(tokensFile);
        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        HashSet<String> tokensSet = new HashSet<>();
        HashSet<String> trainingTokensSet = new HashSet<>();
        int cnt = 0;

        for(String s:successList){
            int index = Utils.getIndice(s);
            String token = tokensList.get(index-3159);
            List<String> list = Arrays.asList(token.split(" "));
            for(String ss:list){
                tokensSet.add(ss);
            }
        }



        for(String s:trainingNamesList){
            List<String> list = Arrays.asList(s.split(" "));
            for(String ss:list){
                trainingTokensSet.add(ss);
            }
        }
        for(String s:tokensSet){
            if(trainingTokensSet.contains(s)){
//                System.out.println();
            }
            else{
                cnt++;
            }
        }
        System.out.println(tokensSet);
//        System.out.println(trainingTokensSet);
        System.out.println(tokensSet.size());
        System.out.println(trainingTokensSet.size());
        System.out.println(cnt);
    }

    private static void OOV_19(String basePath1, String basePath2) throws FileNotFoundException {
        String tokenFile = basePath1 + "allMethodTokens.txt";
//        String tokenFile = basePath1 + "filteredMethodTokens.txt";
//        String tokenFile = basePath1 + "TestMethodBodyTokens.txt";
        String trainingFile = basePath2 + "TrainingMethodBodyTokens.txt";
        ArrayList<String> tokensList = ParserMethodNameMain.getLines(tokenFile);
        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        HashSet<String> tokensSet = new HashSet<>();
        HashSet<String> trainingTokensSet = new HashSet<>();
        int cnt = 0;
        for(String s:tokensList){
            List<String> list = Arrays.asList(s.split(" "));
            for(String ss:list){
                tokensSet.add(ss);
            }
        }
        for(String s:trainingNamesList){
            List<String> list = Arrays.asList(s.split(" "));
            for(String ss:list){
                trainingTokensSet.add(ss);
            }
        }
        for(String s:tokensSet){
            if(trainingTokensSet.contains(s)){
//                System.out.println();
            }
            else{
                cnt++;
            }
        }
        System.out.println(tokensSet);
//        System.out.println(trainingTokensSet);
        System.out.println(tokensSet.size());
        System.out.println(trainingTokensSet.size());
        System.out.println(cnt);
    }

    private static void testOriginalData(String basePath1, String basePath2) throws FileNotFoundException {
        /*
        test original dataset:
        if all the method names in test data occurs in training data.
         */
//        String nameFile = basePath1 + "allMethodNameTokens.txt";
        String nameFile = basePath1 + "TestMethodNames.txt";
//        String nameFile = basePath1 + "parsedMethodContextTokens.txt";
        String trainingFile = basePath2 + "methodNames.txt";
//        String trainingFile = basePath2 + "ParsedMethodNameTokens_1.txt";
        ArrayList<String> namesList = ParserMethodNameMain.getLines(nameFile);
//        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        HashMap<String,Integer> trainingDataHashMap = shortMethodNamesTotalEva(trainingFile);
        int cnt = 0;
        for(String s : namesList){
            if(trainingDataHashMap.containsKey(s)){
                System.out.println(s+":"+trainingDataHashMap.get(s));
                cnt++;
                trainingDataHashMap.remove(s);
            }
            else{
                System.out.println(s+":"+0);
            }
        }
//        System.out.println(namesList.size());
        System.out.println(cnt);


    }
    private static HashMap<String,Integer> shortMethodNamesTotalEva(String basePath) throws FileNotFoundException {
//        String methodNameTokens = basePath + "parsedMethodNameTokens.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(basePath);
        System.out.println(lines.size());
        HashMap<String,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        for(String line:lines){
            String name = line;
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
//        System.out.println(list.subList(0,100));
        return methodNameTokensNumberAndNumber;
    }


    private static void OOV_20(String basePath1, String basePath2) throws FileNotFoundException {
//        String nameFile = basePath1 + "allMethodNameTokens.txt";
        String nameFile = basePath1 + "allMethodContextTokens.txt";
//        String nameFile = basePath1 + "TestMethodNames.txt";
//        String nameFile = basePath1 + "consistent_TrainingDataLT94Tokens\\false.txt";
//        String nameFile = basePath1 + "parsedMethodContextTokens.txt";
//        String trainingFile = basePath2 + "methodNames.txt";
//        String trainingFile = basePath2 + "parsedMethodNameTokens.txt";
//        String trainingFile = basePath2 + "parsedMethodContextTokens.txt";
        String trainingFile = basePath2 + "TrainingData\\ParsedMethodContextTokens_1.txt";
        ArrayList<String> namesList = ParserMethodNameMain.getLines(nameFile);
        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        HashSet<String> nameTokensSet = new HashSet<>();
        HashSet<String> trainingNameTokensSet = new HashSet<>();
        for(String s:namesList){
            List<String> list = Arrays.asList(s.split(" "));
            for(String ss:list){
                nameTokensSet.add(ss);
            }
        }

        for(String s:trainingNamesList){
            List<String> list = Arrays.asList(s.split(" "));
            for(String ss:list){
                trainingNameTokensSet.add(ss);
            }
        }

        int cnt = 0;
        for(String s:nameTokensSet){
            if(trainingNameTokensSet.contains(s)){

            }
            else{
                cnt++;
            }
        }
        System.out.println(nameTokensSet);
        System.out.println(nameTokensSet.size());
        System.out.println(trainingNameTokensSet.size());
        System.out.println(cnt);
        System.out.println(cnt*1.0/ nameTokensSet.size());

    }

    private static void getFalseOOVNumber1(String basePath1, HashSet<String> vocabulary) throws FileNotFoundException {
//        System.out.println(oovTokenSet.size());
//        String falseFile = basePath1 + "consistent_TrainingDataLT94Tokens\\false.txt";
//        String falseFile = basePath1 + "consistent_TrainingDataLT94Tokens\\falseContext.txt";
//        String falseFile = basePath1 + "consistent_TrainingDataLT94Tokens\\parsedMethodNameTokens.txt";
        String falseFile = basePath1 + "consistent_TrainingDataLT94Tokens\\parsedMethodContextTokens.txt";
//        String falseFile = basePath1 + "correct.txt";
        ArrayList<String> falseNamesList = ParserMethodNameMain.getLines(falseFile);
        ArrayList<String> falseOOVList = new ArrayList<>();
        int cnt = 0;
        int cnt1 = 0;
        for(String s:falseNamesList){
            boolean mark = false;
//            String methodName = s.split(":")[1];
            String methodName = s;
            List<String> tokens = Arrays.asList(methodName.split(" "));
            for(String token:tokens){
                if(vocabulary.contains(token)){
//                    cnt1++;
//                    mark=true;
                }
                else{
                    System.out.println(token);
                    System.out.println(methodName);
                    falseOOVList.add(methodName);
                    break;
                }
            }
//            if(mark!=true){
//                cnt++;
//            }
        }
        System.out.println(falseOOVList.size());
        System.out.println("falseNamesList:" + falseNamesList.size());
//        System.out.println(cnt);
//        System.out.println(cnt1);
    }

    private static void getFalseOOVNumber(String basePath1, HashSet<String> oovTokenSet) throws FileNotFoundException {
//        System.out.println(oovTokenSet.size());
        String falseFile = basePath1 + "consistent_TrainingDataLT94Tokens\\false.txt";
//        String falseFile = basePath1 + "correct.txt";
        ArrayList<String> falseNamesList = ParserMethodNameMain.getLines(falseFile);
        HashSet<String> falseOOVSet = new HashSet<>();
        int cnt = 0;
        int cnt1 = 0;
        for(String s:falseNamesList){
            boolean mark = false;
            String methodName = s.split(":")[1];
            List<String> tokens = Arrays.asList(methodName.split(" "));
            for(String token:tokens){
                if(oovTokenSet.contains(token)){
                    cnt1++;
                    mark=true;
                }
                else{
                    falseOOVSet.add(token);
                }
            }
            if(mark==true){
                cnt++;
            }
        }
//        System.out.println(falseOOVSet.size());
        System.out.println("falseNamesList:" + falseNamesList.size());
        System.out.println(cnt);
        System.out.println(cnt1);
    }

    private static HashSet<String> getOOVTokenSet(String basePath1, String basePath2) throws FileNotFoundException {
        String nameFile = basePath1 + "allMethodNameTokens.txt";
//        String nameFile = basePath1 + "parsedMethodNameTokens.txt";
        String trainingFile = basePath2 + "parsedMethodNameTokens.txt";
        ArrayList<String> namesList = ParserMethodNameMain.getLines(nameFile);
        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        HashSet<String> nameTokensSet = new HashSet<>();
        HashSet<String> trainingNameTokensSet = new HashSet<>();
        HashSet<String> oovTokensSet = new HashSet<>();
        for(String name:namesList){
            List<String> nameTokens = Arrays.asList(name.split(" "));
            for(String nameToken:nameTokens){
                nameTokensSet.add(nameToken);
            }
        }
        int cnt = 0;
        for(String name:trainingNamesList){
            List<String> nameTokens = Arrays.asList(name.split(" "));
            for(String nameToken:nameTokens){
                trainingNameTokensSet.add(nameToken);
            }
        }
        for(String name:nameTokensSet){
            if(trainingNameTokensSet.contains(name)){

            }
            else{
                cnt++;
                oovTokensSet.add(name);
            }
        }
        System.out.println("trainingNameTokensNumber:"+trainingNameTokensSet.size());
        System.out.println("consistentSamplesNameTokensNumber:"+nameTokensSet.size());
        System.out.println("OOV Number:"+cnt);
        System.out.println(oovTokensSet.size());
        return oovTokensSet;
    }

    private static void getOOVNumber(String basePath1, String basePath2) throws FileNotFoundException {
        String nameFile = basePath1 + "parsedMethodNameTokens.txt";
        String trainingFile = basePath2 + "parsedMethodNameTokens.txt";
        ArrayList<String> NamesList = ParserMethodNameMain.getLines(nameFile);
        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        HashSet<String> nameTokensSet = new HashSet<>();
        HashSet<String> trainingNameTokensSet = new HashSet<>();
        for(String name:NamesList){
            List<String> nameTokens = Arrays.asList(name.split(" "));
            for(String nameToken:nameTokens){
                nameTokensSet.add(nameToken);
            }
        }
        int cnt = 0;
        for(String name:trainingNamesList){
            List<String> nameTokens = Arrays.asList(name.split(" "));
            for(String nameToken:nameTokens){
                trainingNameTokensSet.add(nameToken);
            }
        }
        for(String name:nameTokensSet){
            if(trainingNameTokensSet.contains(name)){

            }
            else{
                cnt++;
            }
        }
        System.out.println("trainingNameTokensNumber:"+trainingNameTokensSet.size());
        System.out.println("consistentSamplesNameTokensNumber:"+nameTokensSet.size());
        System.out.println("OOV Number:"+cnt);

    }

    public static void IsCorrectOOV_20(String basePath1,String basePath2) throws FileNotFoundException {
        String correctFile = basePath1 + "correct.txt";

        String trainingFile = basePath2 + "ParsedMethodNameTokens_1.txt";
        int cnt = 0;
        ArrayList<String> correctNamesList = ParserMethodNameMain.getLines(correctFile);
        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        for(String correctName:correctNamesList){
            String name = correctName.split(":")[1];
            if(trainingNamesList.contains(name)){
                cnt++;
            }
        }
        System.out.println(correctNamesList.size());
        System.out.println(cnt);
    }
    public static void IsFalseOOV_20(String basePath1,String basePath2) throws FileNotFoundException {
        String falseFile = basePath1 + "false.txt";

        String trainingFile = basePath2 + "parsedMethodNameTokens.txt";
        int cnt = 0;
        int progress = 0;
        ArrayList<String> falseNamesList = ParserMethodNameMain.getLines(falseFile);
        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        for(String correctName:falseNamesList){
            String name = correctName.split(":")[1];
            if(trainingNamesList.contains(name)){
                cnt++;
            }
            progress++;
            if(progress%100==0){
                System.out.println(progress +"-------------- finished");
            }
        }
        System.out.println(falseNamesList.size());
        System.out.println(cnt);
    }

    public static void IsCorrectOOV_19(String basePath1,String basePath2) throws FileNotFoundException {
        String correctFile = basePath1 + "consistentSuccessAnalysis_Top1.txt";
//        String correctFile = basePath1 + "inconsistentSuccessAnalysis_Top1.txt";

        String trainingFile = basePath2 + "methodNames.txt";


        int cnt = 0;
        ArrayList<String> correctNamesList = ParserMethodNameMain.getLines(correctFile);
        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        for(String correctName:correctNamesList){
            int leftSquareBracketsIndex = correctName.indexOf("[");
            int rightSquareBracketsIndex = correctName.indexOf("]");

            String name = correctName.substring(leftSquareBracketsIndex+1,rightSquareBracketsIndex).replaceAll(","," ");
            if(trainingNamesList.contains(name)){
                cnt++;
            }
        }
        System.out.println(correctNamesList.size());
        System.out.println("InV:"+cnt);
        System.out.println("OOV:"+String.valueOf(correctNamesList.size()-cnt));
    }
    public static void IsFalseOOV_19(String basePath1,String basePath2) throws FileNotFoundException {
//        String correctFile = basePath1 + "consistentFailAnalysis_Top1.txt";
        String correctFile = basePath1 + "inconsistentFailAnalysis_Top1.txt";

        String trainingFile = basePath2 + "methodNames.txt";
        int cnt = 0;
        ArrayList<String> correctNamesList = ParserMethodNameMain.getLines(correctFile);
        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        for (String correctName : correctNamesList) {
            int leftSquareBracketsIndex = correctName.indexOf("[");
            int rightSquareBracketsIndex = correctName.indexOf("]");

            String name = correctName.substring(leftSquareBracketsIndex + 1, rightSquareBracketsIndex).replaceAll(",", " ");
            if (trainingNamesList.contains(name)) {
                cnt++;
            }
        }
        System.out.println(correctNamesList.size());
        System.out.println("InV:" + cnt);
        System.out.println("OOV:" + String.valueOf(correctNamesList.size() - cnt));
    }
}
