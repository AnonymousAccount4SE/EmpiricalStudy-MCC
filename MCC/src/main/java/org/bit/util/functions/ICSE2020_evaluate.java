/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/7
 */

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.evaluation.IREvaluation;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.util.*;

public class ICSE2020_evaluate {

    public static void main(String [] args) throws FileNotFoundException {
        // inconsistent evaluation
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\consistent\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\inconsistent\\";
//        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2020\\1_1\\inconsistent\\";
//        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2020\\1_1\\consistent\\";
//        String basePath1 = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TestData\\";
//        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2020\\1_1\\";

        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\";
//        evaluate(basePath1);
//        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\adjustThresholdValue\\";
//        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\consistent\\";
        String trainingPath = "E:\\Workspace\\ICSE2020TrainingData\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\Unique\\consistent\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\Unique\\1_1\\Consistent\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\2632\\Inconsistent\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\2632\\consistent\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\";
//
//        for(double i = 1; i>=0.00;i = i -0.01){
////            if(i>-0.01&&i<0.0){
////                String inconsistentResult = evaluate4adjustment(basePath1,"inconsistent_TrainingDataLT94Tokens",i);
////                String consistentResult = evaluate4adjustment(basePath1,"consistent_TrainingDataLT94Tokens",i);
//
////                String inconsistentResult = evaluate4adjustment(basePath1,"inconsistent",i);
////                String consistentResult = evaluate4adjustment(basePath1,"consistent",i);
//
//                String inconsistentResult = evaluate4adjustment(basePath1,"inconsistent_TrainingDataAllTokens",i);
//                String consistentResult = evaluate4adjustment(basePath1,"consistent_TrainingDataAllTokens",i);
//
//                int TP = Integer.parseInt(inconsistentResult.split(",")[0]);
//                int TN = Integer.parseInt(consistentResult.split(",")[0]);
//                int FN = Integer.parseInt(inconsistentResult.split(",")[1]);
//                int FP = Integer.parseInt(consistentResult.split(",")[1]);
//
////            System.out.println(TP);
////            System.out.println(TN);
////            System.out.println(FN);
////            System.out.println(FP);
////                System.out.println(i+"--------------------------------------------------------");
////                double res = IREvaluation.evaluate_acc(TP,TN,FN,FP);
////                String res = IREvaluation.evaluate_fscore(TP,TN,FN,FP);
////                String res = IREvaluation.evaluate_con(TP,TN,FN,FP);
//                String res = IREvaluation.evaluate_incon(TP,TN,FN,FP);
//            System.out.println(i+","+res);
////            }
//
//        }

        String inconsistentResult = evaluate4adjustment(basePath1,"inconsistent_TrainingDataAllTokens",0);
        String consistentResult = evaluate4adjustment(basePath1,"consistent_TrainingDataAllTokens",0);

        int TP = Integer.parseInt(inconsistentResult.split(",")[0]);
        int TN = Integer.parseInt(consistentResult.split(",")[0]);
        int FN = Integer.parseInt(inconsistentResult.split(",")[1]);
        int FP = Integer.parseInt(consistentResult.split(",")[1]);

//            System.out.println(TP);
//            System.out.println(TN);
//            System.out.println(FN);
//            System.out.println(FP);
//                System.out.println(i+"--------------------------------------------------------");
//                double res = IREvaluation.evaluate_acc(TP,TN,FN,FP);
//                String res = IREvaluation.evaluate_fscore(TP,TN,FN,FP);
                String res = IREvaluation.evaluate_con(TP,TN,FN,FP);
//        String res = IREvaluation.evaluate_incon(TP,TN,FN,FP);
        System.out.println(0+","+res);




//        evaluate_checkIfMatters(basePath);
//        shortMethodNamesEva(basePath1);
//        shortMethodNamesCorrectEva(basePath1);

//        detailedInfoOfGeneration(basePath1);


        // get consistent methods which are predicted as inconsistent and are with less than 3 tokens
//        getMethodsOfLT3Tokens(basePath1);

        // if only take care of the first token. // Deprecated
//        judgeWithFirstToken(basePath1);
        
//        ifTokenCouldBeFoundInContext(basePath1);

//        ifTraingTokenCouldBeFoundInContext(trainingPath);
//        ifTraingTokenCouldBeFoundInContext(basePath1);

        //get all unique names which are generated by MNIRE
//        List<Map.Entry<String,Integer>> uniqueNamesList = getAllUniqueNames(basePath1);
//
//        getAllUniqueNames1(basePath1);
//        List<Map.Entry<String,Integer>> totalNamesList = shortMethodNamesTotalEva(basePath1);
//
//        for(Map.Entry<String,Integer> entry:uniqueNamesList){
//            String methodName = entry.getKey();
//            int frequencyInCorrect = entry.getValue();
//            int frequencyInTotal = getSpecificValue(totalNamesList,methodName).getValue();
//            System.out.println(methodName+":"+frequencyInCorrect+":"+frequencyInTotal);
//        }

//        List<Map.Entry<String,Integer>> totalNamesList = shortMethodNamesTotalEva(basePath1);
//        System.out.println(totalNamesList);

    }


    private static void getAllUniqueNames1(String basePath1) throws FileNotFoundException {
//        String consistentSuccessFile = basePath1 + "ConsistentFailGetSetMethodsAnalysisReport.txt";
//        String consistentSuccessFile = basePath1 + "ConsistentSuccessGetSetMethodsAnalysisReport.txt";
//        java.lang.String consistentSuccessFile = basePath1 + "allMethodNameTokens.txt";
        java.lang.String consistentSuccessFile = basePath1 + "TestMethodNames.txt";
//        String consistentSuccessFile = basePath1 + "allGetSetResultsMethods.txt";
        ArrayList<java.lang.String> csList = ParserMethodNameMain.getLines(consistentSuccessFile);
        HashSet<String> set = new HashSet<>();
        for(java.lang.String s:csList){

            set.add(s);
        }
        System.out.println(set.size());

//        for(Map.Entry<String,Integer> s:list){
//            System.out.println(s.getKey()+":"+s.getValue());
//        }
//        System.out.println(list);
//        System.out.println(list.subList(0,100));
//        return list;
    }

    private static List<Map.Entry<String,Integer>> getAllUniqueNames(String basePath1) throws FileNotFoundException {
//        String consistentSuccessFile = basePath1 + "ConsistentFailGetSetMethodsAnalysisReport.txt";
//        String consistentSuccessFile = basePath1 + "ConsistentSuccessGetSetMethodsAnalysisReport.txt";
        String consistentSuccessFile = basePath1 + "allMethodNameTokens.txt";
//        String consistentSuccessFile = basePath1 + "allGetSetResultsMethods.txt";
        ArrayList<String> csList = ParserMethodNameMain.getLines(consistentSuccessFile);
        HashMap<String,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        for(String s:csList){
            String name = s.split(":")[0];
            if(methodNameTokensNumberAndNumber.get(name)==null){
                methodNameTokensNumberAndNumber.put(name,1);
            }
            else{
                int num = methodNameTokensNumberAndNumber.get(name);
                methodNameTokensNumberAndNumber.put(name,num+1);
            }
        }
        System.out.println(methodNameTokensNumberAndNumber.keySet().size());
        List<Map.Entry<String,Integer>> list =  Utils.sortMap(methodNameTokensNumberAndNumber);
//        for(Map.Entry<String,Integer> s:list){
//            System.out.println(s.getKey()+":"+s.getValue());
//        }
        System.out.println(list);
//        System.out.println(list.subList(0,100));
        return list;
    }


    private static void ifTraingTokenCouldBeFoundInContext(String trainingPath) throws FileNotFoundException {
//        String nameTokens = trainingPath + "parsedMethodNameTokens.txt";
//        String contextTokens = trainingPath + "parsedMethodContextTokens.txt";
        String nameTokens = trainingPath + "allMethodNameTokens.txt";
        String contextTokens = trainingPath + "allMethodContextTokens.txt";
        ArrayList<String> correctNameList = ParserMethodNameMain.getLines(nameTokens);
        ArrayList<String> correctContextList = ParserMethodNameMain.getLines(contextTokens);
        StringBuilder nameTokenCouldBeFoundInContext = new StringBuilder();
        StringBuilder contextTokenCouldBeFoundInContext = new StringBuilder();
        StringBuilder nameTokenCouldNotAllBeFoundInContext = new StringBuilder();
        StringBuilder contextTokenCouldNotAllBeFoundInContext = new StringBuilder();
        int cnt = 0;
        for(int i=0; i< correctNameList.size();i++){
            boolean mark = false;
            String correctName = correctNameList.get(i);
            String correctContext = correctContextList.get(i);

            List<String> correctNameTokens = Arrays.asList(correctName.split(" "));
            List<String> correctContextTokens = Arrays.asList(correctContext.split(" "));

            for(String s:correctNameTokens){
                if(!correctContextTokens.contains(s)){
                    mark = true;
                    cnt++;
                    break;

                }
            }
            if(!mark){
                nameTokenCouldBeFoundInContext.append(correctName+"\n");
                contextTokenCouldBeFoundInContext.append(correctContext+"\n");
            }
            else{
                nameTokenCouldNotAllBeFoundInContext.append(correctName+"\n");
                contextTokenCouldNotAllBeFoundInContext.append(correctContext+"\n");
            }

        }
        System.out.println(correctNameList.size());
        System.out.println(cnt);
//        FileHelper.outputToFile(trainingPath + "nameTokenCouldBeFoundInContext.txt",nameTokenCouldBeFoundInContext,false);
//        FileHelper.outputToFile(trainingPath + "contextTokenCouldBeFoundInContext.txt",contextTokenCouldBeFoundInContext,false);
//
//        FileHelper.outputToFile(trainingPath + "nameTokenCouldNotAllBeFoundInContext.txt",nameTokenCouldNotAllBeFoundInContext,false);
//        FileHelper.outputToFile(trainingPath + "contextTokenCouldNotAllBeFoundInContext.txt",contextTokenCouldNotAllBeFoundInContext,false);

    }

    private static void ifTokenCouldBeFoundInContext(String basePath) throws FileNotFoundException {
        String correctNameFile = basePath + "correct.txt";
        String correctContextFile = basePath + "correctContext.txt";
//        String correctNameFile = basePath + "false.txt";
//        String correctContextFile = basePath + "falseContext.txt";
//        String correctNameFile = basePath + "MethodsOfLT3FalseNameTokens.txt";
//        String correctContextFile = basePath + "MethodsOfLT3FalseContextTokens.txt";
        ArrayList<String> correctNameList = ParserMethodNameMain.getLines(correctNameFile);
        ArrayList<String> correctContextList = ParserMethodNameMain.getLines(correctContextFile);
        StringBuilder nameTokenCouldBeFoundInContext = new StringBuilder();
        StringBuilder contextTokenCouldBeFoundInContext = new StringBuilder();
        StringBuilder nameTokenCouldNotAllBeFoundInContext = new StringBuilder();
        StringBuilder contextTokenCouldNotAllBeFoundInContext = new StringBuilder();
        int cnt = 0;
        for(int i=0; i< correctNameList.size();i++){
            boolean mark = false;
            String correctName = correctNameList.get(i).split(":")[1];
            String correctContext = correctContextList.get(i);

            List<String> correctNameTokens = Arrays.asList(correctName.split(" "));
            List<String> correctContextTokens = Arrays.asList(correctContext.split(" "));

            for(String s:correctNameTokens){
                if(!correctContextTokens.contains(s)){
                    mark = true;
                    cnt++;
                    break;
                }
            }
            if(!mark){
                nameTokenCouldBeFoundInContext.append(correctName+"\n");
                contextTokenCouldBeFoundInContext.append(correctContext+"\n");
            }
            else{
                nameTokenCouldNotAllBeFoundInContext.append(correctName+"\n");
                contextTokenCouldNotAllBeFoundInContext.append(correctContext+"\n");
            }


        }
        System.out.println(correctNameList.size());
        System.out.println(cnt);
//        FileHelper.outputToFile(basePath + "nameTokenCouldBeFoundInContext_false.txt",nameTokenCouldBeFoundInContext,false);
//        FileHelper.outputToFile(basePath + "contextTokenCouldBeFoundInContext_false.txt",contextTokenCouldBeFoundInContext,false);

//        FileHelper.outputToFile(basePath + "nameTokenCouldNotAllBeFoundInContext_false.txt",nameTokenCouldNotAllBeFoundInContext,false);
//        FileHelper.outputToFile(basePath + "contextTokenCouldNotAllBeFoundInContext_false.txt",contextTokenCouldNotAllBeFoundInContext,false);
    }

    private static void judgeWithFirstToken(String basePath1) throws FileNotFoundException {
        String falseFile = basePath1 + "MethodsOfLT3Tokens.txt";
        ArrayList<String> falseList = ParserMethodNameMain.getLines(falseFile);
        StringBuilder sb = new StringBuilder();
        for(String s:falseList){
            String result = s.split(":")[0];
            String testMethod = s.split(":")[1];
            String firstTokenOfResult = result.split(" ")[0];
            String firstTokenOftestMethod = testMethod.split(" ")[0];
            if(firstTokenOfResult.equals(firstTokenOftestMethod)){
                sb.append(s+"\n");
            }
        }
        FileHelper.outputToFile(basePath1 + "JudgeWithFirstToken.txt",sb, false);
    }

    private static void getMethodsOfLT3Tokens(String basePath1) throws FileNotFoundException {
        String falseFile = basePath1 + "false.txt";
        String falseContextFile = basePath1 + "falseContext.txt";
        ArrayList<String> falseList = ParserMethodNameMain.getLines(falseFile);
        ArrayList<String> falseContextList = ParserMethodNameMain.getLines(falseContextFile);
        StringBuilder MethodsOfLT3FalseNameTokens = new StringBuilder();
        StringBuilder MethodsOfLT3FalseContextTokens = new StringBuilder();
        int index = 0;
        for(String s:falseList){
            String [] splitArray = s.split(":")[1].split(" ");
            if(splitArray.length>3){
            }
            else{
                MethodsOfLT3FalseNameTokens.append(s+"\n");
                MethodsOfLT3FalseContextTokens.append(falseContextList.get(index)+"\n");
            }
            index++;
        }
        FileHelper.outputToFile(basePath1 + "MethodsOfLT3FalseNameTokens.txt",MethodsOfLT3FalseNameTokens, false);
        FileHelper.outputToFile(basePath1 + "MethodsOfLT3FalseContextTokens.txt",MethodsOfLT3FalseContextTokens, false);
    }


    private static List<Map.Entry<String,Integer>> shortMethodNamesTotalEva(String basePath) throws FileNotFoundException {
//        String methodNameTokens = basePath + "parsedMethodNameTokens.txt";
//        String methodNameTokens = basePath + "allGeneratedCorrect.txt";
        String methodNameTokens = basePath + "allGeneratedCorrect_TrainingDataAllTokens.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodNameTokens);
        System.out.println(lines.size());
        HashMap<String,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        for(String line:lines){
            String name = line.split(":")[0];
            if(methodNameTokensNumberAndNumber.get(name)==null){
                methodNameTokensNumberAndNumber.put(name,1);
            }
            else{
                int num = methodNameTokensNumberAndNumber.get(name);
                methodNameTokensNumberAndNumber.put(name,num+1);
            }
        }
        for(Map.Entry<String,Integer> entry:methodNameTokensNumberAndNumber.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
//        System.out.println(methodNameTokensNumberAndNumber.keySet().size());
        List<Map.Entry<String,Integer>>list =  Utils.sortMap(methodNameTokensNumberAndNumber);
//        System.out.println(list.subList(0,100));
        return list;
    }

    private static List<Map.Entry<String,Integer>> shortMethodNamesTotalNameCorrectEva(String basePath,String fileName) throws FileNotFoundException {
//        String methodNameTokens = basePath + "correct.txt";
//        String methodNameTokens = basePath + "false.txt";
        String methodNameTokens = basePath + fileName;
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodNameTokens);
        System.out.println(lines.size());
        HashMap<String,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        for(String line:lines){
            String name = line.split(":")[0];
            if(methodNameTokensNumberAndNumber.get(name)==null){
                methodNameTokensNumberAndNumber.put(name,1);
            }
            else{
                int num = methodNameTokensNumberAndNumber.get(name);
                methodNameTokensNumberAndNumber.put(name,num+1);
            }
        }
        System.out.println(methodNameTokensNumberAndNumber.keySet().size());
        List<Map.Entry<String,Integer>>list =  Utils.sortMap(methodNameTokensNumberAndNumber);
//        for(Map.Entry<String,Integer> s:list){
//            System.out.println(s.getKey()+":"+s.getValue());
//        }
        System.out.println(list.subList(0,100));
        return list;
    }

    private static void shortMethodNamesCorrectEva(String basePath) throws FileNotFoundException {
//        String methodNameTokens = basePath + "correct.txt";
        String methodNameTokens = basePath + "allCorrect_TrainingDataAllTokens.txt";
//        String methodNameTokens = basePath + "false.txt";
//        String methodNameTokens = basePath + "parsedMethodNameTokens_unique.txt";
//        String methodNameTokens = basePath + "result.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodNameTokens);
        System.out.println(lines.size());
        HashMap<Integer,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        for(String line:lines){
            String [] splitArray = line.split(":")[0].split(" ");
            int size = splitArray.length;
            if(methodNameTokensNumberAndNumber.get(size)==null){
                methodNameTokensNumberAndNumber.put(size,1);
            }
            else{
                int num = methodNameTokensNumberAndNumber.get(size);
                methodNameTokensNumberAndNumber.put(size,num+1);
            }
        }
        List<Map.Entry<Integer,Integer>>list =  Utils.sortMap_1(methodNameTokensNumberAndNumber);
        System.out.println(list);
    }

    private static void shortMethodNamesEva(String basePath) throws FileNotFoundException {
//        String methodNameTokens = basePath + "MethodNameTokens.txt";
//        String methodNameTokens = basePath + "parsedMethodNameTokens.txt";
        String methodNameTokens = basePath + "allMethodNameTokens.txt";
//        String methodNameTokens = basePath + "TestMethodNames.txt";
//        String methodNameTokens = basePath + "TestMethodNames_RepairedVersion.txt";
//        String methodNameTokens = basePath + "allGeneratedCorrect.txt";
//        String methodNameTokens = basePath + "TrainingData\\ParsedMethodNameTokens_1.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodNameTokens);
        System.out.println(lines.size());
        HashMap<Integer,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        for(String line:lines){
//            String originalName = line.split(":")[1];
//            String [] splitArray = originalName.split(" ");
            String [] splitArray = line.split(" ");
//            String [] splitArray = line.split(",");
            int size = splitArray.length;
            System.out.println(size);
            if(methodNameTokensNumberAndNumber.get(size)==null){
                methodNameTokensNumberAndNumber.put(size,1);
            }
            else{
                int num = methodNameTokensNumberAndNumber.get(size);
                methodNameTokensNumberAndNumber.put(size,num+1);
            }
        }
        List<Map.Entry<Integer,Integer>>list =  Utils.sortMap_1(methodNameTokensNumberAndNumber);
        System.out.println(list);
    }

    private static void detailedInfoOfGeneration(String basePath) throws FileNotFoundException {
//        String methodNameTokens = basePath + "MethodNameTokens.txt";
//        String methodNameTokens = basePath + "parsedMethodNameTokens.txt";
        String methodNameTokens = basePath + "allMethodNameTokens.txt";
//        String methodNameTokens = basePath + "TestMethodNames_RepairedVersion.txt";
//        String methodNameTokens = basePath + "allGeneratedCorrect.txt";
//        String methodNameTokens = basePath + "TrainingData\\ParsedMethodNameTokens_1.txt";
//        String resultPath = basePath + "allResult.txt";
        String resultPath = basePath + "allResult_TrainingDataAllTokens.txt";
        ArrayList<String> nameTokens = ParserMethodNameMain.getLines(methodNameTokens);
        ArrayList<String> resultTokens = ParserMethodNameMain.getLines(resultPath);
//        System.out.println(nameTokens.size());
        int cnt1=0,cnt2=0,cnt3=0,cnt4=0,cnt5=0,cnt6=0;
        for(int i= 0;i<resultTokens.size();i++){
            String resultToken = resultTokens.get(i);
            String nameToken = nameTokens.get(i);
            int length = nameToken.split(" ").length;
            ArrayList<String> nameTokensList = new ArrayList<>(Arrays.asList(nameToken.split(" ")));
            ArrayList<String> resultTokensList = new ArrayList<>(Arrays.asList(resultToken.split(" ")));
            nameTokensList.retainAll(resultTokensList);
            if(length==13){
                cnt1++;
                if(nameTokensList.size()==0){
                    cnt2++;
                }
                else if(nameTokensList.size()==1){
                    cnt3++;
                }
                else if(nameTokensList.size()==2){
                    cnt4++;
                }
                else if(nameTokensList.size()==3){
                    cnt5++;
                }
                else{
                    cnt6++;
                }
            }
        }
        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);
        System.out.println(cnt4);
        System.out.println(cnt5);
        System.out.println(cnt6);
    }

    public static String evaluate4adjustment(String basePath, String type, double threshold) throws FileNotFoundException {
//        String result = basePath + type + "\\result.txt";
//        String result = basePath + type + "\\result_TrainingDataLT94Tokens.txt";
        String result = basePath + type + "\\result_TrainingDataAllTokens.txt";
//        String result = basePath + type + "\\result_0320.txt";
        String nameTokens = basePath + type + "\\parsedMethodNameTokens.txt";
        String contextTokens = basePath + type + "\\parsedMethodContextTokens.txt";
        ArrayList<String> resultLines = ParserMethodNameMain.getLines(result);
        ArrayList<String> nameTokensLines = ParserMethodNameMain.getLines(nameTokens);
        ArrayList<String> contextTokensLines = ParserMethodNameMain.getLines(contextTokens);

//        System.out.println(resultLines.size());
//        System.out.println(nameTokensLines.size());
        int c = 0;
        int ic =0;
        StringBuilder consb = new StringBuilder();
        StringBuilder inconsb = new StringBuilder();
        StringBuilder conContext = new StringBuilder();
        StringBuilder inconContext = new StringBuilder();
        for(int i=0;i<resultLines.size();i++){
            String resultLine = resultLines.get(i);
//            System.out.println(resultLine);
            String nameTokensLine = nameTokensLines.get(i);
            String contextTokensLine = contextTokensLines.get(i);
//            System.out.println(nameTokensLine);
            String [] splitArray1 = resultLine.split(" ");
            String [] splitArray2 = nameTokensLine.split(" ");
            List<String> resultArray = new ArrayList<>(Arrays.asList(splitArray1));
            List<String> nameTokensArray = new ArrayList<>(Arrays.asList(splitArray2));
            HashSet<String> resultSet = new HashSet<>(resultArray);
            HashSet<String> nameTokenSet = new HashSet<>(nameTokensArray);
//            resultArray.retainAll(nameTokensArray);
            resultSet.retainAll(nameTokenSet);
            double sum = splitArray1.length + splitArray2.length;
            double sum_div2 = sum / 2;
//            double sim = resultArray.size() / sum_div2;
            double sim = resultSet.size()*1.0 / sum_div2;
//            System.out.println(resultArray.size());
//            System.out.println(sum_div2);
//            System.out.println(sim);
            if(sim>threshold){
//                System.out.println(i+":Consistent:"+resultLine+":"+nameTokensLine);
                consb.append(resultLine+":"+nameTokensLine+"\n");
                conContext.append(contextTokensLine+"\n");
                c++;
            }
            else{
//                System.out.println(i+":Inconsistent:"+resultLine+":"+nameTokensLine);
                inconsb.append(resultLine+":"+nameTokensLine+"\n");
                inconContext.append(contextTokensLine+"\n");
                ic++;
            }
        }
//        if(type.equals("consistent_TrainingAndNegativeItems")){
//        if(type.equals("consistent_TrainingDataAllTokens")){
//        if(type.equals("consistent_TrainingDataLT94Tokens")){
//        if(type.equals("consistent")){
//            FileHelper.outputToFile(basePath + type + "\\correct.txt",consb,false);
//            FileHelper.outputToFile(basePath + type + "\\false.txt",inconsb,false);
//            FileHelper.outputToFile(basePath + type + "\\correctContext.txt",conContext,false);
//            FileHelper.outputToFile(basePath + type + "\\falseContext.txt",inconContext,false);
//        }
//        else{
//            FileHelper.outputToFile(basePath + type + "\\false.txt",consb,false);
//            FileHelper.outputToFile(basePath + type + "\\correct.txt",inconsb,false);
//            FileHelper.outputToFile(basePath + type + "\\falseContext.txt",conContext,false);
//            FileHelper.outputToFile(basePath + type + "\\correctContext.txt",inconContext,false);
//        }
//        if(type.equals("consistent_TrainingDataLT94Tokens")){
//        if(type.equals("consistent_TrainingAndNegativeItems")){
        if(type.equals("consistent_TrainingDataAllTokens")){
//        if(type.equals("consistent")){
//            System.out.println("TN:"+c);
//            System.out.println("FP:"+ic);
            return c+","+ic;
        }
        else {
//            System.out.println("TP:" + ic);
//            System.out.println("FN:" + c);
            return ic + "," + c;
        }


    }
    public static void evaluate(String basePath, String type) throws FileNotFoundException {
        String result = basePath + type + "\\result.txt";
        String nameTokens = basePath + type + "\\parsedMethodNameTokens.txt";
        String contextTokens = basePath + type + "\\parsedMethodContextTokens.txt";
        ArrayList<String> resultLines = ParserMethodNameMain.getLines(result);
        ArrayList<String> nameTokensLines = ParserMethodNameMain.getLines(nameTokens);
        ArrayList<String> contextTokensLines = ParserMethodNameMain.getLines(contextTokens);

//        System.out.println(resultLines.size());
//        System.out.println(nameTokensLines.size());
        int c = 0;
        int ic =0;
        StringBuilder consb = new StringBuilder();
        StringBuilder inconsb = new StringBuilder();
        StringBuilder conContext = new StringBuilder();
        StringBuilder inconContext = new StringBuilder();
        StringBuilder simSB = new StringBuilder();
        for(int i=0;i<resultLines.size();i++){
            String resultLine = resultLines.get(i);
//            System.out.println(resultLine);
            String nameTokensLine = nameTokensLines.get(i);
            String contextTokensLine = contextTokensLines.get(i);
//            System.out.println(nameTokensLine);
            String [] splitArray1 = resultLine.split(" ");
            String [] splitArray2 = nameTokensLine.split(" ");
            List<String> resultArray = new ArrayList<>(Arrays.asList(splitArray1));
            List<String> nameTokensArray = new ArrayList<>(Arrays.asList(splitArray2));
            HashSet<String> resultSet = new HashSet<>(resultArray);
            HashSet<String> nameTokenSet = new HashSet<>(nameTokensArray);
//            resultArray.retainAll(nameTokensArray);
            resultSet.retainAll(nameTokenSet);
            double sum = splitArray1.length + splitArray2.length;
            double sum_div2 = sum / 2;
//            double sim = resultArray.size() / sum_div2;
            double sim = resultSet.size() / sum_div2;
            simSB.append(sim+"\n");
//            System.out.println(resultArray.size());
//            System.out.println(sum_div2);
//            System.out.println(sim);
//            if(sim>0.8){
////                System.out.println(i+":Consistent:"+resultLine+":"+nameTokensLine);
//                consb.append(resultLine+":"+nameTokensLine+"\n");
//                conContext.append(contextTokensLine+"\n");
//                c++;
//            }
//            else{
////                System.out.println(i+":Inconsistent:"+resultLine+":"+nameTokensLine);
//                inconsb.append(resultLine+":"+nameTokensLine+"\n");
//                inconContext.append(contextTokensLine+"\n");
//                ic++;
//            }
//        }
//        if(type.equals("consistent")){
//            System.out.println("TN:"+c);
//            System.out.println("FP:"+ic);
//        }
//        else{
//            System.out.println("TP:"+ic);
//            System.out.println("FN:"+c);
//
//        }
//
//        if(type.equals("consistent")){
////            FileHelper.outputToFile(basePath + "correct.txt",consb,false);
////            FileHelper.outputToFile(basePath + "false.txt",inconsb,false);
//            FileHelper.outputToFile(basePath + "correctContext.txt",conContext,false);
//            FileHelper.outputToFile(basePath + "falseContext.txt",inconContext,false);
//        }
//        else{
////            FileHelper.outputToFile(basePath + "false.txt",consb,false);
////            FileHelper.outputToFile(basePath + "correct.txt",inconsb,false);
//            FileHelper.outputToFile(basePath + "falseContext.txt",conContext,false);
//            FileHelper.outputToFile(basePath + "correctContext.txt",inconContext,false);
        }
    }
    public static void evaluate(String basePath) throws FileNotFoundException {
        String result = basePath +  "\\allResult_TrainingDataAllTokens.txt";
        String nameTokens = basePath + "\\allMethodNameTokens.txt";
//        String contextTokens = basePath + "\\parsedMethodContextTokens.txt";
        ArrayList<String> resultLines = ParserMethodNameMain.getLines(result);
        ArrayList<String> nameTokensLines = ParserMethodNameMain.getLines(nameTokens);
//        ArrayList<String> contextTokensLines = ParserMethodNameMain.getLines(contextTokens);

//        System.out.println(resultLines.size());
//        System.out.println(nameTokensLines.size());
        int c = 0;
        int ic =0;
        StringBuilder consb = new StringBuilder();
        StringBuilder inconsb = new StringBuilder();
        StringBuilder conContext = new StringBuilder();
        StringBuilder inconContext = new StringBuilder();
        StringBuilder simSB = new StringBuilder();
        for(int i=0;i<resultLines.size();i++){
            String resultLine = resultLines.get(i);
//            System.out.println(resultLine);
            String nameTokensLine = nameTokensLines.get(i);
//            String contextTokensLine = contextTokensLines.get(i);
//            System.out.println(nameTokensLine);
            String [] splitArray1 = resultLine.split(" ");
            String [] splitArray2 = nameTokensLine.split(" ");
            List<String> resultArray = new ArrayList<>(Arrays.asList(splitArray1));
            List<String> nameTokensArray = new ArrayList<>(Arrays.asList(splitArray2));
            HashSet<String> resultSet = new HashSet<>(resultArray);
            HashSet<String> nameTokenSet = new HashSet<>(nameTokensArray);
//            resultArray.retainAll(nameTokensArray);
            resultSet.retainAll(nameTokenSet);
            double sum = splitArray1.length + splitArray2.length;
            double sum_div2 = sum / 2;
//            double sim = resultArray.size() / sum_div2;
            double sim = resultSet.size() / sum_div2;
            simSB.append(sim+"\n");
//            System.out.println(resultArray.size());
//            System.out.println(sum_div2);
//            System.out.println(sim);
//            if(sim>0.8){
////                System.out.println(i+":Consistent:"+resultLine+":"+nameTokensLine);
//                consb.append(resultLine+":"+nameTokensLine+"\n");
//                conContext.append(contextTokensLine+"\n");
//                c++;
//            }
//            else{
////                System.out.println(i+":Inconsistent:"+resultLine+":"+nameTokensLine);
//                inconsb.append(resultLine+":"+nameTokensLine+"\n");
//                inconContext.append(contextTokensLine+"\n");
//                ic++;
//            }
//        }
//        if(type.equals("consistent")){
//            System.out.println("TN:"+c);
//            System.out.println("FP:"+ic);
//        }
//        else{
//            System.out.println("TP:"+ic);
//            System.out.println("FN:"+c);
//
//        }
//
//        if(type.equals("consistent")){
////            FileHelper.outputToFile(basePath + "correct.txt",consb,false);
////            FileHelper.outputToFile(basePath + "false.txt",inconsb,false);
//            FileHelper.outputToFile(basePath + "correctContext.txt",conContext,false);
//            FileHelper.outputToFile(basePath + "falseContext.txt",inconContext,false);
//        }
//        else{
////            FileHelper.outputToFile(basePath + "false.txt",consb,false);
////            FileHelper.outputToFile(basePath + "correct.txt",inconsb,false);
//            FileHelper.outputToFile(basePath + "falseContext.txt",conContext,false);
//            FileHelper.outputToFile(basePath + "correctContext.txt",inconContext,false);
        }
        FileHelper.outputToFile(basePath+"similarities.txt",simSB,false);
    }

    public static void evaluate_checkIfMatters(String basePath) throws FileNotFoundException {
        String result = basePath + "result.txt";
        String nameTokens = basePath + "parsedMethodNameTokens_unique.txt";
        String contextTokens = basePath + "parsedMethodContextTokens_unique.txt";
        String icse2020keepFile = "E:\\BIT\\BadMethodName\\evaluation\\icse2020\\icse2020keep.txt";
        ArrayList<String> resultLines = ParserMethodNameMain.getLines(result);
        ArrayList<String> nameTokensLines = ParserMethodNameMain.getLines(nameTokens);
        ArrayList<String> contextTokensLines = ParserMethodNameMain.getLines(contextTokens);
        ArrayList<String> keepLines = ParserMethodNameMain.getLines(icse2020keepFile);
        Set<Integer> keepSet = new HashSet<>();
        for(String line:keepLines){
            int lineNumber = Integer.valueOf(line.split(" : ")[0]);
            keepSet.add(lineNumber);
        }
        System.out.println("keepSet.size()"+keepSet.size());
        Set<Integer> TPlineNumberSet = new HashSet<>();
        Set<Integer> FNlineNumberSet = new HashSet<>();
        Set<Integer> TNlineNumberSet = new HashSet<>();
        Set<Integer> FPlineNumberSet = new HashSet<>();

        System.out.println(resultLines.size());
        System.out.println(nameTokensLines.size());
        int c = 0;
        int ic =0;
        StringBuilder consb = new StringBuilder();
        StringBuilder inconsb = new StringBuilder();
        StringBuilder conContext = new StringBuilder();
        StringBuilder inconContext = new StringBuilder();
        for(int i=0;i<resultLines.size();i++){
            String resultLine = resultLines.get(i);
//            System.out.println(resultLine);
            String nameTokensLine = nameTokensLines.get(i);
            String contextTokensLine = contextTokensLines.get(i);
//            System.out.println(nameTokensLine);
            String [] splitArray1 = resultLine.split(" ");
            String [] splitArray2 = nameTokensLine.split(" ");
            List<String> resultArray = new ArrayList<>(Arrays.asList(splitArray1));
            List<String> nameTokensArray = new ArrayList<>(Arrays.asList(splitArray2));
            HashSet<String> resultSet = new HashSet<>(resultArray);
            HashSet<String> nameTokenSet = new HashSet<>(nameTokensArray);
//            resultArray.retainAll(nameTokensArray);
            resultSet.retainAll(nameTokenSet);
            double sum = splitArray1.length + splitArray2.length;
            double sum_div2 = sum / 2;
//            double sim = resultArray.size() / sum_div2;
            double sim = resultSet.size() / sum_div2;
//            System.out.println(resultArray.size());
//            System.out.println(sum_div2);
            System.out.println(sim);
            if(sim>0.94){
                System.out.println(i+":Consistent:"+resultLine+":"+nameTokensLine);
                consb.append(resultLine+":"+nameTokensLine+"\n");
                conContext.append(contextTokensLine+"\n");
                c++;
                FNlineNumberSet.add(i);
            }
            else{
                System.out.println(i+":Inconsistent:"+resultLine+":"+nameTokensLine);
                inconsb.append(resultLine+":"+nameTokensLine+"\n");
                inconContext.append(contextTokensLine+"\n");
                ic++;
                TPlineNumberSet.add(i);
            }
        }
        System.out.println("c:"+c);
        System.out.println("ic:"+ic);
//        FileHelper.outputToFile(basePath + "correct.txt",consb,false);
//        FileHelper.outputToFile(basePath + "false.txt",inconsb,false);
//        FileHelper.outputToFile(basePath + "falseContext_1.txt",conContext,false);
//        FileHelper.outputToFile(basePath + "correctContext_1.txt",inconContext,false);
//
//        FileHelper.outputToFile(basePath + "false_1.txt",consb,false);
//        FileHelper.outputToFile(basePath + "correct_1.txt",inconsb,false);
        System.out.println(TPlineNumberSet.size());
        System.out.println(FNlineNumberSet.size());
        int TP=0,FN=0;
        for(int lineNumber : TPlineNumberSet) {
            if (keepSet.contains(lineNumber)) {
                TP++;
            }
        }
        for(int lineNumber:FNlineNumberSet){
            if(keepSet.contains(lineNumber)){
                FN++;
            }
        }
        System.out.println(TP+":"+FN);
    }

}
