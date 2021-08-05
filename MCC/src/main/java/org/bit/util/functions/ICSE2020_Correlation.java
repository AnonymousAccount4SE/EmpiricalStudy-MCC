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

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ICSE2020_Correlation {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\";

//        String basePath1 = "D:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\real\\PositiveItems\\TokensLT94\\";
//        String basePath1 = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\inconsistent\\";
        String trainingPath = "E:\\Workspace\\ICSE2020TrainingDataAllTokens\\TrainingData\\";

//        shortMethodTotalNamesCorrectEva(basePath1);
//        shortMethodTotalNamesCorrectEva(basePath1);

        // calculate frequency correlation

//        HashMap<String,Integer> trainingDataHashMap = shortMethodNamesTotalEva(trainingPath);
////        HashMap<String,Integer> correctDataHashMap = shortMethodNamesTotalNameCorrectEva(basePath1,"correct.txt");
//        HashMap<String,Integer> correctDataHashMap = shortMethodNamesTotalNameCorrectEva(basePath1,"allGeneratedCorrect_TrainingDataAllTokens.txt");
//        System.out.println(correctDataHashMap.size());
////        HashMap<String,Integer> consistentDataHashMap = shortMethodNamesTotalNameCorrectEva(basePath1,"parsedMethodNameTokens.txt");
//        HashMap<String,Integer> consistentDataHashMap = shortMethodNamesTotalNameCorrectEva(basePath1,"allMethodNameTokens.txt");
//        System.out.println(consistentDataHashMap.size());
////        System.out.println(correctDataHashMap);
////        System.out.println(consistentDataHashMap);
//
//        List<HashMap<Integer,Double>> frequencyMap = evaluateFrequencyCorrelation(trainingDataHashMap,correctDataHashMap,consistentDataHashMap);
//        System.out.println(frequencyMap.size());
//        frequencyCorrelation(frequencyMap);


        // calculate method name complexity correlation
        // calculate method body complexity correlation

//        HashMap<Integer,Integer> trainingDataHashMap = MethodContextCorrectEva(trainingPath,"parsedMethodContextTokens.txt");

        HashMap<Integer,Integer> correctDataHashMap = MethodContextCorrectEva(basePath1,"allGeneratedCorrectContext_TrainingDataAllTokens.txt");
        HashMap<Integer,Integer> consistentDataHashMap = MethodContextCorrectEva(basePath1,"allMethodContextTokens.txt");

        List<HashMap<Integer,Double>> frequencyMap = evaluateContextCorrelation(correctDataHashMap,consistentDataHashMap);
        contextCorrelation(frequencyMap);


        //calculate method body complexity correlation <ASTNODE>
//        String basePath1 = "E:\\Workspace\\NewTestDataSet\\ICSE2020\\real\\PositiveItems\\TokensLT94\\";
//        String basePath2 = "E:\\Workspace\\NewTestDataSet\\ICSE2019\\real\\PositiveItems\\TokensLT94\\";
////        getInconsistentIndexAndASTNodeNumber(basePath1,basePath2);
//        ArrayList<Integer> inconsistentASTNodeNumber = getASTNodeNumberMap(basePath2);
//        System.out.println(inconsistentASTNodeNumber.size());
//        String basePath3 = "E:\\Workspace\\NewTestDataSet\\ICSE2020\\real\\NegativeItems\\TokensLT94\\";
//        String basePath4 = "E:\\Workspace\\NewTestDataSet\\ICSE2019\\real\\NegativeItems\\TokensLT94\\";
////        getConsistentIndexAndASTNodeNumber(basePath3,basePath4);
//        ArrayList<Integer> consistentASTNodeNumber = getConsistentASTNodeNumber(basePath3,basePath4);
////        System.out.println(consistentASTNodeNumber.size());
//        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\";
//
//        HashMap<Integer,Integer> allDataHashMap = getConsistentDataHashMap(inconsistentASTNodeNumber,consistentASTNodeNumber);
//
//        ArrayList<Integer> arr = getSuggestedRightIndex(inconsistentASTNodeNumber,consistentASTNodeNumber,basePath);
//
//        HashMap<Integer,Integer> correctDataHashMap = getCorrectDataHashMap(inconsistentASTNodeNumber,consistentASTNodeNumber,arr);
////        HashMap<Integer,Integer> trainingDataHashMap = MethodContextCorrectEva(trainingPath,"parsedMethodContextTokens.txt");
//
////        HashMap<Integer,Integer> correctDataHashMap = MethodContextCorrectEva(basePath1,"allGeneratedCorrectContext.txt");
////        HashMap<Integer,Integer> consistentDataHashMap = MethodContextCorrectEva(basePath1,"allMethodContextTokens.txt");
//
//        List<HashMap<Integer,Double>> frequencyMap = evaluateContextCorrelation(correctDataHashMap,allDataHashMap);
//        contextCorrelation(frequencyMap);







    }

    private static HashMap<Integer,Integer> getCorrectDataHashMap(ArrayList<Integer> inconsistentASTNodeNumber, ArrayList<Integer> consistentASTNodeNumber, ArrayList<Integer> arr) {
        HashMap<Integer,Integer> ASTNodeMap = new HashMap<>();
        for(int index:arr){
            if(index<3159){
                int ASTNodeNumber = inconsistentASTNodeNumber.get(index);
                if(ASTNodeMap.get(ASTNodeNumber)==null){
                    ASTNodeMap.put(ASTNodeNumber,1);
                }
                else{
                    int num = ASTNodeMap.get(ASTNodeNumber);
                    ASTNodeMap.put(ASTNodeNumber,num+1);
                }
            }
            else{
                int ASTNodeNumber = consistentASTNodeNumber.get(index-3159);
                if(ASTNodeMap.get(ASTNodeNumber)==null){
                    ASTNodeMap.put(ASTNodeNumber,1);
                }
                else{
                    int num = ASTNodeMap.get(ASTNodeNumber);
                    ASTNodeMap.put(ASTNodeNumber,num+1);
                }
            }
        }
        return ASTNodeMap;
    }

    private static ArrayList<Integer> getSuggestedRightIndex(ArrayList<Integer> inconsistentASTNodeNumber, ArrayList<Integer> consistentASTNodeNumber, String basePath) throws FileNotFoundException {
        ArrayList<String> results = ParserMethodNameMain.getLines(basePath + "allResult.txt");
        ArrayList<String> testMethods = ParserMethodNameMain.getLines(basePath + "allMethodNameTokens.txt");
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0;i<results.size();i++){
            String resultLine = results.get(i);
//            System.out.println(resultLine);
            String nameTokensLine = testMethods.get(i);
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
            double sim = resultSet.size()*1.0 / sum_div2;
//            System.out.println(resultArray.size());
//            System.out.println(sum_div2);
//            System.out.println(sim);
            if(sim>0.94){
//                if(i>=3159){
                    arr.add(i);
//                }

            }
            else{
//                if(i<3159)
//                    arr.add(i);
//                rr.add(i);
            }
        }
        System.out.println(arr.size());
        return arr;
    }

    private static HashMap<Integer,Integer> getConsistentDataHashMap(ArrayList<Integer> inconsistentASTNodeNumber, ArrayList<Integer> consistentASTNodeNumber) {

        HashMap<Integer,Integer> ASTNodeMap = new HashMap<>();
                System.out.println(consistentASTNodeNumber);
        for(int i:consistentASTNodeNumber){
            if(ASTNodeMap.get(i)==null){
                ASTNodeMap.put(i,1);
            }
            else{
                int num = ASTNodeMap.get(i);
                ASTNodeMap.put(i,num+1);
            }
        }
        for(int i:inconsistentASTNodeNumber){
            if(ASTNodeMap.get(i)==null){
                ASTNodeMap.put(i,1);
            }
            else{
                int num = ASTNodeMap.get(i);
                ASTNodeMap.put(i,num+1);
            }
        }

        return ASTNodeMap;
    }

    private static ArrayList<Integer> getConsistentASTNodeNumber(String basePath1, String basePath2) throws FileNotFoundException {
        ArrayList<String> final_result = ParserMethodNameMain.getLines(basePath1 + "final_result.txt");
        ArrayList<String> methodInfoList = ParserMethodNameMain.getLines(basePath2 + "filteredSelectedMethodInfo_1.txt");
        ArrayList<String> methodTokenList = ParserMethodNameMain.getLines(basePath2 + "filteredSelectedMethodTokens_1.txt");
        HashMap<String, Integer> IdAndASTNodeNumber = new HashMap<>();
        for(int i = 0;i<methodInfoList.size();i++){
            String methodInfo = methodInfoList.get(i);
            String methodToken = methodTokenList.get(i);
            String [] splitArray = methodInfo.split(":");
            String id = splitArray[0] + splitArray[1] + splitArray[2] + splitArray[3];
            int ASTNodeNumber = methodToken.split(" ").length/2;
            IdAndASTNodeNumber.put(id,ASTNodeNumber);
        }
//        System.out.println(IdAndASTNodeNumber.size());
        ArrayList<Integer> ASTNodeNumberList = new ArrayList<>();
        for(String result:final_result){
            String [] splitArray = result.split(":");
            String id = splitArray[0] + splitArray[1] + splitArray[2] + splitArray[3];
//            System.out.println(id + ":" + IdAndASTNodeNumber.get(id));
            if(IdAndASTNodeNumber.get(id)==null)
                ASTNodeNumberList.add(2);
            else{
                ASTNodeNumberList.add(IdAndASTNodeNumber.get(id));
            }
        }
        System.out.println(ASTNodeNumberList.size());
//        System.out.println(ASTNodeNumberList);
        return ASTNodeNumberList;


    }

    private static void getConsistentIndexAndASTNodeNumber(String basePath1, String basePath2) throws FileNotFoundException {
//        ArrayList<String> filteredMethodInfo = ParserMethodNameMain.getLines(basePath2 + "filteredSelectedMethodInfo_1.txt");
//        StringBuilder sb1 = new StringBuilder();
//        for(String methodInfo : filteredMethodInfo){
//            String [] splitArray = methodInfo.split(":");
//            String id = splitArray[0] + splitArray[1] + splitArray[2] + splitArray[3];
//            sb1.append(id + "\n");
//        }
//        FileHelper.outputToFile(basePath2 + "new_filteredSelectedMethodInfo_1.txt", sb1, false);
        ArrayList<String> filteredMethodInfo = ParserMethodNameMain.getLines(basePath1 + "final_result.txt");
        StringBuilder sb1 = new StringBuilder();
        for(String methodInfo : filteredMethodInfo){
            String [] splitArray = methodInfo.split(":");
            String id = splitArray[0] + splitArray[1] + splitArray[2] + splitArray[3];
            sb1.append(id + "\n");
        }
//        FileHelper.outputToFile(basePath1 + "new_final_result.txt", sb1, false);
    }

    private static void getInconsistentIndexAndASTNodeNumber(String basePath1,String basePath2) throws FileNotFoundException {
        ArrayList<Integer> filteredIdx = filterMainTestExampleTemplateAndEmpty(basePath1);
//        System.out.println(filteredIdx);
        ArrayList<String> methodInfoList = ParserMethodNameMain.getLines(basePath2 + "MethodInfos.txt");
        StringBuilder newLine = new StringBuilder();

//        System.out.println(methodInfoList.size());
        for(int i = 0; i<methodInfoList.size();i++){
            String methodInfo = methodInfoList.get(i);
            if(!filteredIdx.contains(i)){
//                System.out.println(methodInfo);
            }
            else{
                newLine.append(methodInfo+"\n");
            }
        }
        FileHelper.outputToFile(basePath2 + "new_methodInfos.txt", newLine, false);
    }



    private static ArrayList<Integer> getASTNodeNumberMap(String basePath1) throws FileNotFoundException {
        ArrayList<String> methodInfoList = ParserMethodNameMain.getLines(basePath1+"new_methodInfos.txt");
        ArrayList<Integer> ASTNodeNumberList = new ArrayList<>();
        for(String methodInfo:methodInfoList){
//            String [] splitArray = methodInfo.split(":");
//            String id = splitArray[0] + splitArray[1] + splitArray[2] + splitArray[3];
            String bodyTokens = methodInfo.split(":")[6];
            int ASTNodeNumber = bodyTokens.split(" ").length/2;
//            System.out.println(ASTNodeNumber);
            ASTNodeNumberList.add(ASTNodeNumber);
        }
//        System.out.println(ASTNodeNumberList.size());
        return ASTNodeNumberList;
    }
    public static ArrayList<Integer> filterMainTestExampleTemplateAndEmpty(String basePath) throws FileNotFoundException {
        String resultPath = basePath + "result.txt";
        FileInputStream fileInputStream = new FileInputStream(resultPath);
        Scanner scanner1 = new Scanner(fileInputStream);
        StringBuilder newLine = new StringBuilder();
        ArrayList<Integer> filteredIdx = new ArrayList<>();
        int idx = 0;
        int cnt1 = 0, cnt2 = 0, cnt3 = 0;
        while (scanner1.hasNext()) {
            String bodyTokens = "";
            String line = scanner1.nextLine();
            String[] splitArray = line.split(":");
            if (splitArray.length == 6) {
                bodyTokens = splitArray[5];
                String className = splitArray[2];
                String packageName = splitArray[1];
                String methodName = splitArray[3];
                String parAndReturnType = splitArray[4];
                String regex = "[^a-zA-Z]";
                Matcher m = Pattern.compile(regex).matcher(methodName);
                // filter out the method names without alphabetic letters
                if (m.matches()) {
                    System.out.println("-----------------" + line);
                    cnt1++;
                    continue;
                }
                if (methodName.equalsIgnoreCase("Main") || methodName.equalsIgnoreCase("test")
                        || packageName.contains("sample") || packageName.contains("example")
                        || packageName.contains("template") || packageName.equals("null")) {
                    System.out.println(line);
                    cnt2++;
                    continue;
                }
                filteredIdx.add(idx);
                newLine.append(line + "\n");
            } else {
                cnt3++;
            }
            idx++;

        }
        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);
        System.out.println(String.valueOf(cnt1 + cnt2 + cnt3));
return filteredIdx;
//        FileHelper.outputToFile(basePath + "new_result.txt", newLine, false);
    }
    private static HashMap<Integer,Integer> MethodContextCorrectEva(String basePath, String fileName) throws FileNotFoundException {
        String methodContextTokens = basePath + fileName;
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodContextTokens);
//        System.out.println(lines.size());
        HashMap<Integer,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        for(String line:lines){
            int contextSize = line.split(" ").length;
//            if(contextSize>500) {
////                System.out.println(line);
//            }
//            else{
//
//            }
            if(methodNameTokensNumberAndNumber.get(contextSize)==null){
                methodNameTokensNumberAndNumber.put(contextSize,1);
            }
            else{
                int num = methodNameTokensNumberAndNumber.get(contextSize);
                methodNameTokensNumberAndNumber.put(contextSize,num+1);
            }
        }
//        System.out.println(methodNameTokensNumberAndNumber.keySet().size());
        List<Map.Entry<Integer,Integer>>list =  Utils.sortMap_2(methodNameTokensNumberAndNumber);
//        for(Map.Entry<Integer,Integer> s:list){
//            System.out.println(s.getKey()+":"+s.getValue());
//        }
//        System.out.println(list.subList(0,100));
        return methodNameTokensNumberAndNumber;
    }

    private static void contextCorrelation(List<HashMap<Integer,Double>> frequencyHashMapList) {
        int cnt1 = 0,cnt2=0,cnt3=0,cnt4=0,cnt5=0,cnt6=0, cnt7=0,cnt_0=0,cnt_1=0,cnt_2=0,cnt_3=0,cnt_4=0;
        double zoneSum1=0;double zoneSum2=0;double zoneSum3=0;double zoneSum4=0;double zoneSum5=0;double zoneSum6=0;
        double zoneSum7=0;double zoneSum_0=0;double zoneSum_1=0,zoneSum_2=0,zoneSum_3=0,zoneSum_4=0;
        for(HashMap<Integer,Double> map:frequencyHashMapList){
            Map.Entry<Integer,Double> entry = map.entrySet().iterator().next();
            double successRatio = entry.getValue();
            int frequencyInTrainingData = entry.getKey();

//            if(frequencyInTrainingData==0){
//                zoneSum_0 += successRatio;
//                cnt_0++;
//            }
            if(frequencyInTrainingData<=5&&frequencyInTrainingData>2){
                zoneSum_1 += successRatio;
                cnt_1++;
            }
            else if(frequencyInTrainingData<=10&&frequencyInTrainingData>5){
                zoneSum_2 += successRatio;
                cnt_2++;
            }
            else if(frequencyInTrainingData<=20&&frequencyInTrainingData>10){
                zoneSum_3 += successRatio;
                cnt_3++;
            }
            else if(frequencyInTrainingData<=40&&frequencyInTrainingData>20){
                zoneSum_4 += successRatio;
                cnt_4++;
            }
            else if(frequencyInTrainingData<=60&&frequencyInTrainingData>40){
                zoneSum1 += successRatio;
                cnt1++;
            }
            else if(frequencyInTrainingData<=80&&frequencyInTrainingData>60){
                zoneSum2 += successRatio;
                cnt2++;
            }
//            else if(frequencyInTrainingData<=100&&frequencyInTrainingData>80){
//                zoneSum3 += successRatio;
//                cnt3++;
//            }
//            else if(frequencyInTrainingData<=3000&&frequencyInTrainingData>2000){
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
            else if(frequencyInTrainingData>80){
                zoneSum7 += successRatio;
                cnt7++;
            }
            else{
                System.out.println("error");
            }
        }
//        System.out.println(zoneSum_0/cnt_0+":"+cnt_0);
        System.out.println(zoneSum_1/cnt_1+":"+cnt_1);
        System.out.println(zoneSum_2/cnt_2+":"+cnt_2);
        System.out.println(zoneSum_3/cnt_3+":"+cnt_3);
        System.out.println(zoneSum_4/cnt_4+":"+cnt_4);
        System.out.println(zoneSum1/cnt1+":"+cnt1);
        System.out.println(zoneSum2/cnt2+":"+cnt2);
//        System.out.println(zoneSum3/cnt3+":"+cnt3);
//        System.out.println(zoneSum4/cnt4+":"+cnt4);
//        System.out.println(zoneSum5/cnt5+":"+cnt5);
//        System.out.println(zoneSum6/cnt6+":"+cnt6);
        System.out.println(zoneSum7/cnt7+":"+cnt7);

    }

    private static void frequencyCorrelation(List<HashMap<Integer,Double>> frequencyHashMapList) {
        int cnt1 = 0,cnt2=0,cnt3=0,cnt4=0,cnt5=0,cnt6=0, cnt7=0,cnt_0=0,cnt_1=0,cnt_2=0,cnt_3=0,cnt_4=0;
        double zoneSum1=0;double zoneSum2=0;double zoneSum3=0;double zoneSum4=0;double zoneSum5=0;double zoneSum6=0;
        double zoneSum7=0;double zoneSum_0=0;double zoneSum_1=0,zoneSum_2=0,zoneSum_3=0,zoneSum_4=0;
        for(HashMap<Integer,Double> map:frequencyHashMapList){
            Map.Entry<Integer,Double> entry = map.entrySet().iterator().next();
            double successRatio = entry.getValue();
            int frequencyInTrainingData = entry.getKey();

            if(frequencyInTrainingData==0){
                zoneSum_0 += successRatio;
                cnt_0++;
            }

            else if(frequencyInTrainingData==1){
                zoneSum_1 += successRatio;
                cnt_1++;
            }
            else if(frequencyInTrainingData<=3&&frequencyInTrainingData>1){
                zoneSum_2 += successRatio;
                cnt_2++;
            }
            else if(frequencyInTrainingData<=10&&frequencyInTrainingData>3){
                zoneSum_3 += successRatio;
                cnt_3++;
            }
            else if(frequencyInTrainingData<=30&&frequencyInTrainingData>10){
                zoneSum_4 += successRatio;
                cnt_4++;
            }
            else if(frequencyInTrainingData<=100&&frequencyInTrainingData>30){
                zoneSum1 += successRatio;
                cnt1++;
            }
            else if(frequencyInTrainingData<=400&&frequencyInTrainingData>100){
                zoneSum2 += successRatio;
                cnt2++;
            }
//            else if(frequencyInTrainingData<=2000&&frequencyInTrainingData>1000){
//                zoneSum3 += successRatio;
//                cnt3++;
//            }
//            else if(frequencyInTrainingData<=3000&&frequencyInTrainingData>2000){
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
            else if(frequencyInTrainingData>400){
                zoneSum7 += successRatio;
                cnt7++;
            }
            else{
                System.out.println("error");
            }
        }
        System.out.println(zoneSum_0/cnt_0+":"+cnt_0);
        System.out.println(zoneSum_1/cnt_1+":"+cnt_1);
        System.out.println(zoneSum_2/cnt_2+":"+cnt_2);
        System.out.println(zoneSum_3/cnt_3+":"+cnt_3);
        System.out.println(zoneSum_4/cnt_4+":"+cnt_4);
        System.out.println(zoneSum1/cnt1+":"+cnt1);
        System.out.println(zoneSum2/cnt2+":"+cnt2);
//        System.out.println(zoneSum3/cnt3+":"+cnt3);
//        System.out.println(zoneSum4/cnt4+":"+cnt4);
//        System.out.println(zoneSum5/cnt5+":"+cnt5);
//        System.out.println(zoneSum6/cnt6+":"+cnt6);
        System.out.println(zoneSum7/cnt7+":"+cnt7);

    }

    private static List<HashMap<Integer,Double>> evaluateContextCorrelation( HashMap<Integer,Integer>
                                                                        correctDataHashMap, HashMap<Integer,Integer> consistentDataHashMap) {

        ArrayList<Double> successRatioList = new ArrayList<>();
        ArrayList<Integer> tokenNumberList = new ArrayList<>();
        List<HashMap<Integer,Double>> mapList = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry:consistentDataHashMap.entrySet()){
            HashMap<Integer,Double> map =new HashMap<>();
            int contextSize = entry.getKey();
            int correctNumber = 0;
            if(correctDataHashMap.containsKey(contextSize)){
                correctNumber = correctDataHashMap.get(contextSize);
            }
            else{
                correctNumber=0;
            }
            int totalNumber = entry.getValue();
//            int totalNumber = 6776;
            Double successRatio = 1.0*correctNumber/totalNumber;
            successRatioList.add(successRatio);
//            int tokenNumber = methodName.split(" ").length;
            tokenNumberList.add(contextSize);
            map.put(contextSize,successRatio);
            System.out.println(contextSize+":"+successRatio+":"+correctNumber+":"+totalNumber);
            mapList.add(map);
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

        return mapList;


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

    private static List<HashMap<Integer,Double>> evaluateFrequencyCorrelation(HashMap<String,Integer> trainingDataHashMap,HashMap<String,Integer>
            correctDataHashMap, HashMap<String,Integer> consistentDataHashMap) {

        ArrayList<Double> successRatioList = new ArrayList<>();
        ArrayList<Integer> frequencyList = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        ArrayList<HashMap<Integer,Double>> frequencySRList = new ArrayList<>();
        for(Map.Entry<String,Integer> entry:consistentDataHashMap.entrySet()){
            String methodName = entry.getKey();
            HashMap<Integer,Double> map = new HashMap<>();
            int correctNumber = 0;
            if(correctDataHashMap.containsKey(methodName)){
                correctNumber = correctDataHashMap.get(methodName);
            }
            else{
                correctNumber = 0;
            }
            int totalNumber = entry.getValue();
//            int totalNumber = 6776;
            Double successRatio = 1.0*correctNumber/totalNumber;
            successRatioList.add(successRatio);
            int frequency=0;
            if(trainingDataHashMap.containsKey(methodName)){
                frequency = trainingDataHashMap.get(methodName);
            }
            else{
                frequency = 0;
            }
            frequencyList.add(frequency);
            String output = methodName+":"+frequency+":"+successRatio+":"+correctNumber+":"+totalNumber;
            System.out.println(output);
            sb.append(output + "\n");
            map.put(frequency,successRatio);
            frequencySRList.add(map);
        }
        FileHelper.outputToFile("E:\\Workspace\\Evaluation_new\\icse2020\\real\\frequencyCorrelation_icse2020.txt",sb,false);
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

        return frequencySRList;
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
    private static HashMap<String,Integer> shortMethodNamesTotalEva(String basePath) throws FileNotFoundException {
//        String methodNameTokens = basePath + "parsedMethodNameTokens.txt";
        String methodNameTokens = basePath + "ParsedMethodNameTokens_1.txt";
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
//        System.out.println(methodNameTokensNumberAndNumber.keySet().size());
        List<Map.Entry<String,Integer>>list =  Utils.sortMap(methodNameTokensNumberAndNumber);
//        System.out.println(list.subList(0,100));
        return methodNameTokensNumberAndNumber;
    }

    private static HashMap<String,Integer> shortMethodNamesTotalNameCorrectEva(String basePath,String fileName) throws FileNotFoundException {
//        String methodNameTokens = basePath + "correct.txt";
//        String methodNameTokens = basePath + "false.txt";
        String methodNameTokens = basePath + fileName;
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodNameTokens);
//        System.out.println(lines.size());
        HashMap<String,Integer> methodNameTokensNumberAndNumber = new HashMap<>();
        if(fileName.equals("allCorrect.txt")){
            System.out.println("allCorrect");
            for(String line:lines){
                String name = line.split(":")[1];
                if(methodNameTokensNumberAndNumber.get(name)==null){
                    methodNameTokensNumberAndNumber.put(name,1);
                }
                else{
                    int num = methodNameTokensNumberAndNumber.get(name);
                    methodNameTokensNumberAndNumber.put(name,num+1);
                }
            }
        }
        else if(fileName.equals("correct.txt")){
            System.out.println("correct");
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
        }
        else if(fileName.equals("allGeneratedCorrect_TrainingDataAllTokens.txt")){
            System.out.println("allGeneratedCorrect_TrainingDataAllTokens");
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
        }
        else if (fileName.equals("allMethodNameTokens.txt")){
            System.out.println("MethodNameTokens");
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
        }

//        System.out.println(methodNameTokensNumberAndNumber.keySet().size());
        List<Map.Entry<String,Integer>>list =  Utils.sortMap(methodNameTokensNumberAndNumber);
//        for(Map.Entry<String,Integer> s:list){
//            System.out.println(s.getKey()+":"+s.getValue());
//        }
//        System.out.println(list.subList(0,100));
        return methodNameTokensNumberAndNumber;
    }

    private static void shortMethodTotalNamesCorrectEva(String basePath) throws FileNotFoundException {
//        String methodNameTokens = basePath + "correct.txt";
//        String methodNameTokens = basePath + "false.txt";
        String methodNameTokens = basePath + "parsedMethodNameTokens.txt";
//        String methodNameTokens = basePath + "result.txt";
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
        List<Map.Entry<String,Integer>>list =  Utils.sortMap(methodNameTokensNumberAndNumber);
        System.out.println(list);
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
