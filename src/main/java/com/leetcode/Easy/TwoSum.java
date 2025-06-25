package com.leetcode.Easy;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {

        // nums = [2,7,11,15], target = 9
        int nums[] = {2,7,11,15} ;
        int target = 9;

        twoSum(nums,target);

    }

    private static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> numsToIndex = new HashMap<>();
        for(int i= 0; i < nums.length ; i++) {
            if (numsToIndex.containsKey(target - nums[i])) {
              //  return new int[](numsToIndex.get(target -nums[i]),i);
            }
            numsToIndex.put(nums[i],i);
        }
        return new int[]{1};
    }

}
