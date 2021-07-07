/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.heuristic;/*

 *   @author Michael
 *   @create 2021/1/18
 */

import java.io.FileNotFoundException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.*;
import org.bit.util.javaparser.ParserMethodNameMain;

public class GetMethodNameFromInfoFile {


    public static void main(String [] args) throws FileNotFoundException {
        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
//        getInconsistentMethodNames(basePath);
        getConsistentMethodNames(basePath);
    }

    public static void getConsistentMethodNames(String basePath) throws FileNotFoundException {
//        String methodInfoFile = basePath + "TrainingMethodInfo.txt";
//        String methodInfoFile = basePath + "Inconsistent\\filteredParsedMethodNames.txt";
        String methodInfoFile = basePath + "Consistent\\filteredSelectedMethodInfo_1.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodInfoFile);
        StringBuilder methodNames = new StringBuilder();
        for(String line:lines){
            String [] splitArray = line.split(":");
            String methodName = splitArray[splitArray.length-1];
            methodNames.append(methodName.replaceAll(","," ").toLowerCase()+"\n");
        }
//        FileHelper.outputToFile(basePath +"Inconsistent\\methodNames.txt",methodNames,false);
        FileHelper.outputToFile(basePath +"Consistent\\methodNames.txt",methodNames,false);
    }

    private static void getInconsistentMethodNames(String basePath) throws FileNotFoundException {
        String methodInfoFile = basePath + "Inconsistent\\filteredParsedMethodNames.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(methodInfoFile);
        StringBuilder methodNames = new StringBuilder();
        for(String line:lines){
            String [] splitArray = line.split("@");
//            String methodName = splitArray[splitArray.length-1];
            String methodName = splitArray[0];
            methodNames.append(methodName.replaceAll(","," ").toLowerCase()+"\n");
        }
        FileHelper.outputToFile(basePath +"Inconsistent\\methodNames.txt",methodNames,false);
    }
}
