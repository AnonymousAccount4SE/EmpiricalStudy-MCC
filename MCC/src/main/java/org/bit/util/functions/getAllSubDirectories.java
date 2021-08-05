/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;

import edu.lu.uni.serval.utils.FileHelper;

import java.io.File;
import java.util.List;

public class getAllSubDirectories {
    public static void main(String [] args){
        String dir = "E:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\RenamedMethods\\";
        List<File> fileList = FileHelper.getAllSubDirectories(dir);
        for(File file:fileList){
            System.out.println(file.getName());
        }
    }
}
