package com.stringInterview;

import com.sun.source.tree.BreakTree;

public class ReverseWords {
    public static void strRev(char[] str, int start, int end){
        if(str == null || str.length < 1){
            return;
        }

        while(start < end) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }

    public static String reverseWords(char[] sentence){
        if(sentence == null || sentence.length == 0){
            return "String is empty";
        }
        int strLen = sentence.length;
        strRev(sentence,0,strLen -1);

        int start = 0;
        int end = 0;

        while(true) {
            if (start >= strLen - 1) {
                break;
            }

            while (sentence[start] == ' ') {
                start++;
            }

            end = start + 1;
            while (end < strLen && sentence[end] != ' ') {
                end++;
            }
            strRev(sentence, start, end - 1);
            start = end;
        }


     return String.valueOf(sentence);
    }

    public static void main(String[] args) {
        String sentence = "Hello World";
        char[] str = sentence.toCharArray();
        String reversedSentence = reverseWords(str);
        System.out.println("Reversed Sentence: " + reversedSentence);
    }
}
