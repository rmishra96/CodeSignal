package com.leetcode.Easy;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int arr[] = {11, 7, 1, 17, 6, 2, 3, 16, 8, 4, 9, 10, 15};
        System.out.println(longest(arr));
    }

    private static int longest(int[] arr) {

        Map<Integer, Boolean> hMap = new HashMap<>();
        // 1. Assume every element as the sp of lcs

        for(int i= 0; i < arr.length ; i++){

            hMap.put(arr[i],true);
        }

        // 2. Consider only valid sp
        for(int i=0; i< arr.length ; i++){
            if(hMap.containsKey(arr[i] - 1) == true){
                hMap.put(arr[i],false);
            }
        }

        // 3/ for valid sp, find the length of lcs
        int maxLen = 1;
        for(int i= 0; i < arr.length ; i++){
            if(hMap.get(arr[i]) == true){
                int currLen = 1;
                int val = arr[i];
                while(hMap.containsKey(val+1) == true){
                    currLen++;
                    val++;
                }
                maxLen = Math.max(maxLen,currLen);
            }
        }
        return maxLen;
    }

}
