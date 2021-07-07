package org.bit.util.javaparser;

import org.bit.util.javaparser.util.JavaParserUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Create By IntelliJ IDEA
 * Author LikeJun
 * Date 2020/12/16
 * File: Main.java
 */

public class ParserMain {
    public static String projectPath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\NewJavaRepos";
    public static List<String> list = new ArrayList<String>();


    public static void main(String[] args) throws IOException {

//        String basePath = "E:\\Workspace\\icse2020\\TestData\\Negative\\RenamedMethods_original\\";
        String basePath = "E:\\Workspace\\icse2020\\TestData\\Positive\\";
//        preprocessNegativeSample(basePath);
        preprocessPositiveSample(basePath);

//    public static void main(String[] args) {



//        File file = new File(projectPath);
//        File[] fs = file.listFiles();
//        for (File f : fs) {
//            if (f.isDirectory()) {
//                System.out.println(f.getName());
//                func(f);
//                System.out.println(list.size());
//
//                try {
//                    BufferedWriter out = new BufferedWriter(new FileWriter("data/" + f.getName() + ".txt"));
//                    for (String s : list) {
//                        out.write(s);
//                    }
//                    out.close();
//                } catch (IOException e) {
//
//                }
//                list.clear();
//            }
//        }


    }

    public static void preprocessPositiveSample(String basePath) {
        int length = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(basePath + "JavaFiles.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(projectPath + str);
                String projectName = str.split("/")[1];
                length++;
                try {
                    List<String> parserResult = JavaParserUtil.parser(new File(projectPath + str));
//                    System.out.println(parserResult.size());

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

    public static void preprocessNegativeSample(String basePath) throws IOException {
        File [] projectList = new File(basePath).listFiles();
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




//            for(int i =0 ;i <classNames.size(); i++){
//                BufferedWriter out = null;
//                try {
//                    String code = "class " + classNames.get(i) + "{" +
//                            methodBodies.get(i) + "}";
////                    System.out.println(code);
//                    List<String> parserResult = JavaParserUtil.parser(code);
////                    System.out.println(parserResult.size());
//
//                    out = new BufferedWriter(new FileWriter("E:\\Workspace\\icse2020\\TestData\\result.txt", true));
////                    System.out.println(projectNames.get(i)+":"+classNames.get(i));
//                    for (String s : parserResult) {
//                        out.write(projectNames.get(i) + ":" +classNames.get(i)+":"+ methodNames.get(i)+":"+ s);
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                finally{
//                    out.close();
//                }
//            }

    }
        System.out.println(methodNumber);
}

//    private static void func(File file) {
//        File[] fs = file.listFiles();
//        for (File f : fs) {
//            if (f.isDirectory() && isNotTestFile(f)) {
//                func(f);
//            }
//
//            if (f.isFile() && isNotTestFile(f)) {
//                try {
//                    list.addAll(JavaParserUtil.parser(f.getAbsoluteFile()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    private static boolean isNotTestFile(File file) {

        if (file.isDirectory()) {
            String name = file.getName();
            return !name.contains("Test") && !name.contains("test");
        } else {
            String name = file.getName();
            if (name.contains("Test") || name.contains("test")) {
                return false;
            }
            return name.endsWith("java");
        }
    }

}
