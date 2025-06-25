package com.leetcode.Easy;

import java.util.HashMap;

public class LongestSubArrayK {
    public static void main(String[] args) {

        int arr[] = {10,5,2,7,1,9};
        int target = 15;
        System.out.println(sumWithLongestArrayK(arr,target));
    }

    private static int sumWithLongestArrayK(int[] arr, int target) {

        int maxLen = -1;
        HashMap<Integer,Integer> map = new HashMap<>();
        // firsr Occurrence of Sum

        map.put(0, -1);
        int pSum = 0;
        for(int i =0; i < arr.length ; i++){
            pSum += arr[i];
            if(map.containsKey(pSum - target) == true){
                maxLen = Math.max(maxLen, i - map.get(pSum -target));
            }
            if(map.containsKey(pSum) == false){
                // if this pSum is appearing for the first time
                map.put(pSum,i);
            }
        }
        return maxLen;
    }
}
