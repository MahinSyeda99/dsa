package intervals;

import java.util.*;

/**
 * https://leetcode.com/problems/insert-interval/description/
 */
public class InsertIntervalMySolution {
    //T.C: O(n)
    //S.C: O(n)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //First, insert the interval
        List<int[]> insertedResult = new ArrayList<>();
        boolean found = false;
        for (int[] interval : intervals) {
            if (newInterval[0] < interval[0]) {
                insertedResult.add(newInterval);
                insertedResult.add(interval);
                found = true;
            } else {
                insertedResult.add(interval);
            }
        }
        if (!found) {
            insertedResult.add(newInterval);
        }
        //Then merge the intervals
        int[] merged = {insertedResult.get(0)[0], insertedResult.get(0)[1]};
        List<int[]> result = new ArrayList<>();
        for (int i = 1; i < insertedResult.size(); i++) {
            //If we find in correct order, then add the merged and initialize merged to current interval
            if (insertedResult.get(i)[0] > merged[1]) {
                result.add(merged);
                merged = insertedResult.get(i);
            } else {
                //Update merged interval's end
                merged[1] = Math.max(merged[1], insertedResult.get(i)[1]);
            }
        }
        //Finally, add merged if it has been initialized. It won't be duplicate, because we are adding merged only once
        result.add(merged);
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        InsertIntervalMySolution insertInterval = new InsertIntervalMySolution();
        // O/p should be [[1, 5], [6, 9]]
//        int[][] arr = {{1, 3}, {6, 9}};
//        int[] newInterval = {2, 5};

        // O/p should be [[1,5]]
//        int[][] arr = {{1,5}};
//        int[] newInterval = {2, 3};

        // O/p should be [[5, 7]]
//        int[][] arr = {};
//        int[] newInterval = {5, 7};

        // O/p should be [[1, 5], [6, 8]]
//        int[][] arr = {{1,5}};
//        int[] newInterval = {6, 8};

        // O/p should be [[0,0],[1,5]]
        int[][] arr = {{1, 5}};
        int[] newInterval = {0, 0};
        System.out.println(Arrays.deepToString(insertInterval.insert(arr, newInterval)));
    }
}
