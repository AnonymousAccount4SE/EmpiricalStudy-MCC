/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/9
 */

import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class ICSE2020_ProjectNumber {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath = "E:\\Workspace\\ICSE2020\\TestData\\Positive\\";
        getProjectNumber(basePath);
    }
    public static void getProjectNumber(String basePath) throws FileNotFoundException {
        String javaFiles = basePath + "JavaFiles_46.txt";
        ArrayList<String> lines = ParserMethodNameMain.getLines(javaFiles);
        HashSet<String> set = new HashSet<>();
        for(String line:lines){
            String projectName = line.substring(0,line.indexOf("/"));
            System.out.println(projectName);
            set.add(projectName);
        }
        System.out.println(set.size());

    }
}
