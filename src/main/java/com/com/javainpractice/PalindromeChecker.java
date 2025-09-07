package com.com.javainpractice;

public class PalindromeChecker {
    public static void main(String[] args) {
        String input = "aba";
        if(isPalindrome(input)){
            System.out.println("Palindrome");
        }else {
            System.out.println("Not a palindrome");
        }

    }

    private static boolean isPalindrome(String input) {
        int left = 0;
        int right = input.length() - 1;
        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
