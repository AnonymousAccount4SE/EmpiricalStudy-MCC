/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/3/30
 */

import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ICSE2020_PredictedWrongInfo {
    public static void main(String [] args) throws FileNotFoundException {
        String basePath = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\";
        getPredictedWrongInfo(basePath);
    }
    public static void getPredictedWrongInfo(String basePath) throws FileNotFoundException {
        String allGeneratedWrongFile = basePath + "allGeneratedFalse.txt";
        ArrayList<String> allGeneratedWrongLines = ParserMethodNameMain.getLines(allGeneratedWrongFile);
        HashMap<Integer,Integer> map = new HashMap<>();
        for(String line:allGeneratedWrongLines){
            String originalName = line.split(":")[1];
            String generatedName = line.split(":")[0];
            List<String> originalNameTokens = new ArrayList<String>(Arrays.asList(originalName.split(" ")));
            List<String> generatedNameTokens = new ArrayList<String>(Arrays.asList(generatedName.split(" ")));
            originalNameTokens.retainAll(generatedNameTokens);
            int rightTokenNumber = originalNameTokens.size();
            if(map.containsKey(rightTokenNumber)){
                int num = map.get(rightTokenNumber);
                map.put(rightTokenNumber,num+1);
            }
            else{
                map.put(rightTokenNumber,1);
            }

        }
        System.out.println(allGeneratedWrongLines.size());
        System.out.println(map);
    }
}
