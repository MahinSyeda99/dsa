package intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        //Sort the array in increasing order of start times
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        int prevEnd = intervals[0][1];
        int count = 0;
        for(int i = 1; i<intervals.length;i++) {
            //If already in correct order, then just add
            if(intervals[i][0] >= prevEnd) {
                prevEnd = intervals[i][1];
            } else {
                //Found overlapping intervals
                //Keep the one with min end time. i.e remove the one with higher end time
                if(prevEnd > intervals[i][1]) {
                    prevEnd = intervals[i][1];
                }
                //Increment removal count
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NonOverlappingIntervals nonOverlappingIntervals = new NonOverlappingIntervals();
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        //O/p should be 1
        System.out.println(nonOverlappingIntervals.eraseOverlapIntervals(intervals));
    }
}
