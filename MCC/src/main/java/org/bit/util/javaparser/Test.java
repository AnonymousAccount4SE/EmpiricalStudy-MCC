package org.bit.util.javaparser;

import org.bit.util.javaparser.util.TokenSplitUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By IntelliJ IDEA
 * Author LikeJun
 * Date 2020/12/16
 * File: Test.java
 */

public class Test {
    public static void main(String[] args) throws Exception {
        BufferedReader nameIn = new BufferedReader(new FileReader("result/name_token_result.txt"));
        BufferedReader contextIn = new BufferedReader(new FileReader("result/context_tokens_result.txt"));
        List<String> nameList = new ArrayList<String>();
        List<String> contextList = new ArrayList<String>();

        String str;
        while ((str = nameIn.readLine()) != null) {
            nameList.add(str);
        }

        while ((str = contextIn.readLine()) != null) {
            contextList.add(str);
        }

    }

    void splitToken() throws IOException {

        long start = System.currentTimeMillis();
        BufferedReader in = new BufferedReader(new FileReader("data/parser_result_new.txt"));
        List<String> dataList = new ArrayList<String>();
        List<List<String>> methodNameList = new ArrayList<>();
        List<List<String>> contextList = new ArrayList<>();

        String str;
        while ((str = in.readLine()) != null) {
            dataList.add(str);
        }

        int length = 0;
        for (String data : dataList) {
            String[] split = data.split(":");
            String methodName = split[3];
            String className = split[2];
            String interfaceName = split[4];
            String methodBody = split[5];

//            methodNameList.add(TokenSplitUtil.split(methodName));
//            List<String> context = new ArrayList<>(TokenSplitUtil.split(className));
//            for (String s : interfaceName.split(" ")) {
//                context.addAll(TokenSplitUtil.split(s));
//            }
//            for (String s : methodBody.split(" ")) {
//                context.addAll(TokenSplitUtil.split(s));
//            }
//            contextList.add(context);
            length++;
            if (length % 10000 == 0 || length == dataList.size()) {
                System.out.println(length);

                BufferedWriter out = new BufferedWriter(new FileWriter("data/name_token_result.txt", true));
                for (List<String> nowMethodName : methodNameList) {
                    StringBuilder sb = new StringBuilder();
                    for (String token : nowMethodName) {
                        sb.append(token).append(" ");
                    }
                    if (sb.length() > 0)
                        sb.deleteCharAt(sb.length() - 1);
                    sb.append("\n");
                    out.write(sb.toString());
                }
                out.close();
                methodNameList.clear();

//                out = new BufferedWriter(new FileWriter("data/context_token_result.txt", true));
//                for (List<String> nowContext : contextList) {
//                    StringBuffer sb = new StringBuffer();
//                    for (String token : nowContext) {
//                        sb.append(token).append(" ");
//                    }
//                    sb.deleteCharAt(sb.length() - 1);
//                    sb.append("\n");
//                    out.write(sb.toString());
//
//                }
//                out.close();

            }
        }
        in.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

}
