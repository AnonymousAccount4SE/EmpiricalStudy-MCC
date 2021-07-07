/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Question1 {
    public static void main(String [] args){
        solve();
    }
    public static void solve(){
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        n = Integer.valueOf(scanner.next());
        ArrayList<Integer> arr = new ArrayList();
        for(int i=0;i<n;i++){
            arr.add(Integer.valueOf(scanner.next()));
        }
        int k = 0;
        for(int i:arr){
            if(i>k){
                k=i;
            }
        }
        System.out.println(arr);
        int number1 = 0,number2 = 0;
        int sum = 0;
        for(; k<100000;k++){
            number1= 0;
            number2 =0;
            for(int i:arr){
                number1 +=i;
            }
            number2 = arr.size()*k-number1;
            if(number1<number2){
                System.out.println(k);
                break;
            }
        }

    }
}
