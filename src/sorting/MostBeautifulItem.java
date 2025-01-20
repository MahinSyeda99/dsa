package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class MostBeautifulItem {

    //Solution 2
    //O(nlogn + qlogq) TC solution
    //O(nlogn) solution
    public int[] maximumBeauty2(int[][] items, int[] queries) {
        //Sort items array
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] < b[0]) {
                    return -1;
                } else if (a[0] > b[0]) {
                    return 1;
                } else {
                    return a[1] - b[1];
                }
            }
        });

        //Create an array of query value, index pair
        int[][] queriesWithIndex = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            queriesWithIndex[i][0] = queries[i];
            queriesWithIndex[i][1] = i;
        }

        //Sort queries with index based on query value
        Arrays.sort(queriesWithIndex, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        int[] result = new int[queries.length];
        int j = 0;
        int max = 0;
        //For each query
        for (int i = 0; i < queriesWithIndex.length; i++) {
            while (j < items.length && queriesWithIndex[i][0] >= items[j][0]) {
                max = Math.max(max, items[j][1]);
                j++;
            }
            result[queriesWithIndex[i][1]] = max;
        }
        return result;
    }


    //Solution 1
    //O(nlogn + qlogq) TC solution
    public int[] maximumBeauty(int[][] items, int[] queries) {
        //Sort items array
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] < b[0]) {
                    return -1;
                } else if (a[0] > b[0]) {
                    return 1;
                } else {
                    return a[1] - b[1];
                }
            }
        });

        //Max beauty at index i
        // max = new int[items.length];
        // max[0] = items[0][1];
        for (int i = 1; i < items.length; i++) {
            items[i][1] = Math.max(items[i - 1][1], items[i][1]);
        }

        int[] result = new int[queries.length];
        //For each query
        for (int i = 0; i < queries.length; i++) {
            result[i] = binarySearch(items, queries[i]);
        }
        return result;
    }

    //Return price
    private int binarySearch(int[][] items, int target) {
        int low = 0;
        int high = items.length - 1;
        int index = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (items[mid][0] <= target) {
                index = mid;
                low = mid + 1;
            } else if (items[mid][0] > target) {
                high = mid - 1;
            }
        }
        return index != -1 ? items[index][1] : 0;
    }

    public static void main(String[] args) {
        int[][] items = {{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}};
        int[] queries = {1, 2, 3, 4, 5, 6};
        MostBeautifulItem mostBeautifulItem = new MostBeautifulItem();
        System.out.println(Arrays.toString(mostBeautifulItem.maximumBeauty(items, queries)));

        items = new int[][]{{193, 732}, {781, 962}, {864, 954}, {749, 627}, {136, 746}, {478, 548}, {640, 908}, {210, 799},
                {567, 715}, {914, 388}, {487, 853}, {533, 554}, {247, 919}, {958, 150}, {193, 523}, {176, 656}, {395, 469},
                {763, 821}, {542, 946}, {701, 676}};
        queries = new int[]{885, 1445, 1580, 1309, 205, 1788, 1214, 1404, 572, 1170, 989, 265, 153, 151, 1479, 1180, 875, 276, 1584};
        System.out.println(Arrays.toString(mostBeautifulItem.maximumBeauty(items, queries)));
    }
}
