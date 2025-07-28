package com.company.zemsos;


/*
* You're given an array of integers A, and you need to:
- Divide it into three non-empty groups.
- For each group, compute the difference between the largest and smallest element.
- Your goal is to minimize the maximum of these three differences

*
*
*
*
* */

import java.util.Arrays;

public class Interview {
    public int solution(int[] A) {
        Arrays.sort(A);
        int N = A.length;
        int minMaxDiff = Integer.MAX_VALUE;

        for (int i = 0; i < N - 2; i++) {
            // Group 1: A[0..i]
            int diff1 = A[i] - A[0];

            // Group 3: A[j+1..N-1], so j must be < N-1
            // We fix i and try j = i+1 to N-2
            int low = i + 1, high = N - 2;
            while (low <= high) {
                int j = (low + high) / 2;
                int diff2 = A[j] - A[i + 1];
                int diff3 = A[N - 1] - A[j + 1];
                int maxDiff = Math.max(diff1, Math.max(diff2, diff3));
                minMaxDiff = Math.min(minMaxDiff, maxDiff);

                // Try to balance the groups by adjusting j
                if (diff2 < diff3) {
                    low = j + 1;
                } else {
                    high = j - 1;
                }
            }
        }

        return minMaxDiff;
    }
}
