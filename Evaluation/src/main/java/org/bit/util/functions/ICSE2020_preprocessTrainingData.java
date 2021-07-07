/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/9
 */

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.javaparser.ParserMain;
import org.bit.util.javaparser.ParserMethodNameMain;
import org.bit.util.javaparser.util.JavaParserUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ICSE2020_preprocessTrainingData {

    public static String projectPath = "D:/BIT/BadMethodName/debug-method-name-master/Data/GoogleJavaRepos/";
    public static List<String> list = new ArrayList<String>();
    public static void main(String [] args) throws FileNotFoundException {
        String basePath = "E:/Workspace/icse2020/TrainingData/";

        //filter main example non-alphabet
//        filterMainTestExampleTemplateAndEmpty(basePath);
//
//        //filter test data info
//        String testDataSetPath = "D:\\BIT\\BadMethodName\\icse2020\\TestMethodInfo.txt";
//        String trainingDataSetPath = basePath + "new_result.txt";
//        filterTestDataSet(testDataSetPath,trainingDataSetPath);
//
//        //OutputTokens
//        ParserMethodNameMain.outputTokens(basePath);
//        //tokenization
//        ParserMethodNameMain.parseMethodNameTokens(basePath);
//        ParserMethodNameMain.parseMethodContextTokens(basePath);

//        filterExtraMain(basePath);
//        RemoveDuplicates.removeDuplicates(basePath);

//        filterTokensGT94FromICSE2020(basePath);
    }
    public static void filterTokensGT94FromICSE2020(String basePath) throws FileNotFoundException {
        String final_result = basePath + "final_result.txt";
        String TrainingMethodInfo = basePath + "TrainingMethodInfo.txt";
        ArrayList<String> final_result_list = ParserMethodNameMain.getLines(final_result);
        ArrayList<String> TrainingMethodInfoList = ParserMethodNameMain.getLines(TrainingMethodInfo);
        ArrayList<String> newList = new ArrayList<>();
        HashSet<String> ids = new HashSet<>();
        StringBuilder newStringBuffer = new StringBuilder();

        for(String s:TrainingMethodInfoList){
            String [] splitArray = s.split(":");
            String id = splitArray[0] + ":" + splitArray[1] + ":" + splitArray[2] + ":" + splitArray[3];
            ids.add(id);
        }

        System.out.println(ids.size());
        int cnt = 0;
        for(String s:final_result_list){
            String [] splitArray = s.split(":");
            String id = splitArray[0] + ":" + splitArray[1] + ":" + splitArray[2] + ":" + splitArray[3];
            if(ids.contains(id)){
                newList.add(s);
                newStringBuffer.append(s+"\n");
            }
            if(cnt%5000==0){
                FileHelper.outputToFile(basePath + "TrainingData.txt",newStringBuffer,true);
                newStringBuffer.setLength(0);
            }
            cnt++;
        }
        System.out.println(newList.size());
        FileHelper.outputToFile(basePath + "TrainingData.txt",newStringBuffer,true);

    }
    private static void filterExtraMain(String basePath) throws FileNotFoundException {
        String nameTokens = basePath + "ParsedMethodNameTokens.txt";
        String contextTokens = basePath + "ParsedMethodContextTokens.txt";
        ArrayList<String> nameTokenList = ParserMethodNameMain.getLines(nameTokens);
        ArrayList<String> contextTokenList = ParserMethodNameMain.getLines(contextTokens);
        System.out.println(nameTokenList.size());
        System.out.println(contextTokenList.size());
        StringBuilder namesb = new StringBuilder();
        StringBuilder contextsb = new StringBuilder();
        int cnt =0 ;
        for(int i=0;i<nameTokenList.size();i++){
            String name = nameTokenList.get(i);
            if(name.equalsIgnoreCase("main")||name.equalsIgnoreCase("test")){
                System.out.println(name);
            }
            else{
                namesb.append(name+"\n");
                contextsb.append(contextTokenList.get(i)+"\n");
                if(cnt%2000==0) {
                    FileHelper.outputToFile(basePath + "ParsedMethodNameTokens_1.txt", namesb, true);
                    FileHelper.outputToFile(basePath + "ParsedMethodContextTokens_1.txt", contextsb, true);
                    namesb.setLength(0);
                    contextsb.setLength(0);
                }
                cnt++;
            }

        }
        FileHelper.outputToFile(basePath + "ParsedMethodNameTokens_1.txt",namesb,true);
        FileHelper.outputToFile(basePath + "ParsedMethodContextTokens_1.txt",contextsb,true);

    }


    private static void filterInconsistentSampleFromConsistentOnes(String consistentFilePath,String inconsistentFilePath) throws FileNotFoundException {
        ArrayList<String> inconsistentList = ParserMethodNameMain.getLines(inconsistentFilePath);
        ArrayList<String> consistentList = ParserMethodNameMain.getLines(consistentFilePath);
        ArrayList<String> inconsistentPackageNameList = new ArrayList<>();
        StringBuilder finalResult = new StringBuilder();
        for(String line:inconsistentList){
            String [] splitArray = line.split(":");
            String packageClassMethodName  = splitArray[1]+":"+splitArray[2]+":"+splitArray[3];
            inconsistentPackageNameList.add(packageClassMethodName);
        }
        int cnt =0;
        for(String line:consistentList){
            String [] splitArray = line.split(":");
            String packageClassMethodName  = splitArray[1]+":"+splitArray[2]+":"+splitArray[3];
            if(inconsistentPackageNameList.contains(packageClassMethodName)){
                cnt ++;
                System.out.println(packageClassMethodName);
            }
            else{
                finalResult.append(line+"\n");
            }
        }
        System.out.println(cnt);
        FileHelper.outputToFile("E:\\Workspace\\icse2020\\TestData\\Positive\\final_result.txt",finalResult,false);
    }

    public static void filterTestDataSet(String testDataSetPath, String trainingDataSetPath) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(testDataSetPath);
        Scanner scanner = new Scanner(fileInputStream);
        HashSet<String> testDataLineSet = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String [] array = line.split(":");
            String newLine = array[0] + ":" + array[1] + ":" + array[2] + ":"+array[3];
            testDataLineSet.add(newLine);
        }
        System.out.println(testDataLineSet.size());
        FileInputStream fileInputStream1 = new FileInputStream(trainingDataSetPath);
        Scanner scanner1 = new Scanner(fileInputStream1);
        ArrayList<Integer> lineNumberList = new ArrayList<>();
        int cnt =0;
        while(scanner1.hasNext()){
            String trainingMethodInfo = scanner1.nextLine();
            String [] array = trainingMethodInfo.split(":");
            String newLine = array[0] + ":" + array[1] + ":" + array[2]+ ":"+array[3];
            if(testDataLineSet.contains(newLine)){
                System.out.println(newLine);
            }
            else{
                stringBuilder.append(trainingMethodInfo+"\n");
                if(cnt%10000==0){
                    FileHelper.outputToFile("E:\\Workspace\\icse2020\\TrainingData\\final_result.txt",stringBuilder,true);
                    stringBuilder.setLength(0);
                }
                cnt++;
            }
        }
        FileHelper.outputToFile("E:\\Workspace\\icse2020\\TrainingData\\final_result.txt",stringBuilder,true);
    }

    public static void filterMainTestExampleTemplateAndEmpty(String basePath) throws FileNotFoundException {
        String resultPath = basePath + "parser_result_new.txt";
        FileInputStream fileInputStream = new FileInputStream(resultPath);
        Scanner scanner1 = new Scanner(fileInputStream);
        StringBuilder newLine = new StringBuilder();
        int cnt1 =0,cnt2=0,cnt3=0;int cnt =0;
        while(scanner1.hasNext()){
            String bodyTokens = "";
            String line = scanner1.nextLine();
            String [] splitArray = line.split(":");
            if(splitArray.length==6){
                bodyTokens = splitArray[5];
                String className = splitArray[2];
                String packageName = splitArray[1];
                String methodName = splitArray[3];
                String parAndReturnType = splitArray[4];
                String regex="[^a-zA-Z]";
                Matcher m= Pattern.compile(regex).matcher(methodName);
                // filter out the method names without alphabetic letters
                if(m.matches()){
                    System.out.println("-----------------"+line);
                    cnt1++;
                    continue;
                }
                if(methodName.equalsIgnoreCase("main")||methodName.equalsIgnoreCase("test")||
                        packageName.contains("sample")||packageName.contains("example")
                        ||packageName.contains("template")||packageName.equals("null")){
                    System.out.println(line);
                    cnt2++;
                    continue;
                }
                newLine.append(line+"\n");
                if(cnt%10000==0){
                    FileHelper.outputToFile(basePath + "new_result.txt",newLine,true);
                    newLine.setLength(0);
                }
                cnt++;
            }
            else{
                cnt3++;
            }

        }
        FileHelper.outputToFile(basePath + "new_result.txt",newLine,true);

        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);
        System.out.println(String.valueOf(cnt1+cnt2+cnt3));

//        FileHelper.outputToFile(basePath + "new_result.txt",newLine,false);


//        StringBuilder newMethodName = new StringBuilder();
//        StringBuilder newMethodTokens = new StringBuilder();
//        // rewrite the filtered method context tokens
//        FileInputStream fileInputStream3 = new FileInputStream(methodContextTokenPath);
//        Scanner scanner3 = new Scanner(fileInputStream3);
//        int index1 = 1;
//        while(scanner3.hasNext()){
//            String line3 = scanner3.nextLine();
//            if(!filteredMethodNameLineList.contains(index1))
//                newMethodTokens.append(line3+"\n");
//            if(index1%10000==0){
//                FileHelper.outputToFile("D:\\BIT\\BadMethodName\\FilteredParsedContextTokens.txt",newMethodTokens,true);
//                newMethodTokens.setLength(0);
//            }
//            index1++;
//        }
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\FilteredParsedContextTokens.txt",newMethodTokens,true);
//
//        // rewrite the filtered method name tokens
//        FileInputStream fileInputStream4 = new FileInputStream(methodNamesTokenPath);
//        Scanner scanner4 = new Scanner(fileInputStream4);
//        index1 = 1;
//        while(scanner4.hasNext()){
//            String line4 = scanner4.nextLine();
//            if(!filteredMethodNameLineList.contains(index1))
//                newMethodName.append(line4+"\n");
//            if(index1%10000==0){
//                FileHelper.outputToFile("D:\\BIT\\BadMethodName\\FilteredParsedNamesTokens.txt",newMethodName,true);
//                newMethodName.setLength(0);
//            }
//            index1++;
//        }
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\FilteredParsedNamesTokens.txt",newMethodName,true);

    }


    public static void parseConsistentSample(String basePath) {
        int length = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(basePath + "JavaFiles_46.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(projectPath + str);
//                String projectName = str.split("/")[1];
                //        System.out.println(file.getAbsolutePath());

                length++;
                try {
                    List<String> parserResult = JavaParserUtil.parser(new File(projectPath + str));
//                    System.out.println(parserResult.size());
                    String projectName = str.substring(0,str.indexOf("/"));
                    System.out.println("PackageName:"+projectName);
                    BufferedWriter out = new BufferedWriter(new FileWriter(basePath + "result.txt", true));
                    for (String s : parserResult) {
                        out.write(projectName + ":" + s);
                    }
                    out.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
        }
    }
}
