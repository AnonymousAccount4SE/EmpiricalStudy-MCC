package org.bit.util.javaparser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Create By IntelliJ IDEA
 * Author LikeJun
 * Date 2020/12/17
 * File: ProcessMain.java
 */

public class ProcessMain {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("data/name_token_result.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("data/" + "parser_result_new.txt", true));

        String str;
        int length = 0;
        while ((str = in.readLine()) != null) {

            if (str.contains("Test") || str.contains("test") ||str.contains("main") || str.contains("example") || str.contains("template") || str.contains("sample")) {
                length++;
            }

//            String[] split = str.split(":");
//            if (split.length == 6) {
//                out.write(str + "\n");
//                length++;
//            }
        }

        in.close();
        System.out.println(length);
    }
}
