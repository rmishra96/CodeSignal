package com.leetcode.Easy;

import java.util.HashMap;

public class SumArrayK {
    public static void main(String[] args) {
        int[] arr = {9,4,20,3,10,5};
        int target = 33;

        System.out.println(subarrayWithSum(arr,target));
    }

    private static int subarrayWithSum(int[] arr, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int ans =0 ;
        int psum =0;
        for(int i=0;i < arr.length; i++){
            psum += arr[i];
            if(map.containsKey(psum -target) == true){
                ans += map.get(psum - target);
            }
            map.put(psum,map.getOrDefault(psum,0)+1);
        }
        return ans;
    }
}
