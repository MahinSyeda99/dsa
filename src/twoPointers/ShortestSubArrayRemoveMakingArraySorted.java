package twoPointers;

/**
 * https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/description
 */
public class ShortestSubArrayRemoveMakingArraySorted {

    //O(n) TC solution
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        //Find non-decreasing array from left, i = 0
        int left = 0; //Index where left non-decreasing array ends
        for (; left + 1 < n; left++) {
            if (arr[left] > arr[left + 1]) {
                break;
            }
        }
        //Array is already non-decreasing
        if (left == n - 1) {
            return 0;
        }

        //Find non-decreasing array ending at index n - 1
        int right = n - 1; //Index where right non-decreasing array starts
        for (; right - 1 >= 0; right--) {
            if (arr[right - 1] > arr[right]) {
                break;
            }
        }

        //Start merging left and right non-increasing sub arrays
        int i = 0;
        int j = right;
        //n - left -1 => Remove everything after left
        //right => remove everything before right
        int remove = Math.min(n - left - 1, right);
        while (i <= left && j < n) {
            if (arr[i] <= arr[j]) {
                //Consider removing everything between i and j i.e from i + 1 to j - 1
                remove = Math.min(remove, j - i - 1);
                i++;
            } else {
                //If arr[i] > arr[j] => All values from i to left > arr[j] and we will never find a non-increasing sequence from any of the i's
                //So, we consider increasing value of arr[j] by incrementing j, hoping that in next iteration arr[i] <= arr[j] is satisfied
                j++;
            }
        }
        return remove;
    }
}
