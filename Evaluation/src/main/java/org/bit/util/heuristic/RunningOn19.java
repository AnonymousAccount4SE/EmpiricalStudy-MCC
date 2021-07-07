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

public class RunningOn19 {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
//        String inconsistentMethodNames = basePath + "Inconsistent\\methodNames.txt";
        String inconsistentMethodNames = basePath + "Consistent\\methodNames.txt";
        String trainingMethodNames = basePath + "methodNames.txt";
        String trainingMethodBodyTokens = basePath + "TrainingMethodBodyTokens.txt";
//        String inconsistentMethodBodyTokens = basePath + "Inconsistent\\MethodTokens_1.txt";
        String inconsistentMethodBodyTokens = basePath + "Consistent\\SelectedMethodTokens_1.txt";
        ArrayList<String> inconsistentMethodNameList = ParserMethodNameMain.getLines(inconsistentMethodNames);
        ArrayList<String> trainingMethodNameList = ParserMethodNameMain.getLines(trainingMethodNames);
        ArrayList<String> inconsistentMethodBodyTokensList = ParserMethodNameMain.getLines(inconsistentMethodBodyTokens);
        ArrayList<String> trainingMethodBodyTokensList = ParserMethodNameMain.getLines(trainingMethodBodyTokens);
        double totalAvgSim = 0;
        ArrayList<Integer> results = new ArrayList<>();
//        System.out.println(inconsistentMethodBodyTokensList.size());
//        System.out.println(inconsistentMethodNameList.size());
        for(int i=0;i<inconsistentMethodBodyTokensList.size();i++)
        {
            ArrayList<Integer> top10SimilarIndexes = new ArrayList<>();
            int cnt = 0;
            String testMethodName = inconsistentMethodNameList.get(i);

            for(String tmn:trainingMethodNameList){
                if(tmn.equals(testMethodName)&&cnt<10){
                    top10SimilarIndexes.add(i);
                    cnt++;
                }
            }
            if(top10SimilarIndexes.isEmpty()){
                // find the first same token
                ArrayList<String> testMethodNameSplitArray = new ArrayList<>(Arrays.asList(testMethodName.split(" ")));
                String testMethodFirstToken = testMethodNameSplitArray.get(0);
//                System.out.println(testMethodFirstToken);
                for(String tmn:trainingMethodNameList){
                    ArrayList<String> trainingMethodNameSplitArray = new ArrayList<>(Arrays.asList(tmn.split(" ")));
                    String trainingMethodFirstToken = trainingMethodNameSplitArray.get(0);
                    if(testMethodFirstToken.equals(trainingMethodFirstToken)&&cnt<10){
                        top10SimilarIndexes.add(i);
                        cnt++;
                    }
                }

            }
//            System.out.println("Similar Size:" + top10SimilarIndexes.size());
            double avgSim = 0;
            double sim = 0;

            String testMethodBodyToken = inconsistentMethodBodyTokensList.get(i);

            ArrayList<String> testMethodBodyTokensSplitArray = new ArrayList<String>(Arrays.asList(testMethodBodyToken.split(" ")));
            int testMethodBodyTokenNumber = testMethodBodyTokensSplitArray.size();
            if(top10SimilarIndexes.size()==0){
                results.add(1);
                continue;
            }
            for(int j =0;j< top10SimilarIndexes.size();j++){

                String trainingMethodBodyToken = trainingMethodBodyTokensList.get(j);
                ArrayList<String> trainingMethodBodyTokensSplitArray = new ArrayList<String>(Arrays.asList(trainingMethodBodyToken.split(" ")));
                testMethodBodyTokensSplitArray.retainAll(trainingMethodBodyTokensSplitArray);
//                System.out.println(testMethodBodyTokensSplitArray.size());
                sim += 1.0*testMethodBodyTokensSplitArray.size()/testMethodBodyTokenNumber;
            }
            avgSim = sim / top10SimilarIndexes.size();
//            System.out.println("Sim:" + avgSim);
            if(avgSim<=0.6){
                results.add(0);
            }
            else{
                results.add(1);
            }
            totalAvgSim += avgSim;
        }
        totalAvgSim= totalAvgSim / inconsistentMethodBodyTokensList.size();
        System.out.println(totalAvgSim);
        System.out.println(results);
        System.out.println("result.size():"+results.size());
        double zeroCnt = 0,oneCnt=0;
        for(int result:results){
            if(result==0)
                zeroCnt++;
            else
                oneCnt++;
        }
        System.out.println(zeroCnt/results.size());
//        System.out.println("TP:"+zeroCnt);
//
//        System.out.println("FN:"+String.valueOf(oneCnt));
        System.out.println("FP:"+zeroCnt);

        System.out.println("TN:"+String.valueOf(oneCnt));

    }
}
