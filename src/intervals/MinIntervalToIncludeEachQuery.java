package intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-interval-to-include-each-query/
 */
public class MinIntervalToIncludeEachQuery {

    //O(nlogn + qlogq) TC solution
    public int[] minInterval(int[][] intervals, int[] queries) {
        //Sort intervals in increasing order of left
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int [] b) {
                return a[0] - b[0];
            }
        });

        //Create an array of query value, index pair
        int[][] queriesWithIndex = new int[queries.length][2];
        for(int i = 0; i < queries.length; i++) {
            queriesWithIndex[i][0] = queries[i];
            queriesWithIndex[i][1] = i;
        }

        //Sort queries with index based on query value
        Arrays.sort(queriesWithIndex, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int [] b) {
                return a[0] - b[0];
            }
        });

        //Create an array to store result
        int[] ans = new int[queries.length];
        //Create a priority queue (min heap) to get the interval with minimum size, add a pair of size of interval and its right
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] < b[0]) {
                    return -1;
                } else if(a[0] > b[0]) {
                    return 1;
                } else {
                    //Give minimum right value interval first
                    return a[1] - b[1];
                }
            }
        });
        //Keep track of index of intervals
        int j = 0;
        for(int i = 0; i < queriesWithIndex.length; i++) {
            int queryVal = queriesWithIndex[i][0];
            int queryIndex = queriesWithIndex[i][1];
            //Keep adding the current interval till it can have the query val
            while(j < intervals.length && queryVal >= intervals[j][0]) {
                pq.add(new int[]{intervals[j][1] - intervals[j][0] + 1, intervals[j][1]});
                j++;
            }
            //Keep removing till the intervals in pq can have the query val
            while(!pq.isEmpty() && pq.peek()[1] < queryVal) {
                pq.poll();
            }
            //If pq is empty, then there was no interval that contains queryVal, else we found the interval and since we got from min heap, it is the one with minimum size of interval
            //Also, since we are finding the result in increasing orders of query values, if for a query we didn't find any interval, then we wont find any interval for a value greater than that
            ans[queryIndex] = pq.isEmpty() ? -1 : pq.peek()[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,4},{2,4},{3,6},{4,4}};
        int[] queries = {2,3,4,5};
        MinIntervalToIncludeEachQuery minIntervalToIncludeEachQuery = new MinIntervalToIncludeEachQuery();
        // O/p should be [3,3,1,4]
        System.out.println(Arrays.toString(minIntervalToIncludeEachQuery.minInterval(intervals, queries)));
    }
}
