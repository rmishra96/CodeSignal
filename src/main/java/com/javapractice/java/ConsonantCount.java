package com.javapractice.java;

public class ConsonantCount {
    public static void main(String[] args) {
        String str = "abc de";
        System.out.println(totalConsantCount(str));
    }

    private static int totalConsantCount(String str) {
        int count = 0 ;
        for(int i =0 ; i < str.length() ; i++){
            if(isConsonantCount((str.charAt(i)))){
                count++;
            }

        }
        return count;
    }

    private static boolean isConsonantCount(char i) {
            i = Character.toUpperCase(i);
            return ! ((i == 'A') || i =='E' || i =='I' || i == 'U' || i =='O' ) && i >=65 && i<=90 ;
    }
}
