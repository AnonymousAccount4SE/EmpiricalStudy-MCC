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

import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class RunninWithRules {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
        String inconsistentMethodNames = basePath + "Inconsistent\\methodNames.txt";
        String consistentMethodNames = basePath + "Consistent\\methodNames.txt";
//        String trainingMethodNames = basePath + "methodNames.txt";
        String trainingMethodBodyTokens = basePath + "TrainingMethodBodyTokens.txt";
//        String trainingMethodBodyTokens = basePath + "newTestingMethodBodyTokens.txt";
        String inconsistentMethodBodyTokens = basePath + "Inconsistent\\filteredMethodTokens.txt";
        String consistentMethodBodyTokens = basePath + "Consistent\\filteredSelectedMethodTokens_1.txt";

        runningWithRules(basePath,inconsistentMethodNames, inconsistentMethodBodyTokens, trainingMethodBodyTokens);
//        runningWithRules(basePath,consistentMethodNames, consistentMethodBodyTokens, trainingMethodBodyTokens);
    }

    public static void runningWithRules(String basePath, String methodNames, String methodBodyTokens,String trainingMethodBodyTokens) throws FileNotFoundException {
        ArrayList<String> inconsistentMethodNameList = ParserMethodNameMain.getLines(methodNames);
//        ArrayList<String> trainingMethodNameList = ParserMethodNameMain.getLines(trainingMethodNames);
        ArrayList<String> inconsistentMethodBodyTokensList = ParserMethodNameMain.getLines(methodBodyTokens);
//        System.out.println(inconsistentMethodBodyTokensList.size());
        ArrayList<String> trainingMethodBodyTokensList = ParserMethodNameMain.getLines(trainingMethodBodyTokens);
        double totalAvgSim = 0;
        ArrayList<Integer> results = new ArrayList<>();
//        System.out.println(inconsistentMethodBodyTokensList.size());
//        System.out.println(inconsistentMethodNameList.size());
        int getSetCnt =0,onlyReturnCnt =0, shortCnt = 0, zeroCnt=0;
        int total = inconsistentMethodBodyTokensList.size();
//        System.out.println(total);
        HashSet<String> trainingBodyTokensSet = new HashSet<>();
        for(String s:trainingMethodBodyTokensList){
            String [] splitTokens = s.split(" ");
            for(String str:splitTokens)
                trainingBodyTokensSet.add(str);
        }
        System.out.println(trainingBodyTokensSet.size());


        for(int i=0;i<inconsistentMethodBodyTokensList.size();i++)
        {
            String testMethodName = inconsistentMethodNameList.get(i);
            String testMethodBody = inconsistentMethodBodyTokensList.get(i);
            int OOVNumber = calOOVTokens(trainingBodyTokensSet,testMethodBody);
//            String [] bodySplitArray = testMethodBody.split(" ");
//            String [] nameSplitArray = testMethodName.split(" ");
//            if(testMethodName.startsWith("get")||testMethodName.startsWith("set")){
//                getSetCnt++;
//            }
//            if(testMethodBody.startsWith("ReturnStatement")){
//                onlyReturnCnt++;
//            }
//            final boolean b = false ;
//            if(b){
//                shortCnt++;
//                zeroCnt++;
//            }

            if(testMethodName.startsWith("get")||testMethodName.startsWith("set")){
////                if(testMethodBody.startsWith("ReturnStatement")){
////                    if(bodySplitArray.length<=10){
////                    if(nameSplitArray.length<=3)
//
                        zeroCnt++;
////                    }
////                }
            }
//            else if(OOVNumber>25){
//                zeroCnt++;
//            }
//            else{
//
//            }


        }
//        System.out.println("getSetCnt:"+getSetCnt);
//        System.out.println("onlyReturnCnt:"+onlyReturnCnt);
//        System.out.println("shortCnt:"+shortCnt);
//        System.out.println(zeroCnt);

        if(methodNames.equals(basePath + "Inconsistent\\methodNames.txt")){
//            System.out.println("TP:"+String.valueOf(total-zeroCnt));
//            System.out.println("FN:"+String.valueOf(zeroCnt));
        }
        else{
//            System.out.println("TN:"+zeroCnt);
//            System.out.println("FP:"+String.valueOf(total-zeroCnt));
        }
    }

    public static int calOOVTokens(HashSet<String> set ,String tokensLine){
        int cnt = 0;
        List<String> tokens = Arrays.asList(tokensLine.split(" "));
        for(String token:tokens){
            if(set.contains(token)){

            }
            else{
                cnt++;
            }
        }
        return cnt;
    }
}
