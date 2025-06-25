package com.stringInterview;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicate {

    static String removesDup(char[] str){
        if(str == null || str.length == 0 || str[0] == '\n'){
            return "";
        }

        int writeIndex =0 ;
        for(int i= 0 ;i < str.length ; i++){
            boolean found = false;

            for(int j= 0;j < writeIndex ;j++){
                if(str[i] == str[j]){
                    found = true;
                    break;
                }
            }
            if(!found){
                str[writeIndex] = str[i];
                writeIndex++;
            }
        }

        if(writeIndex != str.length){
            String ansStr = String.valueOf(Arrays.copyOfRange(str,0,writeIndex));
            return ansStr;
        }else {
            String ansStr = String.valueOf(str);
            return ansStr;
        }
    }
    static String removesDuplicates(char[] str){
        if(str == null || str.length == 0 || str[0] == '\n'){
            return "";
        }

        Set<Character> hashSet = new LinkedHashSet<Character>();

        int writeIndex = 0;
        int readIndex = 0;

        while(readIndex != str.length){
            if(!hashSet.contains(str[readIndex])){
                hashSet.add(str[readIndex]);
                str[writeIndex] = str[readIndex];
                writeIndex++;
            }
            readIndex++;
        }
        String ansStr = String.valueOf(Arrays.copyOfRange(str,0,writeIndex));
        return  ansStr;
    }

    public static void main(String[] args) {
        String str = "dabbc";
        System.out.println("1. Before removing duplicates: " + str);
        char[] charArray = str.toCharArray();
        System.out.println(" After removing duplicates: " + removesDuplicates(charArray));
    }
}
