package interview;

public class Problem2 {

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[high]) {
                if (target >= nums[low]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < nums[high]) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        Problem2 problem2 = new Problem2();
        System.out.println(problem2.search(nums, 10));
    }
}
