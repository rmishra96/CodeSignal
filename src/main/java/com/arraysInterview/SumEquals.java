package com.arraysInterview;

import java.util.HashSet;

public class SumEquals {
    public static void main(String[] args) {
        int arr[] = {2, 11, 5, 1, 4, 7 };
        int n = arr.length ;

        if(checkPair(arr,n) == false){
            System.out.println("No Pair found");
        }

    }

    private static boolean checkPair(int[] arr, int n) {
        int sum = 0 ;
        for(int i=0; i < n ; i++){
            sum += arr[i];

        }
        if(sum % 2 != 0){
            return false;
        }
        sum = sum / 2 ;

        HashSet<Integer> s = new HashSet<>();
        for(int i= 0; i < n; i++){
            int val = sum - arr[i] ;
            if(s.contains(val) && val == (int)s.toArray()[s.size() - 1]){
                System.out.printf("Pair elements are %d and %d \n",arr[i] , val);
                return true;
            }
            s.add(arr[i]);
        }
        return false;
    }
}
