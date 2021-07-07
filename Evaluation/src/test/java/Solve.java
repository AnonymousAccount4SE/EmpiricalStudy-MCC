/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Solve {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();
        int n = 0;
        n = Integer.valueOf(scanner.next());
        ArrayList<Integer> arr = new ArrayList();
        for(int i=0;i<n*2;i++){
            arr.add(Integer.valueOf(scanner.next()));
        }
//        System.out.println(s1+s2+arr);

        for(int index = 0; index< n*2; index= index +2){
            int i = arr.get(index);
            int j = arr.get(index + 1);
//            System.out.println(i);
//            System.out.println(j);
            foo(s1,s2,i,j);
        }


    }

    private static void foo(String s1, String s2, int i, int j) {
        String sub1 = s1.substring(0,i);
        String sub2 = s2.substring(0,j);
        String result = sub1+sub2;
        System.out.println(result);
        HashSet<Character> set = new HashSet<>();
        for(int m = 0; m< result.length();m++){
            set.add(result.charAt(m));
        }
        System.out.println(set.size());
    }
}
