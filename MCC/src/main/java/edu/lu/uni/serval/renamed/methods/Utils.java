/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package edu.lu.uni.serval.renamed.methods;/*
 *   @author Michael
 *   @create 2021/1/11
 */

import edu.lu.uni.serval.utils.FileHelper;

import java.io.IOException;
import java.util.*;

public class Utils {
    public static void main(String [] args) throws IOException {
//        String filePath = "E:\\BIT\\BadMethodName\\NegativeItemSet\\new_result.txt";
//        getProjectItems(filePath);
//        String basePath = "/home1/Michael/NewTestData/ICSE2020originalDataSet/";
//        String basePath = "/home1/michael/BadMethodName/Training/TrainingData/";
//        String basePath = "/home1/michael/BadMethodName/debug-method-name-master/Data/TrainingData/TrainingMethodInfo.txt/";
//        String basePath = "/home1/michael/BadMethodName/debug-method-name-master/Data/TrainingData/TrainingMethodInfo.txt/";
//        String basePath = "/home1/michael/BadMethodName/debug-method-name-master/Data/TestData/";
//        String basePath = "/home1/michael/BadMethodName/Training/TestingData/InconsistentMethods/";
//        String basePath = "/home1/michael/BadMethodName/Output_TestingRepos/InconsistentMethods/ICSE2020TestData/Balanced/";
//        String basePath = "/home1/michael/BadMethodName/Training/TestingData/InconsistentMethods/";
        String basePath = "/home1/michael/BadMethodName/Training/TestingData/ConsistentMethods/";
//        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\originalData\\";
//        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TestData\\";
//        String filePath = basePath + "inconsistent.txt";

//        getProjectItems(filePath);
//        getUniqueClass(basePath);
//        getOriginalDataPath(basePath);
//        getOriginalData(basePath);
//        getMethodNames(basePath);
//        getConsistentMethodNames(basePath);
//        writeZerosAndOnes(basePath);
        getConsistentMethodNames1(basePath);
    }

    private static void getConsistentMethodNames1(String basePath) throws IOException {
        String parsedMethodNamesFile = basePath + "ParsedMethodNames.txt";
        ArrayList<String> parsedMethodNames = ParserMethodNameMain.getLines(parsedMethodNamesFile);
        StringBuilder sb = new StringBuilder();
        for(String line:parsedMethodNames){
            String conMN = line.substring(line.indexOf("@")+1).replaceAll(","," ");
//            System.out.println(conMN);
            sb.append(conMN+"\n");
        }
        FileHelper.outputToFile(basePath + "afterRenamedNames.txt",sb,false);
    }

    private static void writeZerosAndOnes(String basePath) {
        String file = basePath + "TestLabels_new.txt";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<1432;i++)
            sb.append("0"+"\n");
        for(int i=0;i<1432;i++)
            sb.append("1"+"\n");
        FileHelper.outputToFile(file,sb,false);
    }

    private static void getConsistentMethodNames(String basePath) throws IOException {
        String methodNamePath = basePath + "TestMethodNames.txt";
        String labelPath = basePath + "TestLabels.txt";
        ArrayList<String> methodNames = ParserMethodNameMain.getLines(methodNamePath);
        ArrayList<String> labels = ParserMethodNameMain.getLines(labelPath);
        StringBuilder consistentMethodNames = new StringBuilder();
        int cnt = 0;
        for(int i = 0;i< methodNames.size();i++){
            String methodName = methodNames.get(i);
            String label = labels.get(i);
            if(label.equals("1")){
                consistentMethodNames.append(methodName+"\n");
                cnt++;
            }
        }
        System.out.println(cnt);
        FileHelper.outputToFile(basePath+"consistentMethodNames.txt",consistentMethodNames,false);

    }

    private static void getMethodNames(String trainingMethodInfoFile) throws IOException {
//        String file = trainingMethodInfoFile + "TrainingMethodInfo_filtered.txt";
        String file = trainingMethodInfoFile + "TrainingMethodInfo.txt";
        ArrayList<String> trainingMethodInfoList = ParserMethodNameMain.getLines(file);
        ArrayList<String> methodNameList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(String info:trainingMethodInfoList){
            String methodNames = info.substring(info.lastIndexOf("@")+1).replaceAll(","," ");
            System.out.println(methodNames);
            stringBuilder.append(methodNames+"\n");
            methodNameList.add(methodNames);
        }
        System.out.println(methodNameList.size());
        FileHelper.outputToFile(trainingMethodInfoFile+"methodNames.txt",stringBuilder,false);
    }

    private static void getOriginalData(String basePath) throws IOException {
        String testMethodInfo = basePath + "TestMethodInfo.txt";
        String originalDataResult = basePath + "originalDataResult.txt";
        ArrayList<String> testMethodInfoList = ParserMethodNameMain.getLines(testMethodInfo);
        ArrayList<String> originalDataResultList = ParserMethodNameMain.getLines(originalDataResult);
        int cnt = 0;
        for(String testMethodInfoLine:testMethodInfoList){
            String [] itemList = testMethodInfoLine.split(":");
            String packageName = itemList[1];
            String className = itemList[2];
            String methodName = itemList[3];
            String parameter = itemList[4];
            String returnValue = itemList[5];
//            String [] parameters = parameter.split("\\+");
            String item = "";
//            if(parameters.length!=1){
//                String parameterName= "";
//                for(int i=0;i<parameters.length;i++){
//                    if(i%2!=0)
//                        parameterName = parameterName + parameters[i] + " ";
//                }
//                parameterName = parameterName.substring(0,parameterName.length()-1);
////                System.out.println(parameter+":" + parameterName);
//                item = packageName + ":" + className + ":" + methodName + ":" + returnValue + " " +parameterName;
//            }
//            else{
                item = packageName + ":" + className + ":" + methodName;
//            }

//            System.out.println(item);
            for(String originalDataResultLine : originalDataResultList){
                if(originalDataResultLine.contains(item)){
                    System.out.println(item);
                    System.out.println(originalDataResultLine);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    private static void getOriginalDataPath(String basePath) throws IOException {
        String javaFilesPath = basePath + "JavaFiles.txt";
        String uniqueClassPath = basePath + "TestMethodInfo_uniqueClass.txt";
        ArrayList<String> uniqueClassList = ParserMethodNameMain.getLines(uniqueClassPath);
        ArrayList<String> javaFilesList = ParserMethodNameMain.getLines(javaFilesPath);
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(String uniqueClassLine : uniqueClassList){
            String projectName = uniqueClassLine.split(":")[0];
            String pathName = uniqueClassLine.split(":")[1]+".java";
            for(String javaFiles:javaFilesList){
                if(javaFiles.contains(projectName)&&javaFiles.contains(pathName)){
                    System.out.println(javaFiles);
                    sb.append(javaFiles+"\n");
                    cnt++;
                }
            }
        }
        FileHelper.outputToFile(basePath + "javaFilePaths.txt",sb,false);
        System.out.println(cnt);
    }

    private static void getUniqueClass(String basePath) throws IOException {
        String filePath = basePath + "TestMethodInfo_uniqueClass.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        HashSet<String> set = new HashSet<>();
        for(String line:lines){
            List<String> splitArray = Arrays.asList(line.split(":"));
            String className = splitArray.get(0)+":"+splitArray.get(1)+"/"+splitArray.get(2);
//            String className = splitArray.get(1)+":"+splitArray.get(2);
            set.add(className);
        }
        for(String s:set){
            System.out.println(s);
        }
        System.out.println(set.size());
    }

    public static void getProjectItems(String filePath) throws IOException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        HashMap<String,Integer> map = new HashMap<>();
        for(String line:lines){
            String projectName = line.split(":")[0];
            if(map.containsKey(projectName)){
                int value = map.get(projectName);
                map.put(projectName,value+1);
            }
            else{
                map.put(projectName,1);
            }
        }
        for(Map.Entry<String,Integer> entry:map.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
            System.out.println(entry.getKey());
        }
    }
    public static int getIndice(String result){
        int index = result.indexOf("index:");
//            System.out.println(index);
        int tabIndex = result.indexOf(" ");
//            System.out.println(tabIndex);
//            System.out.println(i);
        int indice = Integer.valueOf(result.substring(index+6,tabIndex));
        return indice;
    }
    public static String getFirstToken(String line){
        int leftSquareBracketsIndex = line.indexOf("[");
        int rightSquareBracketsIndex = line.indexOf("]");

        int firstCommaIndex = line.indexOf(",");
        String firstToken = "";
        if(firstCommaIndex==-1){
            firstToken = line.substring(leftSquareBracketsIndex+1,rightSquareBracketsIndex);
        }
        else{
            firstToken = line.substring(leftSquareBracketsIndex+1,firstCommaIndex);
        }
        return firstToken;
    }
    public static List<Map.Entry<String,Integer>> sortMap(HashMap<String,Integer> firstTokenAndNumber){
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(firstTokenAndNumber.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
//        System.out.println(list);
        return list;
    }
    public static List<Map.Entry<Integer,Integer>> sortMap_2(HashMap<Integer,Integer> firstTokenAndNumber){
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(firstTokenAndNumber.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
//        System.out.println(list);
        return list;
    }

    public static List<Map.Entry<Integer,Double>> sortMapByKey(HashMap<Integer,Double> firstTokenAndNumber){
        List<Map.Entry<Integer,Double>> list = new ArrayList<Map.Entry<Integer,Double>>(firstTokenAndNumber.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer,Double>>() {
            public int compare(Map.Entry<Integer,Double> o1,
                               Map.Entry<Integer,Double> o2) {
                return o1.getKey() - o2.getKey() ;
            }
        });
//        System.out.println(list);
        return list;
    }

    public static List<Map.Entry<Integer,Integer>> sortMap_1(HashMap<Integer,Integer> firstTokenAndNumber){
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(firstTokenAndNumber.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
//        System.out.println(list);
        return list;
    }
}
