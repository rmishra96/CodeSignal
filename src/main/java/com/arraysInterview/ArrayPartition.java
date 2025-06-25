package com.arraysInterview;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayPartition {

    public int arrayPairSum(int[] nums){
        Arrays.sort(nums);
        var sum1 =0;
        var sum =0 ;
        for(var i =0; i < nums.length ; i+=2){
            sum +=nums[i];

        }
        return sum;

    }

    public static void main(String[] args) {
        var arrayPartition = new ArrayPartition();
        var result = arrayPartition.arrayPairSum(new int[]{1,4,3,2});
        System.out.println("Result +:" +result);
        assertEquals(4, result);
        System.out.println();
    }
}
