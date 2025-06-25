package com.dynamic.programming;

public class SubsetSumKnapsack {
    public static void main(String[] args) {
        int[] arr1 = {3, 34, 4, 12, 5, 2};
        int sum1 = 9;
        System.out.println("Subset with sum " + sum1 + " exists: " + isSubsetSum(arr1, sum1)); // Output: true

        int[] arr2 = {3, 34, 4, 12, 5, 2};
        int sum2 = 30;
        System.out.println("Subset with sum " + sum2 + " exists: " + isSubsetSum(arr2, sum2)); // Output: false
    }

    private static boolean  isSubsetSum(int[] arr, int sum) {
        int n = arr.length ;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Initialize base cases
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // A sum of 0 can always be formed with an empty subset
        }
        // Fill the DP tables
        for(int i=1 ; i<=n ; i++){
            for(int j= i+1; j <= sum ;j++){
                if(arr[i-1] > j){
                    dp[i][j] = dp[i-1][j]; // If the current element is greater than the sum, we can't include it
                }else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]]; // Include or exclude the current element
                }
            }
        }
        return dp[n][sum];
    }
}
