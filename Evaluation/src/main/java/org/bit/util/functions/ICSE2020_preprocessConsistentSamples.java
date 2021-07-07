/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/4
 */

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.javaparser.ParserMain;
import org.bit.util.javaparser.ParserMethodNameMain;
import org.bit.util.javaparser.util.JavaParserUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ICSE2020_preprocessConsistentSamples {

    public static String projectPath = "E:\\BIT\\BadMethodName\\NegativeItemSet\\NewJavaRepos\\";
//    public static String projectPath = "E:\\BIT\\BadMethodName\\NegativeItemSet\\OldJavaRepos\\";
    public static List<String> list = new ArrayList<String>();
    public static void main(String [] args) throws IOException {
        String basePath = "E:\\BIT\\BadMethodName\\NegativeItemSet\\Output\\DL_Data_LT94\\";
//        String basePath1 = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\NegativeItems\\TokensLT94\\";
//        String resultFile = basePath1 + "result.txt";

        // JavaParser extract information
//        parseConsistentSample(basePath);
//        parseConsistentSampleFrom19(basePath, basePath + "result.txt");
        //filter main example non-alphabet
//        filterMainTestExampleTemplateAndEmpty(basePath1);

        //filter inconsistent from consistent
//        String consistentFilePath = basePath1 + "new_result.txt";
////        String consistentFilePath = "E:\\Workspace\\ICSE2020\\TestData\\Positive\\new_result.txt";
//        String inconsistentFilePath = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\PositiveItems\\TokensLT94\\new_result.txt";
//        filterInconsistentSampleFromConsistentOnes(consistentFilePath,inconsistentFilePath);
//
//        //OutputTokens
//        ParserMethodNameMain.outputTokens(basePath1);
////        tokenization
//        ParserMethodNameMain.parseMethodNameTokens(basePath1);
//        ParserMethodNameMain.parseMethodContextTokens(basePath1);

//        RemoveDuplicates.removeDuplicates(basePath);

    }
    public static List<File> readProjects1111(String filePath) {
        List<File> projects = new ArrayList<>();
        File file = new File(filePath);
        if(file.isDirectory()){
            File [] fileList = file.listFiles();
            for( File f:fileList){
                if(f.isDirectory())
                projects.add(f);
            }
        }

        return projects;
    }
    public static void parseConsistentSampleFrom19(String basePath, String resultPath) throws IOException {
        List<File> projectList = readProjects1111(basePath+"SelectedData\\");
//        for(File f:projectList)
//            System.out.println(f);
        int methodNumber = 0;
//        System.out.println(projectList.length);

        String methodInfoFile = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\NegativeItems\\TokensLT94\\filteredSelectedMethodInfo.txt";
        ArrayList<String> methodInfoList = ParserMethodNameMain.getLines(methodInfoFile);

        String methodTokensFile = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\NegativeItems\\TokensLT94\\filteredSelectedMethodTokens.txt";
        ArrayList<String> methodTokensList = ParserMethodNameMain.getLines(methodTokensFile);

//        ArrayList<String> methodPCMList = new ArrayList<>();
//        for(String s:methodInfoList){
//            int lastSemicolon = s.lastIndexOf(":");
//            int penultimateSemicolon = s.lastIndexOf(":",lastSemicolon-1);
//            String methodPCM = s.substring(0,penultimateSemicolon);
////            System.out.println(methodPCM);
//            methodPCMList.add(methodPCM);
//        }
//        System.out.println("methodPCMList.size():"+methodPCMList.size());
        int cnt = 0;
        int bodyCnt = 0;
        int classEmptyCnt = 0;
//        HashMap<String,Integer> map = new HashMap<>();
//        for(String s:methodInfoList){
//            String projectName = s.substring(0,s.indexOf(":"));
//            if(map.containsKey(projectName)){
//                int value = map.get(projectName);
//                map.put(projectName,value + 1);
//            }
//            else{
//                map.put(projectName,1);
//            }
//        }
//        for(Map.Entry<String,Integer> entry:map.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }

        StringBuilder methodInfo = new StringBuilder();
        StringBuilder methodInfo1 = new StringBuilder();


        for(File project:projectList){
            int projectCnt = 0;
            System.out.println(project);
            String methodBodiesPath = project + "\\method_bodies.txt";
            String methodInfoPath = project + "\\SelectedMethodInfo.txt";
            String methodTokensPath = project + "\\SelectedMethodTokens.txt";
            ArrayList<String> methodTokensLineList = ParserMethodNameMain.getLines(methodTokensPath);
            ArrayList<String> classNames = new ArrayList<>();
            ArrayList<String> methodNames = new ArrayList<>();
            ArrayList<String> methodNames1 = new ArrayList<>();
            ArrayList<String> packageNames = new ArrayList<>();
            ArrayList<String> parameterNames = new ArrayList<>();
            ArrayList<String> returnValues = new ArrayList<>();
            ArrayList<String> parsedMethodNames = new ArrayList<>();
            ArrayList<String> tokens = new ArrayList<>();


            ArrayList<String> projectNames = new ArrayList<>();
            ArrayList<StringBuilder> methodBodies = new ArrayList<>();
            ArrayList<StringBuilder> methodBodies1 = new ArrayList<>();

            FileInputStream fileInputStream = new FileInputStream(methodInfoPath);
            Scanner scanner1 = new Scanner(fileInputStream);
            ArrayList<Integer> omittedIndexes = new ArrayList<>();
            int index = 0;
            while(scanner1.hasNext()){
                String line1 = scanner1.nextLine();
                String [] infos = line1.split(":");

                String className = infos[2];
                String packageName = infos[1];
//                System.out.println("className:"+className);
                String token = methodTokensLineList.get(index);
//                System.out.println(classNames.size());
                methodNumber ++;
                String projectName = infos[0].split("@")[1].substring(0,infos[0].split("@")[1].length()-4);
//                System.out.println("projectName:"+projectName);
                String parsedMethodNameLine = line1.replaceAll("@","#");
                String parsedMethodName = parsedMethodNameLine.substring(parsedMethodNameLine.lastIndexOf("#")+1);
//                System.out.println(projectNames.size());
                String returnValue = infos[5].substring(0,infos[5].indexOf("@"));
                String parameter = infos[4];
//                String tokens = splitArray[10];
                String methodName1 = infos[3];

//                System.out.println(projectName+":"+packageName+":"+className+":"+methodName1+":"+parameter+":"+returnValue+":"+parsedMethodName);
                if(methodInfoList.contains(projectName+":"+packageName+":"+className+":"+methodName1+":"+parameter+":"+returnValue+":"+parsedMethodName)){
//                    System.out.println(projectName+":"+className+":"+methodName1);
//                    if(projectName.substring(0,projectName.indexOf(":")).equals("closure-compiler"))
//                    System.out.println(line1);
//                    if(methodTokensList.contains(tokens)){
                        cnt += 1;
                        projectCnt += 1;
//                        methodNames1.add(methodName1);
                        parameterNames.add(parameter);
                        returnValues.add(returnValue);
                        methodNames.add(methodName1);
                        projectNames.add(projectName);
                        classNames.add(className);
                        packageNames.add(packageName);
                        parsedMethodNames.add(parsedMethodName);
                        tokens.add(token);
                        methodInfo.append(projectName+":"+packageName+":"+className+":"+methodName1+":"+parameter+":"+returnValue+":"+parsedMethodName+":"+token+"\n");
//                    }

//                    else{
//                        omittedIndexes.add(index);
//                    }

                }
                else{
                    omittedIndexes.add(index);
                }
                index++;
            }
            System.out.println("projectCnt:"+projectCnt);
            System.out.println("cnt:"+cnt);
//            for(int i = 0;i<methodNames.size();i++){
//                System.out.println(projectNames.get(i)+":"+classNames.get(i)+":"+methodNames.get(i));
//            }


            // get method bodies.
            FileInputStream fileInputStream3 = new FileInputStream(methodBodiesPath);
            Scanner scanner3 = new Scanner(fileInputStream3);
            StringBuilder methodBody =new StringBuilder();
            methodBody.setLength(0);
            int lineNumber =1;
            while (scanner3.hasNext()) {
                String line3 = scanner3.nextLine();
                if(line3.equals("#METHOD_BODY#========================")){
                    scanner3.nextLine();
                    if(lineNumber!=1){
                        methodBodies.add(methodBody);
                        methodBody = new StringBuilder();
                        continue;
                    }

//                    System.out.println(methodBody);

                }
                else{
                    methodBody.append(line3+"\n");
                }

                lineNumber++;

            }
            if(methodBody.length()!=0)
                methodBodies.add(methodBody);

            for(int i=0;i<methodBodies.size();i++){
                if(omittedIndexes.contains(i)){
                    System.out.println("omit");
                }
                else{
                    methodBodies1.add(methodBodies.get(i));
                }
            }


//            for(StringBuilder stringBuilder:methodBodies){
//                System.out.println(stringBuilder);
//            }
            System.out.println(methodBodies.size());
            System.out.println(methodBodies1.size());
            System.out.println(classNames.size());
            System.out.println(projectNames.size());
            System.out.println(packageNames.size());
            System.out.println(methodNames.size());
            System.out.println(returnValues.size());
            System.out.println(parameterNames.size());
            bodyCnt += methodBodies1.size();
//            System.out.println(projectNames);
//            System.out.println(classNames);
            // debug
//            if(project.getAbsolutePath().equals("E:\\Workspace\\data\\RenamedMethods_original\\error-prone-javac")){
//                int index =-1;
//                for(String methodName1:methodNames1){
//                    System.out.println("methodName1:"+methodName1);
//                    index++;
//                    System.out.println("lineNumber:"+index);
//                    System.out.println(methodBodies.get(index));
//                }
//            }




            for(int i =0 ;i <methodBodies1.size(); i++){
                BufferedWriter out = null;
                if(classNames.get(i).equals("")){
                    classEmptyCnt++;
                    continue;
                }
                try {

                    String code = "class " + classNames.get(i) + "{" +
                            methodBodies1.get(i) + "}";
                    System.out.println(projectNames.get(i));
                    System.out.println(code);
                    List<String> parserResult = JavaParserUtil.parser(code);
                    System.out.println(parserResult.size());

//                    out = new BufferedWriter(new FileWriter(basePath + "InconsistentData\\result.txt", true));
                    out = new BufferedWriter(new FileWriter(resultPath, true));
//                    out = new BufferedWriter(new FileWriter(basePath + "result.txt", true));
                    for (String s : parserResult) {
                        out.write(projectNames.get(i) + ":" + packageNames.get(i) + ":" + classNames.get(i)+":"+ methodNames.get(i)+":"+ s);
                        methodInfo1.append(projectNames.get(i)+":"+packageNames.get(i)+":"+classNames.get(i)+":"+methodNames.get(i)+":"+parameterNames.get(i)+":"+returnValues.get(i)+":"+parsedMethodNames.get(i)+":"+tokens.get(i)+"\n");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally{
                    out.close();
                }
            }

        }
        FileHelper.outputToFile(basePath + "MethodInfo.txt",methodInfo,false);
        FileHelper.outputToFile(basePath + "MethodInfo1.txt",methodInfo1,false);
        System.out.println("bodyCnt:"+bodyCnt);
        System.out.println("classEmptyCnt:"+classEmptyCnt);
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
        FileHelper.outputToFile("E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\NegativeItems\\TokensLT94\\final_result.txt",finalResult,false);
//        FileHelper.outputToFile("E:\\Workspace\\ICSE2020\\TestData\\Positive\\final_result.txt",finalResult,false);
//        FileHelper.outputToFile("E:\\Workspace\\ICSE2020\\TestData\\Consistent\\final_result.txt",finalResult,false);
    }

    public static void filterMainTestExampleTemplateAndEmpty(String basePath) throws FileNotFoundException {
        String resultPath = basePath + "result.txt";
        FileInputStream fileInputStream = new FileInputStream(resultPath);
        Scanner scanner1 = new Scanner(fileInputStream);
        StringBuilder newLine = new StringBuilder();
        int cnt1 =0,cnt2=0,cnt3=0;
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
                if(methodName.equalsIgnoreCase("Main")||methodName.equalsIgnoreCase("test")
                        ||packageName.contains("sample")||packageName.contains("example")
                        ||packageName.contains("template")||packageName.equals("null")){
                    System.out.println(line);
                    cnt2++;
                    continue;
                }

                    newLine.append(line+"\n");
            }
            else{
                cnt3++;
            }

        }
        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);
        System.out.println(String.valueOf(cnt1+cnt2+cnt3));

        FileHelper.outputToFile(basePath + "new_result.txt",newLine,false);

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
            BufferedReader in = new BufferedReader(new FileReader(basePath + "Output\\JavaFiles_new.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(projectPath + str);
//                String projectName = str.split("/")[1];
                //        System.out.println(file.getAbsolutePath());

                length++;
                try {
                    List<String> parserResult = JavaParserUtil.parser(new File(projectPath + str));
//                    System.out.println(parserResult.size());
                    String projectName = str.substring(0,str.indexOf("\\"));
                    System.out.println("projectName:"+projectName);
                    BufferedWriter out = new BufferedWriter(new FileWriter(basePath + "new_result.txt", true));
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
