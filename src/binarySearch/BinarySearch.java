package binarySearch;

public class BinarySearch {

    //O(logn) TC solution
    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int binarySearch(int[] arr, int target, int low, int high) {
        while (low <= high) {
            int mid = high + (low - high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
