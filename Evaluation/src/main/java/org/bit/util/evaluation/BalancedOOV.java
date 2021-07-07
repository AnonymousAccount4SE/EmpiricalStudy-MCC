/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.evaluation;/*
 *   @author Michael
 *   @create 2021/4/20
 */

import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class BalancedOOV {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\";
        getOOVRatio_original(basePath);
        getOOVRatio_balanced(basePath);
    }

    private static void getOOVRatio_balanced(String basePath) throws FileNotFoundException {
        String trainingDataPath = basePath + "TrainingData\\methodNames.txt";
        String testBasePath = "D:\\BIT\\BadMethodName\\NewTestDataSet\\ICSE2020\\notReal\\1_1\\";
        String consistentMethodNamePath = testBasePath + "ConsistentData\\parsedMethodNameTokens.txt";
        String inconsistentMethodNamePath = testBasePath + "InconsistentData\\parsedMethodNameTokens.txt";
        ArrayList<String> trainingMethods = ParserMethodNameMain.getLines(trainingDataPath);
        ArrayList<String> inconsistentMethods = ParserMethodNameMain.getLines(inconsistentMethodNamePath);
        ArrayList<String> consistentMethods = ParserMethodNameMain.getLines(consistentMethodNamePath);

        int cnt =0;
        for(String methodName:inconsistentMethods){
            if(trainingMethods.contains(methodName)){
                cnt++;
            }
        }
        System.out.println(consistentMethods.size());
        System.out.println(inconsistentMethods.size());
        System.out.println(cnt);
        System.out.println(cnt*1.0/ inconsistentMethods.size()*100);
        cnt = 0;
        for(String methodName:consistentMethods){
            if(trainingMethods.contains(methodName)){
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(cnt*1.0/ consistentMethods.size()*100);
    }

    private static void getOOVRatio_original(String basePath) throws FileNotFoundException {
        String trainingDataPath = basePath + "TrainingData\\methodNames.txt";
        String testMethodNamePath = basePath + "TestData\\TestMethodNames.txt";
        String testLabelPath = basePath + "TestData\\TestLabels.txt";
        ArrayList<String> trainingMethods = ParserMethodNameMain.getLines(trainingDataPath);
        ArrayList<String> testMethods = ParserMethodNameMain.getLines(testMethodNamePath);
        ArrayList<String> testLabels = ParserMethodNameMain.getLines(testLabelPath);
        ArrayList<String> inconsistentNames = new ArrayList<>();
        ArrayList<String> consistentNames = new ArrayList<>();
        for(int i = 0; i<testLabels.size();i++){
            String label = testLabels.get(i);
            String testMethod = testMethods.get(i);
            if(label.equals("0")){
                inconsistentNames.add(testMethod);
            }
            else{
                consistentNames.add(testMethod);
            }
        }
        System.out.println(trainingMethods.size());
        System.out.println(inconsistentNames.size());
        System.out.println(consistentNames.size());

        int cnt =0;
        for(String methodName:inconsistentNames){
            if(trainingMethods.contains(methodName)){
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(cnt*1.0/ inconsistentNames.size()*100);
        cnt = 0;
        for(String methodName:consistentNames){
            if(trainingMethods.contains(methodName)){
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(cnt*1.0/ consistentNames.size()*100);

    }
}
