package arrays_and_hashing;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int prefix_product = 1;
        int suffix_product = 1;
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix_product;
            prefix_product = prefix_product * nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = result[i] * suffix_product;
            suffix_product = suffix_product * nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        ProductOfArrayExceptSelf productOfArrayExceptSelf = new ProductOfArrayExceptSelf();
        System.out.println(Arrays.toString(productOfArrayExceptSelf.productExceptSelf(nums)));
    }
}
