package com.company;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        for (int i = 0; i < testcases; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            String s = sc.next();
            char [] ch = s.toCharArray();
            if (m%2==0){
                Arrays.sort(ch);
            }
            else{
                for (int j = 0; j < n; j++) {
                    for (int k = j+2; k < n; k+=2) {
                        if (ch[j]>ch[k]){
                            char sss = ch[j];
                            ch[j] = ch[k];
                            ch[k] = sss;
                        }
                    }
                }
            }
            for (int j = 0; j < ch.length; j++) {
                System.out.print(ch[j]);
            }
            System.out.println();
        }
    }
}