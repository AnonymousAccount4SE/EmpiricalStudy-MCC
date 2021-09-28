/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package edu.lu.uni.serval.renamed.methods;
import edu.lu.uni.serval.method.parser.MethodNameParser;
import edu.lu.uni.serval.utils.FileHelper;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParserMethodNameMain {
    public static void main (String [] args) throws IOException {
//        String basePath = "/home1/michael/ICSE2020/java-med/JavaFiles/TestJavaFiles/";
//        String basePath = "/home1/michael/ICSE2020/java-med/JavaFiles/TrainingJavaFiles/";
        String basePath = "/home1/michael/BadMethodName/Output_TrainingRepos/";
//        String basePath = "/home1/michael/ICSE2020/java-med/JavaFiles/ValidationJavaFiles/";
        //extract pure tokens
        outputTokens(basePath);
//
//        //tokenization
        parseMethodNameTokens(basePath);
        parseMethodContextTokens(basePath);

        //filter test data info
//        String testDataSetPath = "D:\\BIT\\BadMethodName\\TestMethodInfo.txt";
//        String trainingDataSetPath = "D:\\BIT\\BadMethodName\\parser_result_new.txt";
//        filterTestDataSet(testDataSetPath,trainingDataSetPath);

        //filter main example
//        String methodNamesTokenPath = "D:\\BIT\\BadMethodName\\ParsedMethodNameTokens.txt";
//        String methodContextTokenPath = "D:\\BIT\\BadMethodName\\ParsedTokens.txt";
//        String trainingDataSetPath = "D:\\BIT\\BadMethodName\\parser_result_new.txt";
//        filterMainTestExampleTemplateAndEmpty(methodNamesTokenPath,methodContextTokenPath,trainingDataSetPath);
    }
    public static ArrayList<String> getLines(String filePath) throws IOException {
        FileInputStream fileInputStream1 = new FileInputStream(filePath);
        Scanner scanner1 = new Scanner(fileInputStream1);
        ArrayList<String> list = new ArrayList<>();
        while(scanner1.hasNext()){
            String line = scanner1.nextLine();
            list.add(line);
        }
        fileInputStream1.close();
        return list;
    }
    public static void preprocessNegativeSample(String basePath) throws FileNotFoundException {
        File [] projectList = new File(basePath).listFiles();
//        for(File f:projectList)
//            System.out.println(f);
        for(File project:projectList){
            String methodBodiesPath = project + "\\ActualRenamed\\MethodBodies.txt";
            String methodInfoPath = project + "\\ActualRenamed\\MethodTokens.txt";
            ArrayList<String> classNames = new ArrayList<>();
            ArrayList<String> methodNames = new ArrayList<>();
            ArrayList<StringBuilder> methodBodies = new ArrayList<>();

            FileInputStream fileInputStream = new FileInputStream(methodInfoPath);
            Scanner scanner1 = new Scanner(fileInputStream);
            while(scanner1.hasNext()){
                String line1 = scanner1.nextLine();
                String [] splitArray = line1.split(":");
                String packageName = splitArray[3];
                String [] splitArray1 = packageName.split("\\.");
                String className = splitArray1[splitArray1.length-2];
                System.out.println("className:"+className);
                classNames.add(className);

                String oldMethodName = splitArray[4];
                String methodName = oldMethodName.split("@")[0];
                System.out.println("methodName:"+methodName);
                methodNames.add(methodName);
            }

            FileInputStream fileInputStream3 = new FileInputStream(methodBodiesPath);
            Scanner scanner3 = new Scanner(fileInputStream3);
            StringBuilder methodBody = new StringBuilder();
            while (scanner3.hasNext()) {
                String line3 = scanner3.nextLine();
                if(line3.equals("#METHOD_BODY#========================")){
                    methodBodies.add(methodBody);
                    methodBody.setLength(0);
                    continue;
                }
                else{
                    methodBody.append(line3+"\n");
                }

            }
            System.out.println(methodBody);

        }
    }
    public static void filterMainTestExampleTemplateAndEmpty(String methodNamesTokenPath,String methodContextTokenPath,
                                                             String trainingDataSetPath) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(methodNamesTokenPath);
        Scanner scanner1 = new Scanner(fileInputStream);
        int cnt = 0; ArrayList<Integer> filteredMethodNameLineList = new ArrayList<>();
        while (scanner1.hasNext()) {
            String line1 = scanner1.nextLine();
            String regex="[^a-zA-Z \n]";
            Matcher m= Pattern.compile(regex).matcher(line1);
            // filter out the method names without alphabetic letters
            if(m.find()){
                filteredMethodNameLineList.add(cnt);
                cnt++;
            }
            //filter out the main Method names, and example names
            else if(line1.equalsIgnoreCase("Main")||line1.contains("sample")||line1.contains("example")
                    ||line1.contains("template")||line1.equals("null")){
                filteredMethodNameLineList.add(cnt);
                cnt++;
            }
            else
                cnt++;
        }
        System.out.println(filteredMethodNameLineList.size());

        StringBuilder newMethodName = new StringBuilder();
        StringBuilder newMethodTokens = new StringBuilder();
        // rewrite the filtered method context tokens
        FileInputStream fileInputStream3 = new FileInputStream(methodContextTokenPath);
        Scanner scanner3 = new Scanner(fileInputStream3);
        int index1 = 1;
        while(scanner3.hasNext()){
            String line3 = scanner3.nextLine();
            if(!filteredMethodNameLineList.contains(index1))
                newMethodTokens.append(line3+"\n");
            if(index1%10000==0){
                FileHelper.outputToFile("D:\\BIT\\BadMethodName\\FilteredParsedContextTokens.txt",newMethodTokens,true);
                newMethodTokens.setLength(0);
            }
            index1++;
        }
        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\FilteredParsedContextTokens.txt",newMethodTokens,true);

        // rewrite the filtered method name tokens
        FileInputStream fileInputStream4 = new FileInputStream(methodNamesTokenPath);
        Scanner scanner4 = new Scanner(fileInputStream4);
        index1 = 1;
        while(scanner4.hasNext()){
            String line4 = scanner4.nextLine();
            if(!filteredMethodNameLineList.contains(index1))
                newMethodName.append(line4+"\n");
            if(index1%10000==0){
                FileHelper.outputToFile("D:\\BIT\\BadMethodName\\FilteredParsedNamesTokens.txt",newMethodName,true);
                newMethodName.setLength(0);
            }
            index1++;
        }
        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\FilteredParsedNamesTokens.txt",newMethodName,true);

    }
    public static void filterTestDataSet(String testDataSetPath, String trainingDataSetPath) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(testDataSetPath);
        Scanner scanner = new Scanner(fileInputStream);
        HashSet<String> testDataLineSet = new HashSet<>();
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String [] array = line.split(":");
            String newLine = array[0] + ":" + array[1] + ":" + array[2];
            testDataLineSet.add(newLine);
        }
        System.out.println(testDataLineSet.size());
        FileInputStream fileInputStream1 = new FileInputStream(trainingDataSetPath);
        Scanner scanner1 = new Scanner(fileInputStream1);
        int lineNumber = 1;
        ArrayList<Integer> lineNumberList = new ArrayList<>();
        while(scanner1.hasNext()){
            String trainingMethodInfo = scanner1.nextLine();
            String [] array = trainingMethodInfo.split(":");
            String newLine = array[0] + ":" + array[1] + ":" + array[2];
            if(testDataLineSet.contains(newLine)){
                lineNumberList.add(lineNumber);
            }
            lineNumber++;

        }
        System.out.println(lineNumberList.size());

        String tokensFilePath = "D:\\BIT\\BadMethodName\\ParsedTokens.txt";
        FileInputStream fileInputStream3 = new FileInputStream(tokensFilePath);
        Scanner scanner3 = new Scanner(fileInputStream3);
        ArrayList<String> tokensList = new ArrayList<>();
        ArrayList<String> newTokensList = new ArrayList<>();
        int index1 = 1;
        System.out.println("tokensList.size():"+tokensList.size());
        while(scanner3.hasNext()){
            String line3 = scanner3.nextLine();
            if(!lineNumberList.contains(index1))
                newTokensList.add(line3);
            index1++;
        }

        System.out.println("newTokensList.size():"+newTokensList.size());
        StringBuilder stringBuilder = new StringBuilder();
        int cnt =0;
        for(String s:newTokensList){
            stringBuilder.append(s+"\n");
            if(cnt%10000==0){
                FileHelper.outputToFile("D:\\BIT\\BadMethodName\\FilteredParsedTokens.txt",stringBuilder,true);
                stringBuilder.setLength(0);
            }
            cnt++;
        }
        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\FilteredParsedTokens.txt",stringBuilder,true);
    }
    public static void parseMethodContextTokens(String basePath) {
        StringBuilder parsedTokens = new StringBuilder();
        BufferedReader bufferedReader = null;
        try{
            FileReader fileReader = new FileReader(new File(basePath + "beforeParsedMethodContextTokens.txt"));
            bufferedReader = new BufferedReader(fileReader);
            int line = 1;
            String tempString = null;
            while ((tempString = bufferedReader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                String [] array = tempString.split(" ");
                System.out.println("number of Tokens in line: "+ line + " " + array.length);
//                MethodNameParser parser = new MethodNameParser();
//                TokenSplitUtil tokenSplitUtil = new TokenSplitUtil();
                int cnt =0;
                for(String s:array){
//                    parsedTokens.append(parser.parseWithGenTest(s));
                    if(cnt!=array.length-1)
//                        parsedTokens.append(TokenSplitUtil.split(s)+" ");
                        parsedTokens.append(new MethodNameParser().parseMethodName(s));
                    else
//                        parsedTokens.append(TokenSplitUtil.split(s));
                        parsedTokens.append(new MethodNameParser().parseMethodName(s));
                    cnt++;
                }
                parsedTokens.append("\n");
                if (line % 100 == 0) {
//                    FileHelper.outputToFile(basePath + "parsedMethodContextTokens.txt",parsedTokens,true);
                    FileHelper.outputToFile(basePath + "parsedMethodContextTokens_splitter.txt",parsedTokens,true);
                    parsedTokens.setLength(0);
                }
                line++;
            }
//            FileHelper.outputToFile(basePath + "parsedMethodContextTokens.txt",parsedTokens,true);
            FileHelper.outputToFile(basePath + "parsedMethodContextTokens_splitter.txt",parsedTokens,true);
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void parseMethodNameTokens(String basePath){
        StringBuilder parsedTokens = new StringBuilder();
        BufferedReader bufferedReader = null;
        try{
            FileReader fileReader = new FileReader(new File(basePath + "beforeParsedMethodNameTokens.txt"));
            bufferedReader = new BufferedReader(fileReader);
            int line = 1;
            String tempString = null;
            while ((tempString = bufferedReader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                String [] array = tempString.split(" ");
                System.out.println("number of Tokens in line: "+ line + " " + array.length);
//                MethodNameParser parser = new MethodNameParser();
//                TokenSplitUtil tokenSplitUtil = new TokenSplitUtil();
                for(String s:array){
//                    parsedTokens.append(parser.parseWithGenTest(s));
                    parsedTokens.append(new MethodNameParser().parseMethodName(s));
                }
                parsedTokens.append("\n");
                if (line % 100 == 0) {
//                    FileHelper.outputToFile(basePath + "parsedMethodNameTokens.txt",parsedTokens,true);
                    FileHelper.outputToFile(basePath + "parsedMethodNameTokens_splitter.txt",parsedTokens,true);
                    parsedTokens.setLength(0);
                }
                line++;
            }
//            FileHelper.outputToFile(basePath + "parsedMethodNameTokens.txt",parsedTokens,true);
            FileHelper.outputToFile(basePath + "parsedMethodNameTokens_splitter.txt",parsedTokens,true);
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    public static void outputTokens(String basePath) throws FileNotFoundException {

//        String parsedMethodPath = basePath + "result_Override_filtered.txt";
        String parsedMethodPath = basePath + "new_result.txt";
        FileInputStream fis1 = new FileInputStream(parsedMethodPath);
        Scanner scanner1 = new Scanner(fis1);
        StringBuilder extractedMethodContextInfo = new StringBuilder();
        StringBuilder extractedMethodNameInfo = new StringBuilder();
        while(scanner1.hasNext()){
            String bodyTokens;
            String line = scanner1.nextLine();
            String [] splitArray = line.split(":");
            if(splitArray.length==6) {
                bodyTokens = splitArray[5];
            }
            else{
                bodyTokens = "";
            }
                String className = splitArray[2];
                String methodName = splitArray[3];
                String parAndReturnType = splitArray[4];
                extractedMethodContextInfo.append(className + " ");
                extractedMethodContextInfo.append(parAndReturnType+ " ");
                extractedMethodContextInfo.append(bodyTokens+"\n");
                extractedMethodNameInfo.append(methodName+"\n");

        }
        FileHelper.outputToFile(basePath + "beforeParsedMethodContextTokens.txt",extractedMethodContextInfo,false);
        FileHelper.outputToFile(basePath + "beforeParsedMethodNameTokens.txt",extractedMethodNameInfo,false);
    }
}
