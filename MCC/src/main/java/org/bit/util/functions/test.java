/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/24
 */
import org.bit.util.*;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class test {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath3 = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\";
        String basePath4 = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
        IsFalseOOV_19(basePath3,basePath4);
    }

    public static void IsFalseOOV_19(String basePath1,String basePath2) throws FileNotFoundException {
        String correctFile = basePath1 + "consistentFailAnalysis_Top1.txt";
//        String correctFile = basePath1 + "inconsistentFailAnalysis_Top1.txt";

        String trainingFile = basePath2 + "methodNames.txt";
        int cnt = 0;
        ArrayList<String> correctNamesList = ParserMethodNameMain.getLines(correctFile);
        ArrayList<String> trainingNamesList = ParserMethodNameMain.getLines(trainingFile);
        for (String correctName : correctNamesList) {
            int leftSquareBracketsIndex = correctName.indexOf("[");
            int rightSquareBracketsIndex = correctName.indexOf("]");

            String name = correctName.substring(leftSquareBracketsIndex + 1, rightSquareBracketsIndex).replaceAll(",", " ");
            System.out.println(name);
            if (trainingNamesList.contains(name.trim())) {
                cnt++;
            }
        }
        System.out.println(correctNamesList.size());
        System.out.println("InV:" + cnt);
        System.out.println("OOV:" + String.valueOf(correctNamesList.size() - cnt));
    }
}
