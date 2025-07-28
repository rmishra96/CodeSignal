package com.company.barclays;


/*Given a set of non-overlapping intervals sorted by start time, insert a new interval and merge if necessary.
* Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
*
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalInsertion {
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = new int[]{4,8};
//        int[][] intervals = {
//                {1, 3},
//                {6, 9}
//        };
//        int[] newInterval = {2, 5};

        // Call the insert method
        int[][] merged = insert(intervals, newInterval);

        // Print the result
        System.out.println("Merged Intervals:");
        for (int[] interval : merged) {
            System.out.println(Arrays.toString(interval));
        }

    }

    private static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i=0;
        int n= intervals.length;

        // Steps 1 : Add interval before new intervals
        while(i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Step 2 : Merge overlapping intervals
        while( i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);


        // Steps3 Add remaining intervals
        while(i < n){
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}
