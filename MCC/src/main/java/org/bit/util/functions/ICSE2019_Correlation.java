/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/3/9
 */

import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.util.*;

public class ICSE2019_Correlation {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\";
        String trainingPath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
        // calculate first token frequency correlation

        HashMap<String,Integer> correct = getFirstTokenAndNumber(basePath1);
        HashMap<String,Integer> consistent = getFirstTokenAndNumber_1(basePath1);
        HashMap<String,Integer> training = getFirstTokenAndNumber_2(trainingPath);
        List<HashMap<Integer, Double>> mapList = firstTokenCorrelation(correct,consistent,training);
        frequencyCorrelation(mapList);

        // calculate method name complexity correlation

//        HashMap<Integer,Integer> training = getTotalBodyASTNodeNumber(trainingPath,"TrainingMethodBodyTokens.txt");


//        HashMap<Integer,Integer> correct = getCorrectBodyASTNodeNumber(basePath1);
//        HashMap<Integer,Integer> consistent = getTotalBodyASTNodeNumber(basePath1,"allMethodTokens.txt");
//
//        BodyASTNodeNumberCorrelation(correct,consistent);
//        frequencyCorrelation(mapList);

    }
    private static void frequencyCorrelation(List<HashMap<Integer,Double>> frequencyHashMapList) {
        int cnt1 = 0,cnt2=0,cnt3=0,cnt4=0,cnt5=0,cnt6=0, cnt7=0,cnt_0=0,cnt_1=0,cnt_2=0,cnt_3=0,cnt_4=0,cnt_5=0,cnt_6=0;
        double zoneSum1=0;double zoneSum2=0;double zoneSum3=0;double zoneSum4=0;double zoneSum5=0;double zoneSum6=0;
        double zoneSum7=0;double zoneSum_0=0;double zoneSum_1=0,zoneSum_2=0,zoneSum_3=0,zoneSum_4=0,zoneSum_5=0,zoneSum_6=0;
        for(HashMap<Integer,Double> map:frequencyHashMapList){
            Map.Entry<Integer,Double> entry = map.entrySet().iterator().next();
            double successRatio = entry.getValue();
            int frequencyInTrainingData = entry.getKey();

//            if(frequencyInTrainingData==0){
//                zoneSum_0 += successRatio;
//                cnt_0++;
//            }
//            else if(frequencyInTrainingData<=2&&frequencyInTrainingData>0){
//                zoneSum_1 += successRatio;
//                cnt_1++;
//            }
            if(frequencyInTrainingData<=5&&frequencyInTrainingData>0){
                zoneSum_2 += successRatio;
                cnt_2++;
            }
            else if(frequencyInTrainingData<=10&&frequencyInTrainingData>5){
                zoneSum_3 += successRatio;
                cnt_3++;
            }
            else if(frequencyInTrainingData<=20&&frequencyInTrainingData>10){
                zoneSum_4 += successRatio;
                cnt_4++;
            }
            else if(frequencyInTrainingData<=40&&frequencyInTrainingData>20){
                zoneSum_5 += successRatio;
                cnt_5++;
            }
            else if(frequencyInTrainingData<=80&&frequencyInTrainingData>40){
                zoneSum_6 += successRatio;
                cnt_6++;
            }
            else if(frequencyInTrainingData<=200&&frequencyInTrainingData>80){
                zoneSum1 += successRatio;
                cnt1++;
            }
            else if(frequencyInTrainingData<=500&&frequencyInTrainingData>200){
                zoneSum3 += successRatio;
                cnt3++;
            }
            else if(frequencyInTrainingData>500){
                zoneSum7 += successRatio;
                cnt7++;
            }
//            else if(frequencyInTrainingData<=3000&&frequencyInTrainingData>500){
//                zoneSum4 += successRatio;
//                cnt4++;
//            }
//            else if(frequencyInTrainingData<=5000&&frequencyInTrainingData>3000){
//                zoneSum5 += successRatio;
//                cnt5++;
//            }
//            else if(frequencyInTrainingData<=10000&&frequencyInTrainingData>5000){
//                zoneSum6 += successRatio;
//                cnt6++;
//            }
//            else if(frequencyInTrainingData>3000){
//                zoneSum7 += successRatio;
//                cnt7++;
//            }
            else{
//                System.out.println(frequencyInTrainingData);
//                System.out.println("error");
            }
        }
//        System.out.println(zoneSum_0/cnt_0+":"+cnt_0);
//        System.out.println(zoneSum_1/cnt_1+":"+cnt_1);
        System.out.println(zoneSum_2/cnt_2+":"+cnt_2);
        System.out.println(zoneSum_3/cnt_3+":"+cnt_3);
        System.out.println(zoneSum_4/cnt_4+":"+cnt_4);
        System.out.println(zoneSum_5/cnt_5+":"+cnt_5);
        System.out.println(zoneSum_6/cnt_6+":"+cnt_6);

        System.out.println(zoneSum1/cnt1+":"+cnt1);
//        System.out.println(zoneSum2/cnt2+":"+cnt2);
        System.out.println(zoneSum3/cnt3+":"+cnt3);
//        System.out.println(zoneSum4/cnt4+":"+cnt4);
//        System.out.println(zoneSum5/cnt5+":"+cnt5);
//        System.out.println(zoneSum6/cnt6+":"+cnt6);
        System.out.println(zoneSum7/cnt7+":"+cnt7);

    }
    private static void BodyASTNodeNumberCorrelation(HashMap<Integer, Integer> correct, HashMap<Integer, Integer> consistent) {
//        HashMap<Integer, Double> map = new HashMap<Integer, Double>();
        for(Map.Entry<Integer,Integer> entry:consistent.entrySet()){
            int ASTNodeNumber = entry.getKey();
            int value=0;
            if(correct.containsKey(ASTNodeNumber))
                value = correct.get(ASTNodeNumber);
            else
                value = 0;
            int total = entry.getValue();

            System.out.println(ASTNodeNumber+":"+1.0*value/total+":"+value+":"+total);
//            map.put(training1,1.0*value/total);

        }
//        List<Map.Entry<Integer,Double>> list= Utils.sortMapByKey(map);
//        System.out.println(list);
//        for(Map.Entry<Integer,Double> entry:list){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
//        return map;
    }

    private static HashMap<Integer, Integer> getTotalBodyASTNodeNumber(String basePath ,String file) throws FileNotFoundException {
        String contextFile = basePath + file;
        ArrayList<String> contextList = ParserMethodNameMain.getLines(contextFile);
        HashMap<Integer,Integer> ASTNodeNumberAndFrequency = new HashMap<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        System.out.println(contextList.size());
        int i=0;
        for(String context:contextList){
            int ASTNodeNumber = context.split(" ").length/2;
//            if(ASTNodeNumber==0){
//                System.out.println(i);
//
//            }
            if(ASTNodeNumberAndFrequency.get(ASTNodeNumber)==null){
                ASTNodeNumberAndFrequency.put(ASTNodeNumber,1);
            }
            else{
                int num = ASTNodeNumberAndFrequency.get(ASTNodeNumber);
                ASTNodeNumberAndFrequency.put(ASTNodeNumber,num+1);
            }
            i++;
        }
        for(Map.Entry<Integer,Integer> entry:ASTNodeNumberAndFrequency.entrySet()){
            System.out.println(entry.getKey()+":"+ entry.getValue());
        }
        System.out.println(ASTNodeNumberAndFrequency);
//        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(ASTNodeNumberAndFrequency.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
//            public int compare(Map.Entry<Integer, Integer> o1,
//                               Map.Entry<Integer, Integer> o2) {
//                return (o2.getValue() - o1.getValue());
//            }
//        });
//        System.out.println(list);
        return ASTNodeNumberAndFrequency;
    }

    private static HashMap<Integer, Integer> getCorrectBodyASTNodeNumber(String basePath) throws FileNotFoundException {
        String consistentSuccessAnalysis = basePath + "allSuccessAnalysis_Top1.txt";
        String contextFile = basePath + "allMethodTokens.txt";

        ArrayList<String> successList = ParserMethodNameMain.getLines(consistentSuccessAnalysis);
        ArrayList<String> contextList = ParserMethodNameMain.getLines(contextFile);

        HashMap<Integer,Integer> ASTNodeNumberAndFrequency = new HashMap<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        for(String s:successList){
            int index = Utils.getIndice(s);
            indexes.add(index);
        }

        for(int index:indexes){
            String context = contextList.get(index);
            int ASTNodeNumber = context.split(" ").length/2;
            if(ASTNodeNumberAndFrequency.get(ASTNodeNumber)==null){
                ASTNodeNumberAndFrequency.put(ASTNodeNumber,1);
            }
            else{
                int num = ASTNodeNumberAndFrequency.get(ASTNodeNumber);
                ASTNodeNumberAndFrequency.put(ASTNodeNumber,num+1);
            }
        }
        System.out.println(ASTNodeNumberAndFrequency);
//        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(ASTNodeNumberAndFrequency.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
//            public int compare(Map.Entry<Integer, Integer> o1,
//                               Map.Entry<Integer, Integer> o2) {
//                return (o2.getValue() - o1.getValue());
//            }
//        });
//        System.out.println(list);
        return ASTNodeNumberAndFrequency;
    }

    private static  List<HashMap<Integer, Double>> firstTokenCorrelation(HashMap<String, Integer> correct, HashMap<String, Integer> consistent,HashMap<String, Integer> training) {
        List<HashMap<Integer, Double>> mapList = new ArrayList<>();
        for(Map.Entry<String,Integer> entry:consistent.entrySet()){
            HashMap<Integer, Double> map = new HashMap<>();
            String name = entry.getKey();
            int value = 0;
            if(correct.containsKey(name)){
                value = correct.get(name);
            }
            else{
                value = 0;
            }
            int total = entry.getValue();
            int training1=0;
            if(training.containsKey(name)){
                training1 = training.get(name);
            }
            else{
                training1 = 0;
            }
            System.out.println(name+":"+training1+":"+1.0*value/total+":"+value+":"+total);
            if(total>=5) {
                map.put(training1, 1.0 * value / total);
                mapList.add(map);
            }
        }
//        List<Map.Entry<Integer,Double>> list= Utils.sortMapByKey(map);
//        System.out.println(list);
//        for(Map.Entry<Integer,Double> entry:list){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
        return mapList;
    }

    public static HashMap<String,Integer> getFirstTokenAndNumber_2(String basePath) throws FileNotFoundException {

        String consistentSuccessAnalysis = basePath + "methodNames.txt";
        ArrayList<String> successList = ParserMethodNameMain.getLines(consistentSuccessAnalysis);
        HashMap<String,Integer> firstTokenAndNumber = new HashMap<>();
        for(String s:successList){
            String firstToken = s.split(" ")[0];
            if(firstTokenAndNumber.get(firstToken)==null){
                firstTokenAndNumber.put(firstToken,1);
            }
            else{
                int num = firstTokenAndNumber.get(firstToken);
                firstTokenAndNumber.put(firstToken,num+1);
            }
        }

        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(firstTokenAndNumber.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        System.out.println(list);
        return firstTokenAndNumber;
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetNamePredictResult.txt",getsetLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetBodyPredictResult.txt",getsetLines,false);
    }

    public static HashMap<String,Integer> getFirstTokenAndNumber_1(String basePath) throws FileNotFoundException {

        String consistentSuccessAnalysis = basePath + "TestingMethodNames.txt";
        ArrayList<String> successList = ParserMethodNameMain.getLines(consistentSuccessAnalysis);
        HashMap<String,Integer> firstTokenAndNumber = new HashMap<>();
        for(String s:successList){
            String firstToken = s.split(" ")[0];
            if(firstTokenAndNumber.get(firstToken)==null){
                firstTokenAndNumber.put(firstToken,1);
            }
            else{
                int num = firstTokenAndNumber.get(firstToken);
                firstTokenAndNumber.put(firstToken,num+1);
            }
        }

        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(firstTokenAndNumber.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        System.out.println(list);
        return firstTokenAndNumber;
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetNamePredictResult.txt",getsetLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetBodyPredictResult.txt",getsetLines,false);
    }

    public static HashMap<String,Integer> getFirstTokenAndNumber(String basePath) throws FileNotFoundException {

        String consistentSuccessAnalysis = basePath + "allSuccessAnalysis_Top1.txt";
        ArrayList<String> successList = ParserMethodNameMain.getLines(consistentSuccessAnalysis);
        HashMap<String,Integer> firstTokenAndNumber = new HashMap<>();
        for(String s:successList){
            String firstToken = Utils.getFirstToken(s);
            if(firstTokenAndNumber.get(firstToken)==null){
                firstTokenAndNumber.put(firstToken,1);
            }
            else{
                int num = firstTokenAndNumber.get(firstToken);
                firstTokenAndNumber.put(firstToken,num+1);
            }
        }

        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(firstTokenAndNumber.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        System.out.println(list);
        return firstTokenAndNumber;
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetNamePredictResult.txt",getsetLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetBodyPredictResult.txt",getsetLines,false);
    }

    private static List<Map.Entry<Integer, Integer>> MethodContextCorrectEva(String basePath, String fileName) throws FileNotFoundException {
        String methodContextTokens = basePath + fileName;
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodContextTokens);
        System.out.println(lines.size());
        HashMap<Integer,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        for(String line:lines){
            int contextSize = line.split(" ").length;
            if(methodNameTokensNumberAndNumber.get(contextSize)==null){
                methodNameTokensNumberAndNumber.put(contextSize,1);
            }
            else{
                int num = methodNameTokensNumberAndNumber.get(contextSize);
                methodNameTokensNumberAndNumber.put(contextSize,num+1);
            }
        }
        System.out.println(methodNameTokensNumberAndNumber.keySet().size());
        List<Map.Entry<Integer,Integer>>list =  Utils.sortMap_2(methodNameTokensNumberAndNumber);
//        for(Map.Entry<String,Integer> s:list){
//            System.out.println(s.getKey()+":"+s.getValue());
//        }
//        System.out.println(list.subList(0,100));
        return list;
    }


    private static HashMap<Integer,Double> evaluateContextCorrelation( List<Map.Entry<Integer,Integer>>
                                                                        correctDataHashMap, List<Map.Entry<Integer,Integer>> consistentDataHashMap) {

        ArrayList<Double> successRatioList = new ArrayList<>();
        ArrayList<Integer> tokenNumberList = new ArrayList<>();
        HashMap<Integer,Double> map = new HashMap<>();
        for(Map.Entry<Integer,Integer> entry:correctDataHashMap){
            int contextSize = entry.getKey();
            int correctNumber = entry.getValue();
            int totalNumber = getSpecificValue_1(consistentDataHashMap,contextSize).getValue();
//            int totalNumber = 6776;
            Double successRatio = 1.0*correctNumber/totalNumber;
            successRatioList.add(successRatio);
//            int tokenNumber = methodName.split(" ").length;
            tokenNumberList.add(contextSize);
            map.put(contextSize,successRatio);
            System.out.println(contextSize+":"+successRatio+":"+correctNumber+":"+totalNumber);
        }
//        System.out.println(successRatioList.size());
//        System.out.println(tokenNumberList.size());
//        System.out.println(successRatioList);
//        System.out.println(tokenNumberList);
//        List<Map.Entry<Integer,Double>>list =  Utils.sortMapByKey(map);
//
//        System.out.println(list);
//        for(Map.Entry<Integer,Double> s:list){
//            System.out.println(s.getKey()+":"+s.getValue());
//        }

        return map;


    }

    private static HashMap<Integer,Double> evaluateFrequencyCorrelation( List<Map.Entry<String,Integer>>
            correctDataHashMap, List<Map.Entry<String,Integer>> consistentDataHashMap) {

        ArrayList<Double> successRatioList = new ArrayList<>();
        ArrayList<Integer> tokenNumberList = new ArrayList<>();
        HashMap<Integer,Double> map = new HashMap<>();
        for(Map.Entry<String,Integer> entry:correctDataHashMap){
            String methodName = entry.getKey();
            int correctNumber = entry.getValue();
            int totalNumber = getSpecificValue(consistentDataHashMap,methodName).getValue();
//            int totalNumber = 6776;
            Double successRatio = 1.0*correctNumber/totalNumber;
            successRatioList.add(successRatio);
            int tokenNumber = methodName.split(" ").length;
            tokenNumberList.add(tokenNumber);
            map.put(tokenNumber,successRatio);

        }
        System.out.println(successRatioList.size());
        System.out.println(tokenNumberList.size());
        System.out.println(successRatioList);
        System.out.println(tokenNumberList);
        List<Map.Entry<Integer,Double>>list =  Utils.sortMapByKey(map);

        System.out.println(list);
        for(Map.Entry<Integer,Double> s:list){
            System.out.println(s.getKey()+":"+s.getValue());
        }

        return map;


    }

    private static HashMap<Integer,Double> evaluateCorrelation(List<Map.Entry<String,Integer>> trainingDataHashMap, List<Map.Entry<String,Integer>>
            correctDataHashMap, List<Map.Entry<String,Integer>> consistentDataHashMap) {

        ArrayList<Double> successRatioList = new ArrayList<>();
        ArrayList<Integer> frequencyList = new ArrayList<>();
        HashMap<Integer,Double> map = new HashMap<>();
        for(Map.Entry<String,Integer> entry:correctDataHashMap){
            String methodName = entry.getKey();
            int correctNumber = entry.getValue();
            int totalNumber = getSpecificValue(consistentDataHashMap,methodName).getValue();
//            int totalNumber = 6776;
            Double successRatio = 1.0*correctNumber/totalNumber;
            successRatioList.add(successRatio);
            int frequency = getSpecificValue(trainingDataHashMap,methodName).getValue();
            frequencyList.add(frequency);

            System.out.println(methodName+":"+frequency+":"+successRatio+":"+correctNumber+":"+totalNumber);
        }
//        System.out.println(successRatioList.size());
//        System.out.println(frequencyList.size());
//        System.out.println(successRatioList);
//        System.out.println(frequencyList);
//        List<Map.Entry<Integer,Double>>list =  Utils.sortMapByKey(map);
//
//        System.out.println(list);
//        for(Map.Entry<Integer,Double> s:list){
//            System.out.println(s.getKey()+":"+s.getValue());
//        }

        return map;


    }

    private static Map.Entry<String,Integer> getSpecificValue(List<Map.Entry<String,Integer>> list, String key){
        for(Map.Entry<String,Integer> entry:list){
            if(entry.getKey().equals(key)){
                return entry;
            }
        }
        return null;
    }
    private static Map.Entry<Integer,Integer> getSpecificValue_1(List<Map.Entry<Integer,Integer>> list, Integer key){
        for(Map.Entry<Integer,Integer> entry:list){
            if(entry.getKey().equals(key)){
                return entry;
            }
        }
        return null;
    }
    private static List<Map.Entry<String,Integer>> shortMethodNamesTotalEva(String basePath) throws FileNotFoundException {
        String methodNameTokens = basePath + "parsedMethodNameTokens.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodNameTokens);
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
        System.out.println(methodNameTokensNumberAndNumber.keySet().size());
        List<Map.Entry<String,Integer>>list =  Utils.sortMap(methodNameTokensNumberAndNumber);
        System.out.println(list.subList(0,100));
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
        String methodNameTokens = basePath + "correct.txt";
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
        String methodNameTokens = basePath + "parsedMethodNameTokens.txt";
//        String methodNameTokens = basePath + "TrainingData\\ParsedMethodNameTokens_1.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodNameTokens);
        System.out.println(lines.size());
        HashMap<Integer,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        for(String line:lines){
            String [] splitArray = line.split(" ");
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

}
