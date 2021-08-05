/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DeleteSpecificDirectories {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath = "E:/BIT/BadMethodName/NegativeItemSet/NewJavaRepos/";
        String repos = basePath + "repos.txt";
        List<File> fileList = FileHelper.getAllSubDirectories(basePath);
        ArrayList<String> reposList = ParserMethodNameMain.getLines(repos);
        for(File file : fileList){
            if(reposList.contains(file.getName())){
                System.out.println(file.getName());
            }
            else{
                FileHelper.deleteDirectory(file.getAbsolutePath());
                System.out.println("delete:"+file.getAbsolutePath());
            }
        }

    }
}
