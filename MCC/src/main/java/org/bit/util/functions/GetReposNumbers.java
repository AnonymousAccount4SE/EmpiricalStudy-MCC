/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;

import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class GetReposNumbers {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath = "E:\\BIT\\BadMethodName\\NegativeItemSet\\Output\\";
        String JavaFiles = basePath + "JavaFiles.txt";
        ArrayList<String> files = ParserMethodNameMain.getLines(JavaFiles);
        HashSet<String> set = new HashSet<>();
        for(String file:files){
            String reposName = file.split("\\\\")[0];
            set.add(reposName);
        }
        System.out.println(set.size());

    }
}
