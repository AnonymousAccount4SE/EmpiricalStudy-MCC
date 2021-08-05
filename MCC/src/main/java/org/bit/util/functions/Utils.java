/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/11
 */

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Utils {
    public static void main(String [] args) throws FileNotFoundException {
//        String filePath = "E:\\BIT\\BadMethodName\\NegativeItemSet\\new_result.txt";
//        getProjectItems(filePath);
//        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\originalData\\";
//        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TestData\\";
//        String filePath = basePath + "inconsistent.txt";

//        String basePath = "E:\\Workspace\\ICSE2020TrainingData\\";
//        String basePath = "E:\\Workspace\\ICSE2020\\TrainingData\\";
//        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\RenamedMethods\\";
        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
//        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\1_1\\consistent\\";
//        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\1_1\\inconsistent\\";
//        getProjectItems(filePath);
//        getUniqueClass(basePath);
//        getOriginalDataPath(basePath);
//        getRepairedVersion(basePath);

//        deleteGetFromFile(basePath);
        
//        getAllRenamedMethods(basePath);
        getGetInTrainingData(basePath);
    }

    private static void getGetInTrainingData(String basePath) throws FileNotFoundException {
        String  methodNamesPath = basePath + "methodNames.txt";
        int cnt = 0;
        ArrayList<String> methodNamesList = ParserMethodNameMain.getLines(methodNamesPath);
        for(String methodName:methodNamesList){
            if(methodName.startsWith("get")){
                cnt++;
            }
        }
        System.out.println(cnt);

    }

    private static void getAllRenamedMethods(String basePath) throws FileNotFoundException {
        File[] projectList = new File(basePath).listFiles();
        int cnt = 0;
        for(File f:projectList){
            String methodTokenPath = f.getAbsolutePath() + "\\ActualRenamed\\MethodTokens.txt";
            if(f.getAbsolutePath().endsWith(".txt")) continue;
            System.out.println(methodTokenPath);

            ArrayList<String> lines = ParserMethodNameMain.getLines(methodTokenPath);
            cnt += lines.size();
        }
        System.out.println(cnt);
    }

    private static void deleteGetFromFile(String basePath) throws FileNotFoundException {
//        String nameTokensFile = basePath + "parsedMethodNameTokens.txt";
//        String contextTokensFile = basePath + "parsedMethodContextTokens.txt";
        String nameTokensFile = basePath + "ParsedMethodNameTokens_1.txt";
        String contextTokensFile = basePath + "ParsedMethodContextTokens_1.txt";
        ArrayList<String> nameTokens = ParserMethodNameMain.getLines(nameTokensFile);
        ArrayList<String> contextTokens = ParserMethodNameMain.getLines(contextTokensFile);
        ArrayList<Integer> omitIndexes = new ArrayList<>();
        StringBuilder namesb = new StringBuilder();
        StringBuilder contextsb = new StringBuilder();
        int i = 0;
        int cnt = 0;
        for(String nameToken:nameTokens){
            if(nameToken.startsWith("get")){
                omitIndexes.add(i);
                cnt++;
            }
            else{
                namesb.append(nameToken+"\n");
            }
            i++;
        }
//        for(int j = 0; j< contextTokens.size();j++){
//            String contextToken = contextTokens.get(j);
//            if(omitIndexes.contains(j)){
//
//            }
//            else{
//                contextsb.append(contextToken+"\n");
//            }
//
//        }
        System.out.println(cnt*1.0/ nameTokens.size());
//        FileHelper.outputToFile(basePath + "parsedMethodNameTokens_filterGet.txt",namesb,false);
//        FileHelper.outputToFile(basePath + "parsedMethodContextTokens_filterGet.txt",contextsb,false);

    }

    private static void getRepairedVersion(String basePath) throws FileNotFoundException {
        String filePath = basePath + "ParsedMethodNames.txt";
        ArrayList<String> namesList = ParserMethodNameMain.getLines(filePath);
        for(String name:namesList){
            System.out.println(name.split("@")[1]);
        }
    }

    private static void getOriginalDataPath(String basePath) throws FileNotFoundException {
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

    private static void getUniqueClass(String basePath) throws FileNotFoundException {
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

    public static void getProjectItems(String filePath) throws FileNotFoundException {
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
