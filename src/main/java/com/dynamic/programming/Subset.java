package com.dynamic.programming;

public class Subset {

    public static boolean isSubsetSum(int[] set, int n, int sum) {
        // Base Cases
        if (sum == 0) return true;
        if (n == 0) return false;

        // If last element is greater than sum, ignore it
        if (set[n - 1] > sum) {
            return isSubsetSum(set, n - 1, sum);
        }

        // Check if sum can be obtained by any of the following:
        // (1) including the last element
        // (2) excluding the last element
        return isSubsetSum(set, n - 1, sum) ||
                isSubsetSum(set, n - 1, sum - set[n - 1]);
    }

    public static void main(String[] args) {
            int[] set = {3, 34, 4, 12, 5, 2};
            isSubsetSum(set,set.length,9);
            System.out.println("Is there a subset with sum 9? " + isSubsetSum(set, set.length, 9));
    }
}
