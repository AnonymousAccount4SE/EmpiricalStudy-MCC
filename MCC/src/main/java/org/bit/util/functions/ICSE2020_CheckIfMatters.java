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
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ICSE2020_CheckIfMatters {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath1 = "E:\\BIT\\BadMethodName\\CheckIfMatters\\real_more\\DL_Data_methodInfo_Tokens\\RenamedMethods\\";
        String basePath2 = "E:\\BIT\\BadMethodName\\ICSE2020DataSet\\TestData\\Inconsistent\\";
        checkIfMatters(basePath1,basePath2);

//        String basePath = "E:\\BIT\\BadMethodName\\ICSE2020DataSet\\TestData\\Inconsistent\\RenamedMethods_more\\";
//        assembleAllRenamedMethods(basePath);
    }

    public static void assembleAllRenamedMethods(String basePath) throws FileNotFoundException {
        List<File> projectList = FileHelper.getAllSubDirectories(basePath);
        System.out.println(projectList.size());
        StringBuilder methodTokenBuilder = new StringBuilder();
        for(File f : projectList){
            String filePath = f.getAbsolutePath()+"\\ActualRenamed\\MethodTokens.txt";
//            System.out.println(filePath);
            ArrayList<String> methodTokens = ParserMethodNameMain.getLines(filePath);
            for(String methodToken : methodTokens){
                methodTokenBuilder.append(methodToken+"\n");
            }
        }
//        System.out.println(methodTokenBuilder);
        FileHelper.outputToFile(basePath+"allRenamedMethods.txt",methodTokenBuilder,false);
    }
    public static void checkIfMatters(String basePath1,String basePath2) throws FileNotFoundException {
        String methodInfoFile = basePath1 + "MethodInfo.txt";
        String parsedMethodNamesFile = basePath1 + "ParsedMethodNames.txt";
        String new_result = basePath2 + "new_result.txt";
        ArrayList<String> methodInfoList = ParserMethodNameMain.getLines(methodInfoFile);
        ArrayList<String> parsedMethodNamesList = ParserMethodNameMain.getLines(parsedMethodNamesFile);

        ArrayList<String> new_resultList = ParserMethodNameMain.getLines(new_result);
        ArrayList<String> methodInfoIdList = new ArrayList<>();
        int cnt = 0;
        for(String methodInfo : methodInfoList){
            int lastSemicolon = methodInfo.lastIndexOf(":");
            int penultimateSemicolon = methodInfo.lastIndexOf(":",lastSemicolon-1);
            int lastThirdSemicolon = methodInfo.lastIndexOf(":",penultimateSemicolon-1);

            String methodInfoId = methodInfo.substring(0,lastThirdSemicolon);
            methodInfoId = methodInfoId + ":" + parsedMethodNamesList.get(cnt).split("@")[0].replaceAll(",","");
//            System.out.println(cnt+" : "+methodInfoId);
            methodInfoIdList.add(methodInfoId);
            cnt++;
        }
        System.out.println(methodInfoIdList.size());

        int contain2Cnt = 0;
        for(int i=0;i<new_resultList.size();i++){
            String resultLine = new_resultList.get(i);
            int lastSemicolon = resultLine.lastIndexOf(":");
            int penultimateSemicolon = resultLine.lastIndexOf(":",lastSemicolon-1);
            String resultId = resultLine.substring(0,penultimateSemicolon);
//            System.out.println(i+" : "+resultId);
            if(methodInfoIdList.contains(resultId)){
                System.out.println(i+" : "+resultId);
                contain2Cnt++;
            }
        }
        System.out.println(contain2Cnt);
    }
}
