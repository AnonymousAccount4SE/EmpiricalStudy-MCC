/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/3/18
 */

import org.bit.util.javaparser.ParserMethodNameMain;
import org.bit.util.javaparser.util.JavaParserUtil;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ICSE2020_generateOriginalTestData {
    public static void main(String [] args) throws IOException {
        String basePath = "";
        generateOriginalDataSet(basePath);
    }
    public static void generateOriginalDataSet(String basePath) throws IOException {
        String filePath = basePath + "javaFilePaths.txt";
        String resultPath = basePath + "originalDataResult.txt";
        ArrayList<String> filePathList = ParserMethodNameMain.getLines(filePath);
        for(String filePathLine:filePathList){
            List<String> parserResult = JavaParserUtil.parser(filePathLine);
            System.out.println(parserResult.size());
            BufferedWriter out = null;
//                    out = new BufferedWriter(new FileWriter(basePath + "InconsistentData\\result.txt", true));
            out = new BufferedWriter(new FileWriter(resultPath, true));
//                    out = new BufferedWriter(new FileWriter(basePath + "result.txt", true));
            for (String s : parserResult) {
                out.write(s);
            }
        }


    }
}
