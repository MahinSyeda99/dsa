package binarySearch;

/**
 * A Bitonic Sequence is a sequence of numbers that is first strictly increasing then after a point decreasing.
 * https://www.geeksforgeeks.org/find-element-bitonic-array/
 */
public class FindElementInBitonicArray {

    //O(logn) TC
    public int findElementInBitonicArray(int[] arr, int target) {
        //Find bitonic point
        int bitonicPoint = findBitonicPoint(arr);
        //If bitonic point value is same as target, return bitonic point
        if (arr[bitonicPoint] == target) {
            return bitonicPoint;
        }
        int index;
        //Find target in left of bitonic point, which strictly increasing
        index = binarySearchIncreasing(arr, 0, bitonicPoint - 1, target);
        if (index != -1) {
            return index;
        }
        //Find target in right of bitonic point, which is strictly decreasing
        index = binarySearchDecreasing(arr, bitonicPoint + 1, arr.length - 1, target);
        return index;
    }

    private int findBitonicPoint(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid] < arr[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return 0;
    }

    private int binarySearchIncreasing(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = (low + high) / 2;
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

    private int binarySearchDecreasing(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindElementInBitonicArray findElementInBitonicArray = new FindElementInBitonicArray();
        //o/p is 3
        int[] arr = {-3, 9, 18, 20, 17, 5, 1};
        System.out.println(findElementInBitonicArray.findElementInBitonicArray(arr, 20));
        //o/p is 3
        arr = new int[]{-3, 9, 18, 20, 19, 18};
        System.out.println(findElementInBitonicArray.findElementInBitonicArray(arr, 20));
        //o/p is 4
        arr = new int[]{-3, 9, 18, 21, 20, 19};
        System.out.println(findElementInBitonicArray.findElementInBitonicArray(arr, 20));
        //o/p is -1
        arr = new int[]{5, 6, 7, 8, 9, 10, 3, 2, 1};
        System.out.println(findElementInBitonicArray.findElementInBitonicArray(arr, 30));
    }
}
