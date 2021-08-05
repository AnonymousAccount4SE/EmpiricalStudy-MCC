/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.heuristic;/*
 *   @author Michael
 *   @create 2021/3/16
 */

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.javaparser.ParserMethodNameMain;
import org.bit.util.javaparser.util.TokenSplitUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReSplitTrainingTokens {
    public static void main(String [] args) throws FileNotFoundException {
//        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
        String basePath = "E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\";
        String bodyTokensFile = basePath + "allMethodTokens.txt";
//        String bodyTokensFile = basePath + "TrainingMethodBodyTokens.txt";
        ArrayList<String> tokens = ParserMethodNameMain.getLines(bodyTokensFile);
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(String token : tokens){
            sb.append(reSplit(token));
            if(cnt%5000 ==0){
                FileHelper.outputToFile(basePath + "newTestingMethodBodyTokens.txt",sb,true);
                sb.setLength(0);
            }
            cnt++;
        }
        FileHelper.outputToFile(basePath + "newTestingMethodBodyTokens.txt",sb,true);
    }
    public static String reSplit(String s){
//        if(s.contains("LabeledStatement")){
//            System.out.println(s);
//            s = s.replaceFirst("LabeledStatement ","");
//            System.out.println(s);
//        }
//        else if(s.charAt(0)=='='){
//            System.out.println(s);
//            s = s.replaceFirst("= ","");
//            System.out.println(s);
//        }
//        else{
//
//        }
//
        List<String> splitList = Arrays.asList(s.split(" "));
        int cnt = 0;String result ="";
        if(splitList.size()%2!=0){
            if(s.contains("LabeledStatement")){
//            System.out.println(s);
                s = s.replaceFirst("LabeledStatement ","");
//            System.out.println(s);
            }
            else if(s.charAt(0)=='=') {
//                System.out.println(s);
                s = s.replaceFirst("= ", "");
//                System.out.println(s);
            }
            else{

            }
            List<String> splitList1 = Arrays.asList(s.split(" "));
            for(String str:splitList1){
                if(cnt %2==0){
                    result = result + str + " ";
                }
                else{
                    result = result + TokenSplitUtil.split(str) + " ";
                }
                cnt++;
            }
            return result + "\n";
        }
        else{
            for(String str:splitList){
                if(cnt %2==0){
                    result = result + str + " ";
                }
                else{
                    result = result + TokenSplitUtil.split(str) + " ";
                }
                cnt++;
            }
            return result + "\n";
        }



    }
}
