/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.heuristic;/*
 *   @author Michael
 *   @create 2021/3/17
 */

import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class LineTokenNumber {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
//        String basePath = "E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\";
        getLineTokenNumber(basePath);
    }
    public static void getLineTokenNumber(String basePath) throws FileNotFoundException {
//        String file = basePath + "allMethodTokens.txt";
        String file = basePath + "newTestingMethodBodyTokens.txt";
//        String file = basePath + "TrainingMethodBodyTokens.txt";
        ArrayList<String> tokensList = ParserMethodNameMain.getLines(file);
        int cnt = 0;
        HashSet<String> set = new HashSet<>();
        for (String s : tokensList) {
            String[] splitArray = s.split(" ");
//            if (splitArray.length % 2 != 0) {
//                System.out.println(s);
////                System.out.println(splitArray.length);
//                cnt++;
//            }
//            for(String str:splitArray){
//                set.add(str);
//            }
            cnt = cnt + splitArray.length;
        }
        System.out.println(cnt);
//        System.out.println(set.size());
    }
}
