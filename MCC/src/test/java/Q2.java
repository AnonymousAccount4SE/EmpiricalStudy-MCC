/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Q2 {
    public static void main (String [] args){
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        n = Integer.valueOf(scanner.next());
        ArrayList<Integer> arr = new ArrayList();
        for(int i=0;i<n*3;i++){
            arr.add(Integer.valueOf(scanner.next()));
        }
        int  cnt = 1;
        int l=0,r=0,k=0;
        for(int i:arr){

            if(cnt%3==1){
                l=i;
            }
            if(cnt%3==2){
                r=i;
            }
            if(cnt%3==0){
                k=i;
                foo(l,r,k);
                l=0;r=0;k=0;
            }
                cnt++;
        }
        double d = 0.1211223;
        DecimalFormat df =new DecimalFormat("0.0000");
        System.out.println(df.format(d));
        foo(1,100,15);
        return;
    }
    public static void foo(int l, int r, int k){
        double result = 0;
        for(int index=l;index <=r;index=index+1){
            result = result + Math.pow((index + Math.pow(0.1,k)),1.0/3) -Math.pow(index,1.0/3);
        }
        String str = String.valueOf(result);
        System.out.println(new DecimalFormat("#.#####E000").format(result));
    }

    public static String SicenToComm(double value) {
        String retValue = null;
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        retValue = df.format(value);
        System.out.println(retValue);
        retValue = retValue.replaceAll(",","");
        return retValue;
    }
}
