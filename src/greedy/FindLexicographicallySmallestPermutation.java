package greedy;

import java.util.Arrays;

/**
 *Data analysts at Amazon are analyzing the information gained when a model is trained with different arrangements of the same data.
 * For an array of n integers, data, an arrangement is represented using a permutation of the integers from 1 to n.
 * They observed that the information gained for some permutation p of n integers for the array data is equal to the sum of i * data[p[i]].
 *
 * For example, if data = [2, 4, 5, 3] and p = [2, 1, 3, 4],
 * then the information gained is 1 * data[2] + 2 * data[1] + 3 * data[3] + 4 * data[4] = 1 * 4 + 2 * 2 + 3 * 5 + 4 * 3 = 4 + 4 + 15 + 12 = 35.
 *
 * Given the array data, find the lexicographically smallest permutation p such that the information gained for the given data is maximum.
 *
 * Note: A permutation p is considered lexicographically smaller than a permutation q, if the first index i where p[i] ≠ q[i], p[i] < q[i].
 *
 * Example
 * Suppose n = 3 and data = [2, 1, 2].
 *
 * Permutation | Information Gain
 * [1, 2, 3]   | 1 * 2 + 2 * 1 + 3 * 2 = 10
 * [2, 1, 3]   | 1 * 1 + 2 * 2 + 3 * 2 = 11
 * [1, 3, 2]   | 1 * 2 + 2 * 2 + 3 * 1 = 9
 * [3, 1, 2]   | 1 * 2 + 2 * 2 + 3 * 1 = 9
 * [2, 3, 1]   | 1 * 1 + 2 * 2 + 3 * 2 = 11
 * [3, 2, 1]   | 1 * 2 + 2 * 1 + 3 * 2 = 10
 *
 * The maximum information is gained for permutations [2, 1, 3] and [2, 3, 1]. The lexicographically smaller permutation of the two is [2, 1, 3]. Hence the answer is [2, 1, 3].
 */
public class FindLexicographicallySmallestPermutation {

    //O(nlogn) TC
    public int[] getLexicographicallySmallestPermutation(int[] data) {
        int n = data.length;
        int[][] valuesWithIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            valuesWithIndex[i][0] = data[i];
            valuesWithIndex[i][1] = i + 1;
        }
        Arrays.sort(valuesWithIndex, (a, b) -> {
            if (a[0] < b[0]) {
                return 1;
            } else if (a[0] > b[0]) {
                return -1;
            } else {
                if (a[1] < b[1]) {
                    return 1;
                } else if (a[1] > b[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int[] p = new int[n];
        //Assign higher value index in data to higher index in permutation to maximize info gained
        for (int i = 0; i < n; i++) {
            p[n - 1 - i] = valuesWithIndex[i][1];
        }
        return p;
    }

    public static void main(String[] args) {
        FindLexicographicallySmallestPermutation findLexicographicallySmallestPermutation = new FindLexicographicallySmallestPermutation();
        int[] data = new int[]{2, 1, 2};
        //o/p is [2, 1, 3]
        System.out.println(Arrays.toString(findLexicographicallySmallestPermutation.getLexicographicallySmallestPermutation(data)));
        data = new int[]{2, 1, 2, 3};
        //o/p is [2, 1, 3, 4]
        System.out.println(Arrays.toString(findLexicographicallySmallestPermutation.getLexicographicallySmallestPermutation(data)));
        data = new int[]{2, 4, 5, 3};
        //o/p is [1, 4, 2, 3]
        System.out.println(Arrays.toString(findLexicographicallySmallestPermutation.getLexicographicallySmallestPermutation(data)));
    }
}
