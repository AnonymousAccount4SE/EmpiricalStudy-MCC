/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/5
 */

import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class ReturnStatementProcess {

    public static void main(String [] args) throws FileNotFoundException {
        getTrainingDataReturnStatement();

    }
    public static void getFirstToken() throws FileNotFoundException {
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\";
//        String inconsisPath = basePath + "DL_Data\\RenamedMethods\\MethodTokens.txt";
        String inconsisPath = basePath + "real_more\\DL_Data\\RenamedMethods\\MethodTokens_1.txt";

//        String consisPath = basePath + "DL_Data\\SelectedData\\SelectedMethodTokens.txt";
        String consisPath = basePath + "real_more\\DL_Data\\SelectedData\\SelectedMethodTokens_1.txt";

        String Top1Result = basePath + "real_more_unique\\Top1_PredictResult.txt";
//        String Top1Result = basePath + "real_more_unique\\consistentSuccessNotGetSetAnalysis_Top1.txt";
//        String Top1Result = basePath + "real_more_unique\\consistentSuccessAnalysis_Top1.txt";
        ArrayList<String> inconsistentList = ParserMethodNameMain.getLines(inconsisPath);
        ArrayList<String> consistentList = ParserMethodNameMain.getLines(consisPath);

        ArrayList<Integer> inconsistentMap = new ArrayList<>();
        ArrayList<Integer> consistentMap = new ArrayList<>();
        int cnt1 =0 ,cnt2=0;
        for(String inconsis:inconsistentList){
            if(inconsis.startsWith("ReturnStatement")){
//                System.out.println(inconsis);
                inconsistentMap.add(1);
                cnt1++;
            }
            else{
                inconsistentMap.add(0);
            }
        }
//        System.out.println(inconsistentMap);
//        System.out.println(inconsistentMap.size());
        for(String consis:consistentList){
            if(consis.startsWith("ReturnStatement")){
//                System.out.println(consis);
                consistentMap.add(1);
                cnt2++;
            }
            else{
                consistentMap.add(0);
            }
        }
//        System.out.println(consistentMap);
//        System.out.println(cnt1);
        System.out.println(cnt2);
//        System.out.println(consistentMap.size());

        ArrayList<String> results = ParserMethodNameMain.getLines(Top1Result);

        ArrayList<String> returnInconsisMethods = new ArrayList<>();
        ArrayList<String> returnConsisMethods = new ArrayList<>();

        for(int i=0;i<results.size();i++){
            String result = results.get(i);
            int index = Utils.getIndice(result);
            int originalTagIndex = result.indexOf("originalTag:");
            int predictedTagIndex = result.indexOf("predictedTag:");
            String originalTag = result.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = result.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
            if(index<2303){
                if(originalTag.equals(predictedTag)){
                    if(inconsistentMap.get(index)==1){
//                        System.out.println(i+":"+inconsistentMap.get(i));
                        returnInconsisMethods.add(result);
                    }
                }
            }
            else{
                if(originalTag.equals(predictedTag)){
                    if(consistentMap.get(index-2303)==1){
//                        System.out.println(i+":"+consistentMap.get(i));
                        returnConsisMethods.add(result);
                    }
                }
            }
        }
//        System.out.println(returnInconsisMethods.size());
        System.out.println(results.size());
        System.out.println(returnConsisMethods.size());
    }
    public static void getTrainingDataReturnStatement() throws FileNotFoundException {
//        String filePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\TrainingMethodInfo.txt";
//        String filePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\TrainingMethodBodyTokens.txt";
        String filePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\TrainingMethodBodyTokens.txt";
        int cnt = 0;
        ArrayList<String> tokens = ParserMethodNameMain.getLines(filePath);
        for(String token:tokens){
            String [] splitArray = token.split(" ");
            if(splitArray[0].equalsIgnoreCase("returnstatement")){
                cnt++;
            }
        }
//        HashMap<String,Integer> firstTokenAndNumber = ICSE2019_firstTokenNumber.getFirstTokenNumber(filePath);
//        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(firstTokenAndNumber.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
//            public int compare(Map.Entry<String, Integer> o1,
//                               Map.Entry<String, Integer> o2) {
//                return (o2.getValue() - o1.getValue());
//            }
//        });
//        System.out.println(list);
        System.out.println(cnt);
    }
}
