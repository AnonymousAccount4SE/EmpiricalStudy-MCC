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

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ICSE2019_filterDelAndRenamedMethods {
    public static void main (String [] args) throws FileNotFoundException {
        String basePath1 = "E:\\BIT\\BadMethodName\\NegativeItemSet\\Output\\RenamedMethods\\";
        String delFile = basePath1 + "DeletedMethodsCollection.txt";
        String renamedMethodsFile = basePath1 + "RenamedMethodsCollection.txt";
        String basePath2 = "E:\\BIT\\BadMethodName\\NegativeItemSet\\Output\\DL_Data_LT94\\SelectedData\\";
        String methodInfoFile = basePath2 + "SelectedMethodInfo.txt";
        String methodTokensFile = basePath2 + "SelectedMethodTokens.txt";

        ArrayList<String> deletedMethods = ParserMethodNameMain.getLines(delFile);
        ArrayList<String> renamedMethods = ParserMethodNameMain.getLines(renamedMethodsFile);
        ArrayList<String> methodInfos = ParserMethodNameMain.getLines(methodInfoFile);
        ArrayList<String> methodTokens = ParserMethodNameMain.getLines(methodTokensFile);

        ArrayList<String> deletedMethodsInfos = new ArrayList<>();
        ArrayList<String> renamedMethodsInfos = new ArrayList<>();
        ArrayList<Integer> filteredIndex = new ArrayList<>();

        for(String s:deletedMethods){
            String [] infos = s.split(":");
            int lastDot = infos[3].lastIndexOf(".");
            int penultimateDot = infos[3].lastIndexOf(".",lastDot-1);
            String className = infos[3].substring(penultimateDot+1,lastDot);
            String packageName = infos[3].substring(0,penultimateDot);
//            String info = infos[0] + ":" + packageName + ":" + className + ":" + infos[4].substring(0,infos[4].indexOf(","))
//                    + ":" + infos[8] + ":" + infos[7];
            String info = infos[0] + ":" + packageName + ":" + className + ":" + infos[4].substring(0,infos[4].indexOf(","));
//            System.out.println(info);
            deletedMethodsInfos.add(info);
        }

        for(String s:renamedMethods){
            String [] infos = s.split(":");
            int lastDot = infos[3].lastIndexOf(".");
            int penultimateDot = infos[3].lastIndexOf(".",lastDot-1);
            String className = infos[3].substring(penultimateDot+1,lastDot);
            String packageName = infos[3].substring(0,penultimateDot);
//            String info = infos[0] + ":" + packageName + ":" + className + ":" + infos[4].substring(0,infos[4].indexOf("@"))
//                    + ":" + infos[9] + ":" + infos[8];
            String info = infos[0] + ":" + packageName + ":" + className + ":" + infos[4].substring(0,infos[4].indexOf("@"));
//            System.out.println(info);
            renamedMethodsInfos.add(info);
        }

        StringBuilder infoBuilder = new StringBuilder();
        StringBuilder tokenBuilder = new StringBuilder();
        for(int i=0; i<methodInfos.size();i++){
            String [] infos = methodInfos.get(i).split(":");
            String info0 = infos[0].split("@")[1];
            String projectName = info0.substring(0,info0.length()-4);
            String packageName = infos[1];
            String className = infos[2];
            String methodName = infos[3];
            String argument = infos[4];
            String returnValue = infos[5].substring(0,infos[5].indexOf("@"));

            String parsedMethodNameLine = methodInfos.get(i).replaceAll("@","#");
            String parsedMethodName = parsedMethodNameLine.substring(parsedMethodNameLine.lastIndexOf("#")+1);
            String info1 = projectName + ":" + packageName + ":" + className + ":" + methodName + ":" + argument + ":"
                    + returnValue + ":" + parsedMethodName;
            String info2 = projectName + ":" + packageName + ":" + className + ":" + methodName;
            System.out.println(info1);
            if(deletedMethodsInfos.contains(info2)||renamedMethodsInfos.contains(info2)){
                filteredIndex.add(i);
//                System.out.println(info);
            }
            else{
                infoBuilder.append(info1+"\n");
                if(i%2000==0){
                    FileHelper.outputToFile(basePath2 + "filteredSelectedMethodInfo.txt",infoBuilder,true);
                    infoBuilder.setLength(0);
                }
            }
         }
        FileHelper.outputToFile(basePath2 + "filteredSelectedMethodInfo.txt",infoBuilder,true);
        infoBuilder.setLength(0);

        for(int i=0;i<methodTokens.size();i++){
            if(filteredIndex.contains(i)){
//                System.out.println("filtered!");
            }
            else{
                tokenBuilder.append(methodTokens.get(i)+"\n");
                if(i%2000==0){
                    FileHelper.outputToFile(basePath2 + "filteredSelectedMethodTokens.txt",tokenBuilder,true);
                    tokenBuilder.setLength(0);
                }
            }
        }

        FileHelper.outputToFile(basePath2 + "filteredSelectedMethodTokens.txt",tokenBuilder,true);
        tokenBuilder.setLength(0);

        System.out.println("deletedMethods:"+deletedMethodsInfos.size());
        System.out.println("RenamedMethods:"+renamedMethodsInfos.size());
        System.out.println("filteredMethods:"+filteredIndex.size());

    }
}
