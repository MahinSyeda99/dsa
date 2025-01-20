package slidingWindow;

public class NumberOfSubarraysWithProductMaxK {

    //O(n) TC, sliding window approach
    public int numberOfSubArraysWithProductLessThanEqualToK(int[] arr, int k) {
        int product = 1;
        int start = 0, end = 0;
        int count = 0;
        int n = arr.length;
        while (end < n) {
            product = product * arr[end];
            // Move left bound so guarantee that product is again less than or equal to k.
            while (start <= end && product > k) {
                product = product / arr[start];
                start++;
            }
            // All subarrays ending at `end` and starting from `start` to `end` are valid and number of such subarrays are (end - start) + 1
            //Indeed, the element itself comprises an array, and also we can multiply arr[end] to all contiguous arrays which have right border at end.
            // There are as many such arrays as the length of the window.
            //Ex: [1, 2, 3, 4], k = 10
            //end = 0, start = 0, product = 1 <= 10 => count = 1([1])
            //end = 1, start = 0, product = 2 <= 10 => count = count + 2([1, 2], [2]) = 3
            //end = 2, start = 0, product = 6 <= 10 => count = count + 3([1, 2, 3], [2, 3], [3]) = 6
            count = count + (end - start + 1);
            end++;
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfSubarraysWithProductMaxK numberOfSubarraysWithProductMaxK = new NumberOfSubarraysWithProductMaxK();
        int[] arr = {2, 3, 4};
        //o/p is 4 [2], [3], [4], [2, 3]
        System.out.println(numberOfSubarraysWithProductMaxK.numberOfSubArraysWithProductLessThanEqualToK(arr, 6));
        arr = new int[]{1, 2, 3};
        //o/p is 4 [1], [2], [3], [1, 2]
        System.out.println(numberOfSubarraysWithProductMaxK.numberOfSubArraysWithProductLessThanEqualToK(arr, 4));
        arr = new int[]{1, 2, 3};
        //o/p is 6 [1], [2], [3], [1, 2], [1, 2, 3], [2, 3]
        System.out.println(numberOfSubarraysWithProductMaxK.numberOfSubArraysWithProductLessThanEqualToK(arr, 7));
    }
}
