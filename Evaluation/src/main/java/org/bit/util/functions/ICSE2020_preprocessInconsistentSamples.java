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
import org.bit.util.javaparser.ParserMethodNameMain;
import org.bit.util.javaparser.util.JavaParserUtil;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ICSE2020_preprocessInconsistentSamples {
    public static void main(String [] args) throws IOException {
        //JavaParser
//        String basePath = "E:\\BIT\\BadMethodName\\icse2020\\TestData\\Inconsistent\\test\\InconsistentData\\";
//        String basePath = "E:\\BIT\\BadMethodName\\ICSE2020DataSet\\TestData\\Inconsistent\\";
        String basePath = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\notReal\\";
//        String basePath1 = "E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\PositiveItems\\TokensLT94\\";
//        String basePath = "E:\\Workspace\\ICSE2020\\TestData\\For1_1_Consistent\\";
//        preprocessNegativeSample2(basePath,basePath + "result.txt");
        preprocessNegativeSample1(basePath,basePath + "result(3379)_1.txt");
//        //filter main example non-alphabet
//        filterMainTestExampleTemplateAndEmpty(basePath1);
        //OutputTokens
//        outputTokens(basePath1);
        //tokenization
//        ParserMethodNameMain.parseMethodNameTokens(basePath1);
//        ParserMethodNameMain.parseMethodContextTokens(basePath1);
//        //remove duplicates
//        RemoveDuplicates.removeDuplicates(basePath);


        //1_1_more
//        String resultPath1 = basePath + "ConsistentData\\result.txt";
//        preprocessNegativeSample1(basePath,resultPath1);
//        String resultPath2 = basePath + "InconsistentData\\result.txt";
//        preprocessNegativeSample1(basePath,resultPath2);

//        filterMainTestExampleTemplateAndEmpty(basePath,"InconsistentData");
//        filterMainTestExampleTemplateAndEmpty(basePath,"ConsistentData");

        splitRandomly2(basePath);
        split(basePath);

        //OutputTokens
//        outputTokens(basePath,"InconsistentData");
//        //tokenization
//        ParserMethodNameMain.parseMethodNameTokens(basePath + "InconsistentData\\");
//        ParserMethodNameMain.parseMethodContextTokens(basePath + "InconsistentData\\");
//        //remove duplicates
//        RemoveDuplicates.removeDuplicates(basePath  + "InconsistentData\\");

        //OutputTokens
//        outputTokens(basePath,"ConsistentData");
//        //tokenization
//        ParserMethodNameMain.parseMethodNameTokens(basePath + "ConsistentData\\");
//        ParserMethodNameMain.parseMethodContextTokens(basePath + "ConsistentData\\");
//        //remove duplicates
//        RemoveDuplicates.removeDuplicates(basePath  + "ConsistentData\\");
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
                System.out.println(line);
                cnt3++;
            }

        }
        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);
        System.out.println(String.valueOf(cnt1+cnt2+cnt3));

        FileHelper.outputToFile(basePath + "new_result.txt",newLine,false);
    }


    public static void filterMainTestExampleTemplateAndEmpty(String basePath,String type) throws FileNotFoundException {
        String resultPath = basePath + type + "\\result.txt";
//        String resultPath = basePath + "ConsistentData\\result.txt";
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
                if(methodName.equalsIgnoreCase("Main")||packageName.contains("sample")||packageName.contains("example")
                        ||packageName.contains("template")||packageName.equals("null")){
                    System.out.println(line);
                    cnt2++;
                    continue;
                }

                newLine.append(line+"\n");


            }
            else{
                cnt3++;
                System.out.println(line);
            }

        }
        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);
        System.out.println(String.valueOf(cnt1+cnt2+cnt3));

        FileHelper.outputToFile(basePath + type +  "\\new_result.txt",newLine,false);
//        FileHelper.outputToFile(basePath + "ConsistentData\\new_result.txt",newLine,false);

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
    public static void split (String basePath) throws FileNotFoundException {
        String labels = basePath + "1_1/labels.txt";
        String context = basePath + "1_1/1_1_context.txt";
        String name = basePath + "1_1/1_1_name.txt";

        ArrayList<String> labelsList = ParserMethodNameMain.getLines(labels);
        ArrayList<String> nameList = ParserMethodNameMain.getLines(name);
        ArrayList<String> contextList = ParserMethodNameMain.getLines(context);
        StringBuilder inconsisName = new StringBuilder();
        StringBuilder inconsisContext = new StringBuilder();
        StringBuilder consisName = new StringBuilder();
        StringBuilder consisContext = new StringBuilder();
        int consiscnt=0,inconsiscnt =0;
        for(int i=0;i<labelsList.size();i++){
            String label = labelsList.get(i);
            if(label.equals("0")){
                inconsisName.append(nameList.get(i)+"\n");
                inconsisContext.append(contextList.get(i)+"\n");
                inconsiscnt++;
            }
            else{
                consisName.append(nameList.get(i)+"\n");
                consisContext.append(contextList.get(i)+"\n");
                consiscnt++;
            }
        }
        System.out.println(consiscnt+":"+inconsiscnt);
        FileHelper.outputToFile(basePath + "1_1/ConsistentData/parsedMethodNameTokens.txt",consisName,false);
        FileHelper.outputToFile(basePath + "1_1/ConsistentData/parsedMethodContextTokens.txt",consisContext,false);
        FileHelper.outputToFile(basePath + "1_1/InconsistentData/parsedMethodNameTokens.txt",inconsisName,false);
        FileHelper.outputToFile(basePath + "1_1/InconsistentData/parsedMethodContextTokens.txt",inconsisContext,false);

    }
    private static void splitRandomly1(String basePath) throws FileNotFoundException {
        String inconsistentResultFile = basePath + "InconsistentData/new_result.txt";
        String ConsistentResultFile = basePath + "ConsistentData/new_result.txt";
        ArrayList<String> inconsisArrayList = ParserMethodNameMain.getLines(inconsistentResultFile);
        ArrayList<String> consisArrayList = ParserMethodNameMain.getLines(ConsistentResultFile);
        System.out.println(inconsisArrayList.size());
        System.out.println(consisArrayList.size());
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder labelBuilder = new StringBuilder();
        int numConsistent = consisArrayList.size() / 2;
        int numInconsistent = consisArrayList.size() - numConsistent;
        Random random = new Random();

        for(int index = 0; index<consisArrayList.size(); index++){
            int nextNumber = random.nextInt(100);

            if(nextNumber < 50){
                if(numInconsistent==0){
                    stringBuilder.append(consisArrayList.get(index)+"\n");
                    labelBuilder.append("1\n");
                }
                else{
                    stringBuilder.append(inconsisArrayList.get(index)+"\n");
                    labelBuilder.append("0\n");
                    numInconsistent--;
                }
            }
            else{
                if(numConsistent==0){
                    stringBuilder.append(inconsisArrayList.get(index)+"\n");
                    labelBuilder.append("0\n");

                }
                else{
                    stringBuilder.append(consisArrayList.get(index)+"\n");
                    labelBuilder.append("1\n");
                    numConsistent--;
                }

            }
        }
        FileHelper.outputToFile(basePath + "result.txt",stringBuilder,false);
        FileHelper.outputToFile(basePath + "labels.txt",labelBuilder,false);

    }

    private static void splitRandomly2(String basePath) throws FileNotFoundException {
        String inconsistentNameFile = basePath + "InconsistentData/parsedMethodNameTokens.txt";
        String inconsistentContextFile = basePath + "InconsistentData/parsedMethodContextTokens.txt";
        String ConsistentNameFile = basePath + "ConsistentData/parsedMethodNameTokens.txt";
        String ConsistentContextFile = basePath + "ConsistentData/parsedMethodContextTokens.txt";
        ArrayList<String> inconsisNameArrayList = ParserMethodNameMain.getLines(inconsistentNameFile);
        ArrayList<String> inconsisTokenArrayList = ParserMethodNameMain.getLines(inconsistentContextFile);
        ArrayList<String> consisNameArrayList = ParserMethodNameMain.getLines(ConsistentNameFile);
        ArrayList<String> consisTokenArrayList = ParserMethodNameMain.getLines(ConsistentContextFile);
        System.out.println(inconsisNameArrayList.size());
        System.out.println(consisNameArrayList.size());
        StringBuilder nameStringBuilder = new StringBuilder();
        StringBuilder contextStringBuilder = new StringBuilder();
        StringBuilder labelBuilder = new StringBuilder();
        int numConsistent = consisNameArrayList.size() / 2;
        int numInconsistent = consisNameArrayList.size() - numConsistent;
        Random random = new Random();

        for(int index = 0; index<consisNameArrayList.size(); index++){
            int nextNumber = random.nextInt(100);

            if(nextNumber < 50){
                if(numInconsistent==0){
                    nameStringBuilder.append(consisNameArrayList.get(index)+"\n");
                    contextStringBuilder.append(consisTokenArrayList.get(index)+"\n");

                    labelBuilder.append("1\n");
                }
                else{
                    nameStringBuilder.append(inconsisNameArrayList.get(index)+"\n");
                    contextStringBuilder.append(inconsisTokenArrayList.get(index)+"\n");
                    labelBuilder.append("0\n");
                    numInconsistent--;
                }
            }
            else{
                if(numConsistent==0){
                    nameStringBuilder.append(inconsisNameArrayList.get(index)+"\n");
                    contextStringBuilder.append(inconsisTokenArrayList.get(index)+"\n");
                    labelBuilder.append("0\n");

                }
                else{
                    nameStringBuilder.append(consisNameArrayList.get(index)+"\n");
                    contextStringBuilder.append(consisTokenArrayList.get(index)+"\n");
                    labelBuilder.append("1\n");
                    numConsistent--;
                }

            }
        }
        FileHelper.outputToFile(basePath + "1_1/1_1_name.txt",nameStringBuilder,false);
        FileHelper.outputToFile(basePath + "1_1/1_1_context.txt",contextStringBuilder,false);
        FileHelper.outputToFile(basePath + "1_1/labels.txt",labelBuilder,false);

    }
    public static void outputTokens(String basePath,String type) throws FileNotFoundException {
        String parsedMethodPath = "";
        if(type.equalsIgnoreCase("InconsistentData"))
            parsedMethodPath = basePath + type + "/inconsisResult.txt";
        else
            parsedMethodPath = basePath + type + "/new_result.txt";
        FileInputStream fis1 = new FileInputStream(parsedMethodPath);
        Scanner scanner1 = new Scanner(fis1);
        StringBuilder extractedMethodContextInfo = new StringBuilder();
        StringBuilder extractedMethodNameInfo = new StringBuilder();
        while(scanner1.hasNext()){
            String bodyTokens = "";
            String line = scanner1.nextLine();
            String [] splitArray = line.split(":");
//            if(splitArray.length==6){
            bodyTokens = splitArray[5];
            String className = splitArray[2];
            String methodName = splitArray[3];
            String parAndReturnType = splitArray[4];
            extractedMethodContextInfo.append(className + " ");
            extractedMethodContextInfo.append(parAndReturnType+ " ");
            extractedMethodContextInfo.append(bodyTokens+"\n");
            extractedMethodNameInfo.append(methodName+"\n");
//            }

        }
        FileHelper.outputToFile(basePath + type + "/beforeParsedMethodContextTokens.txt",extractedMethodContextInfo,false);
        FileHelper.outputToFile(basePath + type + "/beforeParsedMethodNameTokens.txt",extractedMethodNameInfo,false);
    }
    public static void outputTokens(String basePath) throws FileNotFoundException {

        String parsedMethodPath = basePath + "new_result.txt";
        FileInputStream fis1 = new FileInputStream(parsedMethodPath);
        Scanner scanner1 = new Scanner(fis1);
        StringBuilder extractedMethodContextInfo = new StringBuilder();
        StringBuilder extractedMethodNameInfo = new StringBuilder();
        while(scanner1.hasNext()){
            String bodyTokens = "";
            String line = scanner1.nextLine();
            String [] splitArray = line.split(":");
//            if(splitArray.length==6){
            bodyTokens = splitArray[5];
            String className = splitArray[2];
            String methodName = splitArray[3];
            String parAndReturnType = splitArray[4];
            extractedMethodContextInfo.append(className + " ");
            extractedMethodContextInfo.append(parAndReturnType+ " ");
            extractedMethodContextInfo.append(bodyTokens+"\n");
            extractedMethodNameInfo.append(methodName+"\n");
//            }

        }
        FileHelper.outputToFile(basePath + "beforeParsedMethodContextTokens.txt",extractedMethodContextInfo,false);
        FileHelper.outputToFile(basePath + "beforeParsedMethodNameTokens.txt",extractedMethodNameInfo,false);
    }
    public static List<File> readProjects1111(String filePath) {
        List<File> projects = new ArrayList<>();
        File file = new File(filePath);
        if(file.isDirectory()){
            File [] fileList = file.listFiles();
            for( File f:fileList){
                projects.add(f);
            }
        }

        return projects;
    }
    public static void preprocessNegativeSample1(String basePath, String resultPath) throws IOException {
//        File[] projectList = new File(basePath+"RenamedMethods_more\\").listFiles();
          List<File> projectList = readProjects1111(basePath+"RenamedMethods_more\\");
//        for(File f:projectList)
//            System.out.println(f);
        int methodNumber = 0;
//        System.out.println(projectList.length);

        String methodInfoFile = "E:\\BIT\\BadMethodName\\190Backup\\server190backup\\BadMethodName\\Output_46projects\\real_more\\DL_Data\\RenamedMethods\\MethodInfo_notUnique.txt";
        ArrayList<String> methodInfoList = ParserMethodNameMain.getLines(methodInfoFile);

        String methodTokensFile = "E:\\BIT\\BadMethodName\\190Backup\\server190backup\\BadMethodName\\Output_46projects\\real_more\\DL_Data\\RenamedMethods\\MethodTokens_notUnique.txt";
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

        for(File project:projectList){
            int projectCnt = 0;
            System.out.println(project);
            String methodBodiesPath = project + "\\ActualRenamed\\MethodBodies.txt";
            String methodInfoPath = project + "\\ActualRenamed\\MethodTokens.txt";
            ArrayList<String> classNames = new ArrayList<>();
            ArrayList<String> methodNames = new ArrayList<>();
            ArrayList<String> methodNames1 = new ArrayList<>();
            ArrayList<String> projectNames = new ArrayList<>();
            ArrayList<StringBuilder> methodBodies = new ArrayList<>();
            ArrayList<StringBuilder> methodBodies1 = new ArrayList<>();

            FileInputStream fileInputStream = new FileInputStream(methodInfoPath);
            Scanner scanner1 = new Scanner(fileInputStream);
            ArrayList<Integer> omittedIndexes = new ArrayList<>();
            int index = 0;
            while(scanner1.hasNext()){
                String line1 = scanner1.nextLine();
                String [] splitArray = line1.split(":");
                String packageName = splitArray[3];
                String [] splitArray1 = packageName.split("\\.");
                String className = splitArray1[splitArray1.length-2];
//                System.out.println("className:"+className);

//                System.out.println(classNames.size());
                methodNumber ++;
                String projectName = splitArray[0] + ":"+ packageName.substring(0,packageName.lastIndexOf(".",packageName.lastIndexOf(".")-1));
//                System.out.println("projectName:"+projectName);

//                System.out.println(projectNames.size());
                String returnValue = splitArray[8];
                String parameter = splitArray[9];
                String tokens = splitArray[10];
                String oldMethodName = splitArray[4];
                String newMethodName = splitArray[5];

                String methodName = oldMethodName.split("@")[0];
                String methodName1 = newMethodName.split("@")[0];
//                System.out.println("methodName:"+methodName);

//                System.out.println(methodNames.size());
                if(methodInfoList.contains(projectName+":"+className+":"+methodName1+":"+parameter+":"+returnValue)){
//                    System.out.println(projectName+":"+className+":"+methodName1);
//                    if(projectName.substring(0,projectName.indexOf(":")).equals("closure-compiler"))
//                    System.out.println(line1);

                    if(methodTokensList.contains(tokens)){
                        cnt += 1;
                        projectCnt += 1;

                        methodNames.add(methodName);
                        methodNames1.add(methodName1);
                        projectNames.add(projectName);
                        classNames.add(className);
                        methodInfo.append(projectName+":"+className+":"+methodName1+":"+parameter+":"+returnValue+":"+tokens+"\n");
                    }
                    else{
                        omittedIndexes.add(index);
                    }

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
            System.out.println(methodNames.size());
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
                try {
                    String code = "class " + classNames.get(i) + "{" +
                            methodBodies1.get(i) + "}";
//                    System.out.println(code);
                    List<String> parserResult = JavaParserUtil.parser(code);
                    System.out.println(parserResult.size());

//                    out = new BufferedWriter(new FileWriter(basePath + "InconsistentData\\result.txt", true));
                    out = new BufferedWriter(new FileWriter(resultPath, true));
//                    out = new BufferedWriter(new FileWriter(basePath + "result.txt", true));
                    for (String s : parserResult) {
                            out.write(projectNames.get(i) + ":" +classNames.get(i)+":"+ methodNames1.get(i)+":"+ s);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally{
                    out.close();
                }
            }

        }
        System.out.println("bodyCnt:"+bodyCnt);
//        FileHelper.outputToFile("E:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2019\\PositiveItems\\TokensLT94\\MethodInfos.txt",methodInfo,false);
    }
    public static void preprocessNegativeSample2(String basePath, String resultPath) throws IOException {
        File[] projectList = new File(basePath+"RenamedMethods_more\\").listFiles();
//        for(File f:projectList)
//            System.out.println(f);
        int methodNumber = 0;
        for(File project:projectList){
            System.out.println(project);
            String methodBodiesPath = project + "\\ActualRenamed\\MethodBodies.txt";
            String methodInfoPath = project + "\\ActualRenamed\\MethodTokens.txt";
            ArrayList<String> classNames = new ArrayList<>();
            ArrayList<String> methodNames = new ArrayList<>();
            ArrayList<String> methodNames1 = new ArrayList<>();
            ArrayList<String> projectNames = new ArrayList<>();
            ArrayList<StringBuilder> methodBodies = new ArrayList<>();

            FileInputStream fileInputStream = new FileInputStream(methodInfoPath);
            Scanner scanner1 = new Scanner(fileInputStream);
            while(scanner1.hasNext()){
                String line1 = scanner1.nextLine();
                String [] splitArray = line1.split(":");
                String packageName = splitArray[3];
                String [] splitArray1 = packageName.split("\\.");
                String className = splitArray1[splitArray1.length-2];
//                System.out.println("className:"+className);
                classNames.add(className);
//                System.out.println(classNames.size());
                methodNumber += classNames.size();
                String projectName = splitArray[0] + ":"+ packageName.substring(0,packageName.lastIndexOf(".",packageName.lastIndexOf(".")-1));
//                System.out.println("projectName:"+projectName);
                projectNames.add(projectName);
//                System.out.println(projectNames.size());

                String oldMethodName = splitArray[4];
                String newMethodName = splitArray[5];

                String methodName = oldMethodName.split("@")[0];
                String methodName1 = newMethodName.split("@")[0];
//                System.out.println("methodName:"+methodName);
                methodNames.add(methodName);
                methodNames1.add(methodName1);
//                System.out.println(methodNames.size());
            }

            FileInputStream fileInputStream3 = new FileInputStream(methodBodiesPath);
            Scanner scanner3 = new Scanner(fileInputStream3);
            StringBuilder methodBody =new StringBuilder();
            methodBody.setLength(0);
            int lineNumber =1;
            while (scanner3.hasNext()) {
                String line3 = scanner3.nextLine();
                if(line3.equals("#METHOD_BODY#========================")){
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


//            for(StringBuilder stringBuilder:methodBodies){
//                System.out.println(stringBuilder);
//            }
            System.out.println(methodBodies.size());
            System.out.println(classNames.size());

            System.out.println(projectNames.size());
            System.out.println(methodNames.size());
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




            for(int i =0 ;i <classNames.size(); i++){
                BufferedWriter out = null;
                try {
                    String code = "class " + classNames.get(i) + "{" +
                            methodBodies.get(i) + "}";
//                    System.out.println(code);
                    List<String> parserResult = JavaParserUtil.parser(code);
//                    System.out.println(parserResult.size());

//                    out = new BufferedWriter(new FileWriter(basePath + "InconsistentData\\result.txt", true));
                    out = new BufferedWriter(new FileWriter(resultPath, true));
//                    out = new BufferedWriter(new FileWriter(basePath + "result.txt", true));
//                    System.out.println(projectNames.get(i)+":"+classNames.get(i));
                    for (String s : parserResult) {
                        out.write(projectNames.get(i) + ":" +classNames.get(i)+":"+ methodNames1.get(i)+":"+ s);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally{
                    out.close();
                }
            }
        }

        System.out.println(methodNumber);
    }
}
