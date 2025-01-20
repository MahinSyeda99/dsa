package intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {
    //O(nlogn) TC
    //O(1) SC
    public int[][] merge(int[][] intervals) {
        //Sort the given intervals based on start time
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        //Merge the intervals and store the final result in place
        int index = 0;
        for(int i=1; i<intervals.length;i++) {
            //In correct order
            if(intervals[i][0] > intervals[index][1]) {
                index++;
                intervals[index] = intervals[i];
            } else {
                //Merge intervals
                //Update last interval's max
                intervals[index][1] = Math.max(intervals[index][1], intervals[i][1]);
            }
        }
        //To get sub array
        return Arrays.copyOfRange(intervals, 0, index + 1);
    }

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        //O/p should be [[1,6],[8,10],[15,18]]
        System.out.println(Arrays.deepToString(mergeIntervals.merge(intervals)));
    }
}
