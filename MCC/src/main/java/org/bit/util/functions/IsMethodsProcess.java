/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/10
 */

import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class IsMethodsProcess {
    public static void main(String [] args) throws FileNotFoundException {
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\";
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\consistent\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\inconsistent\\";
        getPredictSucOrFailNumber(basePath);


    }

    private static void getPredictSucOrFailNumber(String basePath) throws FileNotFoundException {
//        private static final DecimalFormat FORMAT = new DecimalFormat("0.000");
//        String resultFile = basePath + "real_more_Top40.txt";
        String resultFile = basePath + "Top1_PredictResult.txt";
//        String resultFile = basePath + "consistentFailAnalysis_Top1.txt";
        FileInputStream fis1 = new FileInputStream(resultFile);
        ArrayList<StringBuilder> successArrayList = new ArrayList<>();
        ArrayList<StringBuilder> failArrayList = new ArrayList<>();

        ArrayList<String> getSetArrayList = new ArrayList<>();
        ArrayList<String> getSet0ArrayList = new ArrayList<>();
        ArrayList<String> getSet1ArrayList = new ArrayList<>();

        ArrayList<String> inconsistentSuccessArrayList = new ArrayList<>();
        ArrayList<String> consistentSuccessArrayList = new ArrayList<>();
        ArrayList<String> inconsistentFailArrayList = new ArrayList<>();
        ArrayList<String> inconsistentGetSetSuccessArrayList = new ArrayList<>();
        ArrayList<String> consistentGetSetSuccessArrayList = new ArrayList<>();

        HashMap<String,Integer> successFirstTokenMap = new HashMap<>();
        HashMap<String,Integer> failFirstTokenMap = new HashMap<>();
        Scanner scanner1 = new Scanner(fis1);
        while(scanner1.hasNext()){
            String line = scanner1.nextLine();
            String firstToken = Utils.getFirstToken(line);
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);


            if(originalTag.equals(predictedTag)&&originalTag.equals("0")){
                inconsistentSuccessArrayList.add(line);
            }
            if(originalTag.equals(predictedTag)&&originalTag.equals("1")){
                consistentSuccessArrayList.add(line);
            }
            if(!originalTag.equals(predictedTag)&&originalTag.equals("0")){
                inconsistentFailArrayList.add(line);
            }


            if(firstToken.equalsIgnoreCase("add")){
                getSetArrayList.add(line);
//                System.out.println(line);
                if(originalTag.equals("0")){
                    getSet0ArrayList.add(line);
                }
                else{
                    getSet1ArrayList.add(line);
                }


                // predict successfully
                if(originalTag.equals(predictedTag)){
                    if(originalTag.equals("0")){
                        inconsistentGetSetSuccessArrayList.add(line);
                    }
                    else{
                        consistentGetSetSuccessArrayList.add(line);
                    }
//                    System.out.println(originalTag + ":"+predictedTag);
                    if(successFirstTokenMap.containsKey(firstToken)){
                        int value = successFirstTokenMap.get(firstToken);
                        successFirstTokenMap.put(firstToken,value+1);
                    }
                    else{
                        successFirstTokenMap.put(firstToken,1);
                    }
                    successArrayList.add(new StringBuilder().append(line).append("\n"));
                }
                // predict failed
                else{
                    if(failFirstTokenMap.containsKey(firstToken)){
                        int value = failFirstTokenMap.get(firstToken);
                        failFirstTokenMap.put(firstToken,value+1);
                    }
                    else{
                        failFirstTokenMap.put(firstToken,1);
                    }
                    failArrayList.add(new StringBuilder().append(line).append("\n"));
                }
            }

        }


        System.out.println("total get&set method numbers:"+getSetArrayList.size());
        System.out.println("consistent get&set method numbers:"+getSet1ArrayList.size());
        System.out.println("inconsistent get&set method numbers:"+getSet0ArrayList.size());

        System.out.println("consistent predicted success:"+consistentSuccessArrayList.size());
        System.out.println("inconsistent predicted success:"+inconsistentSuccessArrayList.size());

        System.out.println("consistent get set predicted success:"+consistentGetSetSuccessArrayList.size());
        System.out.println("inconsistent get set predicted success:"+inconsistentGetSetSuccessArrayList.size());
//        System.out.println(failFirstTokenMap);
    }
}
