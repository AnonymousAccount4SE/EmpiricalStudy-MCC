/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ICSE2019_filterFrom2020 {

    public static void main(String [] args) throws IOException {
//        String basePath = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\PositiveItems\\TokensLT94\\";
//        String resultPath = basePath + "result.txt";
//        filterPositiveItems(basePath,resultPath);

        // filter empty and main test,
//        String basePath = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\NegativeItems\\TokensLT94\\";
//        String resultPath = basePath + "result.txt";
//        filterNegativeItems_1(basePath,resultPath);

        // filter inconsistent samples
        String basePath = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\NegativeItems\\TokensLT94\\";
        String resultPath = basePath + "new_result.txt";
        filterNegativeItems_2(basePath,resultPath);

//        filterNegativeItems1(basePath,resultPath);

    }

    private static void filterNegativeItems_2(String basePath, String resultPath) throws FileNotFoundException {

        String newMethodInfoFile = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\NegativeItems\\TokensLT94\\MethodInfo_1.txt";
        ArrayList<String> newMethodInfoList = ParserMethodNameMain.getLines(newMethodInfoFile);

        int cnt = -1;
        int bodyCnt = 0;

//        String resultPath = basePath + "result.txt";
        FileInputStream fileInputStream = new FileInputStream(resultPath);
        Scanner scanner1 = new Scanner(fileInputStream);
        StringBuilder newLine = new StringBuilder();
        int cnt1 =0,cnt2=0,cnt3=0;

        ArrayList<Integer> omittedList = new ArrayList<>();
//        Set<Integer> omittedSet = new HashSet<Integer>();
        String consistentFilePath = resultPath;
        String inconsistentFilePath = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\PositiveItems\\TokensLT94\\new_result.txt";
        filterInconsistentSampleFromConsistentOnes(consistentFilePath,inconsistentFilePath,omittedList);



        System.out.println("omittedList.size():"+omittedList.size());
//        System.out.println("omittedSet.size():"+omittedSet.size());
        System.out.println("newMethodInfoList.size():"+newMethodInfoList.size());
        StringBuilder methodInfo1 = new StringBuilder();
//        System.out.println(omittedList);
//        FileHelper.outputToFile(basePath + "new_result.txt",newLine,false);
        ArrayList<String> filteredMethodInfo = new ArrayList<>();
        for(int i = 0; i < newMethodInfoList.size(); i++ ){
            if(omittedList.contains(i)){
//                System.out.println("omit");
            }
            else{
                filteredMethodInfo.add(newMethodInfoList.get(i));
                methodInfo1.append(newMethodInfoList.get(i)+"\n");
            }
        }
        System.out.println("filteredMethodInfo.size():"+filteredMethodInfo.size());
        FileHelper.outputToFile(basePath + "MethodInfo_2.txt",methodInfo1,false);


        StringBuilder newInfo = new StringBuilder();
        StringBuilder newToken = new StringBuilder();
        for(int i = 0; i < newMethodInfoList.size(); i++){
            if(omittedList.contains(i)){

            }
            else{
                String line =  newMethodInfoList.get(i);
                int lastSemicolon = line.lastIndexOf(":");
                String infoLine = line.substring(0,lastSemicolon);
                String tokenLine = line.substring(lastSemicolon+1);
                newInfo.append(infoLine+"\n");
                newToken.append(tokenLine+"\n");
            }
        }
        FileHelper.outputToFile("E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\NegativeItems\\TokensLT94\\filteredSelectedMethodInfo_1.txt", newInfo, false);
        FileHelper.outputToFile("E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\NegativeItems\\TokensLT94\\filteredSelectedMethodTokens_1.txt", newToken, false);

    }


    public static void filterNegativeItems_1(String basePath, String resultPath) throws FileNotFoundException {
        String MethodInfoFile = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\NegativeItems\\TokensLT94\\filteredSelectedMethodInfo.txt";
        ArrayList<String> methodInfoList = ParserMethodNameMain.getLines(MethodInfoFile);

        String MethodTokenFile = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\NegativeItems\\TokensLT94\\filteredSelectedMethodTokens.txt";
        ArrayList<String> methodTokensList = ParserMethodNameMain.getLines(MethodTokenFile);

        String newMethodInfoFile = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\NegativeItems\\TokensLT94\\MethodInfo1.txt";
        ArrayList<String> newMethodInfoList = ParserMethodNameMain.getLines(newMethodInfoFile);

        int cnt = -1;
        int bodyCnt = 0;

//        String resultPath = basePath + "result.txt";
        FileInputStream fileInputStream = new FileInputStream(resultPath);
        Scanner scanner1 = new Scanner(fileInputStream);
        StringBuilder newLine = new StringBuilder();
        int cnt1 =0,cnt2=0,cnt3=0;

        ArrayList<Integer> omittedList = new ArrayList<>();
        Set<Integer> omittedSet = new HashSet<Integer>();
        while(scanner1.hasNext()){
            cnt++;
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
                Matcher m = Pattern.compile(regex).matcher(methodName);
                // filter out the method names without alphabetic letters
                if(m.matches()){
                    System.out.println("-----------------"+line);
                    cnt1++;
                    omittedList.add(cnt);
                    omittedSet.add(cnt);
                    continue;
                }
                if(methodName.equalsIgnoreCase("Main")||methodName.equalsIgnoreCase("test")
                        ||packageName.contains("sample")||packageName.contains("example")
                        ||packageName.contains("template")||packageName.equals("null")){
//                    System.out.println(line);
                    cnt2++;
                    omittedList.add(cnt);
                    omittedSet.add(cnt);
                    continue;
                }

                newLine.append(line+"\n");
            }
            else{
//                System.out.println(cnt+":"+line);
                omittedList.add(cnt);
                omittedSet.add(cnt);
                cnt3++;
            }

        }


        System.out.println("cnt1:"+cnt1);
        System.out.println("cnt2:"+cnt2);
        System.out.println("cnt3:"+cnt3);
        System.out.println("total:"+String.valueOf(cnt1+cnt2+cnt3));
        System.out.println("omittedList.size():"+omittedList.size());
        System.out.println("omittedSet.size():"+omittedSet.size());
        System.out.println("newMethodInfoList.size():"+newMethodInfoList.size());
        StringBuilder methodInfo1 = new StringBuilder();
//        System.out.println(omittedList);
//        FileHelper.outputToFile(basePath + "new_result.txt",newLine,false);
        ArrayList<String> filteredMethodInfo = new ArrayList<>();
        for(int i = 0; i < newMethodInfoList.size(); i++ ){
            if(omittedList.contains(i)){
//                System.out.println("omit");
            }
            else{
                filteredMethodInfo.add(newMethodInfoList.get(i));
                methodInfo1.append(newMethodInfoList.get(i)+"\n");
            }
        }
        System.out.println("filteredMethodInfo.size():"+filteredMethodInfo.size());
        FileHelper.outputToFile(basePath + "MethodInfo_1.txt",methodInfo1,false);
//        ArrayList<Integer> omitIndexList = new ArrayList<>();
//        for(int i = 0; i < methodInfoList.size(); i++){
//            String info = methodInfoList.get(i);
//            String token = methodTokensList.get(i);
//            String line = info + ":" + token;
//            if(filteredMethodInfo.contains(line)){
//
//            }
//            else{
////                System.out.println("omit");
//                omitIndexList.add(i);
//            }
//        }
////
//        System.out.println(omitIndexList.size());









//
//
//        System.out.println("inconsistent:"+omittedList.size());
//
//
//
//        for(int i = 0; i < methodInfoList.size(); i++){
//            if(omittedList.contains(i)){
//
//            }
//            else{
//                String infoLine = methodInfoList.get(i);
//                String tokenLine = methodTokensList.get(i);
//                newInfo.append(infoLine+"\n");
//                newToken.append(tokenLine+"\n");
//            }
//        }
//        FileHelper.outputToFile("E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\NegativeItems\\TokensLT94\\filteredSelectedMethodInfo_1.txt", newInfo, false);
//        FileHelper.outputToFile("E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\NegativeItems\\TokensLT94\\filteredSelectedMethodTokens_1.txt", newToken, false);
    }



    public static void filterPositiveItems (String basePath, String resultPath) throws IOException {

    String methodInfoFile = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\PositiveItems\\TokensLT94\\MethodInfo_notUnique.txt";
    ArrayList<String> methodInfoList = ParserMethodNameMain.getLines(methodInfoFile);

    String methodTokensFile = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\PositiveItems\\TokensLT94\\MethodTokens_notUnique.txt";
    ArrayList<String> methodTokensList = ParserMethodNameMain.getLines(methodTokensFile);

    String parsedMethodNamesFile = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\PositiveItems\\TokensLT94\\ParsedMethodNames_notUnique.txt";
    ArrayList<String> parsedMethodNamesList = ParserMethodNameMain.getLines(parsedMethodNamesFile);

    String newMethodInfoFile = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\PositiveItems\\TokensLT94\\MethodInfos.txt";
    ArrayList<String> newMethodInfoList = ParserMethodNameMain.getLines(newMethodInfoFile);

    int cnt = 0;
    int bodyCnt = 0;

//        String resultPath = basePath + "result.txt";
        FileInputStream fileInputStream = new FileInputStream(resultPath);
        Scanner scanner1 = new Scanner(fileInputStream);
        StringBuilder newLine = new StringBuilder();
        int cnt1 =0,cnt2=0,cnt3=0;

        ArrayList<Integer> omittedList = new ArrayList<>();

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
                Matcher m = Pattern.compile(regex).matcher(methodName);
                // filter out the method names without alphabetic letters
                if(m.matches()){
                    System.out.println("-----------------"+line);
                    cnt1++;
                    omittedList.add(cnt);
                    continue;
                }
                if(methodName.equalsIgnoreCase("Main")||methodName.equalsIgnoreCase("test")
                        ||packageName.contains("sample")||packageName.contains("example")
                        ||packageName.contains("template")||packageName.equals("null")){
                    System.out.println(line);
                    cnt2++;
                    omittedList.add(cnt);
                    continue;
                }

                newLine.append(line+"\n");
            }
            else{
//                System.out.println(cnt+":"+line);
                omittedList.add(cnt);
                cnt3++;
            }
            cnt++;
        }


        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);
        System.out.println(String.valueOf(cnt1+cnt2+cnt3));

        FileHelper.outputToFile(basePath + "new_result.txt",newLine,false);
        ArrayList<String> filteredMethodInfo = new ArrayList<>();
        for(int i = 0; i < newMethodInfoList.size(); i++ ){
            if(omittedList.contains(i)){
                System.out.println("omit");
            }
            else{
                filteredMethodInfo.add(newMethodInfoList.get(i));
            }
        }
        System.out.println("filteredMethodInfo.size():"+filteredMethodInfo.size());

        ArrayList<Integer> omitIndexList = new ArrayList<>();
        for(int i = 0; i < methodInfoList.size(); i++){
            String info = methodInfoList.get(i);
            String token = methodTokensList.get(i);
            String line = info + ":" + token;
            if(filteredMethodInfo.contains(line)){

            }
            else{
//                System.out.println("omit");
                omitIndexList.add(i);
            }
        }

        System.out.println(omitIndexList.size());

        StringBuilder newInfo = new StringBuilder();
        StringBuilder newToken = new StringBuilder();
        StringBuilder newParsedName = new StringBuilder();

        for(int i = 0; i < methodInfoList.size(); i++){
            if(omitIndexList.contains(i)){

            }
            else{
                String infoLine = methodInfoList.get(i);
                String tokenLine = methodTokensList.get(i);
                String parsedNameLine = parsedMethodNamesList.get(i);
                newInfo.append(infoLine+"\n");
                newToken.append(tokenLine+"\n");
                newParsedName.append(parsedNameLine+"\n");
            }
        }
        FileHelper.outputToFile("E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\PositiveItems\\TokensLT94\\filteredMethodInfos.txt", newInfo, false);
        FileHelper.outputToFile("E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\PositiveItems\\TokensLT94\\filteredMethodTokens.txt", newToken, false);
        FileHelper.outputToFile("E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\PositiveItems\\TokensLT94\\filteredParsedMethodNames.txt", newParsedName, false);
    }

    private static void filterInconsistentSampleFromConsistentOnes(String consistentFilePath,String inconsistentFilePath,ArrayList<Integer> omittedList) throws FileNotFoundException {
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
//                System.out.println(packageClassMethodName);
                omittedList.add(cnt);
            }
            else{
                finalResult.append(line+"\n");
            }
        }
//        System.out.println(cnt);
//        FileHelper.outputToFile("E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\NegativeItems\\TokensLT94\\final_result.txt",finalResult,false);
//        FileHelper.outputToFile("E:\\Workspace\\ICSE2020\\TestData\\Positive\\final_result.txt",finalResult,false);
//        FileHelper.outputToFile("E:\\Workspace\\ICSE2020\\TestData\\Consistent\\final_result.txt",finalResult,false);
    }
}
