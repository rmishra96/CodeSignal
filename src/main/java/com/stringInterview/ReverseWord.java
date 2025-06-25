package com.stringInterview;

public class ReverseWord {
    public static void main(String[] args) {
        String originalWord = "Hello World";
        String reversed = reverseWord(originalWord);

        System.out.println("Original Word: "
                + originalWord);
        System.out.println("Reversed Word: " + reversed);
    }

    private static String reverseWord(String originalWord) {
        StringBuilder reversedWord
                = new StringBuilder(originalWord.length());
        for(int i = originalWord.length() -1 ; i>=0 ; i--){
            reversedWord.append(originalWord.charAt(i));
        }
        return reversedWord.toString();
    }
}
