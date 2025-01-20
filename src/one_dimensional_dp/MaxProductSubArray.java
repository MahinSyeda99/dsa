package one_dimensional_dp;

public class MaxProductSubArray {

    //Similar to Kadence algo of max sum sub array
    //O(n) TC and O(1) SC
    public int maxProductWithoutDp(int[] nums) {
        int currMaxProduct = 1;
        int currMinProduct = 1;
        int result = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            int temp = nums[i] * currMaxProduct;
            currMaxProduct = Math.max(nums[i], Math.max(nums[i] * currMaxProduct, nums[i] * currMinProduct));
            currMinProduct = Math.min(nums[i], Math.min(nums[i] * currMinProduct, temp));
            result = Math.max(result, currMaxProduct);
        }
        return result;
    }

    //DP solution, O(n) TC and O(n) S.C
    public int maxProduct(int[] nums) {
        //Array for storing max product
        int[] dpMax = new int[nums.length];
        //Array for storing min product
        int[] dpMin = new int[nums.length];

        //Base case, intitialize
        dpMax[0] = nums[0]; //Initial product is number itself
        dpMin[0] = nums[0];

        //Final max product
        int maxResult = dpMax[0];

        //Iterate over the rest over the elements
        for(int i = 1; i < nums.length; i++) {
            //Find max among current element and max of (product of current element and prev max,product of current element and prev min(In case of negatives, we will get the correct max value))
            dpMax[i] = Math.max(nums[i], Math.max(nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1]));

            //Now, evaluate minimum product
            //Find min among current element and min of (product of current element and prev min, product of current element and prev min(In case of negatives, we will get coorect minimum value))
            dpMin[i] = Math.min(nums[i], Math.min(nums[i] * dpMin[i - 1], nums[i] * dpMax[i - 1]));

            //Keep track of max product
            if(dpMax[i] > maxResult) {
                maxResult = dpMax[i];
            }
            /**
             Ex: [-1, -2, -3, -4]
             dpMax[0] = -1
             dpMin[0] = -1

             i = 1
             dpMax[1] = max(-2, max(-2 * -1, -2 * -1)) = 2
             dpMin[1] = min(-2, min(-2 * -1, -2 * 1)) = -2

             i = 2
             dpMax[2] = max(-3, max(-3 * 2, -3 * -2)) = 6
             dpMin[2] = min(-3, min(-3 * -2, -3 * 2)) = -6

             i = 3
             dpMax[3] = max(-4, max(-4 * 6, -4 * -6)) = 24
             dpMin[3] = min(-4, min(-4 * -6, -4 * 6)) = -24

             Final result=24(max of all dpMax)

             Ex: [-1, -2, -3, 4]

             i = 3
             dpMax[3] = max(4, max(4 * 6, 4 * -6)) = 24
             dpMin[3] = min(4, min(4 * -6, 4 * 6)) = -24

             Final result=24(max of all dpMax)
             */
        }
        return maxResult;
    }

    public static void main(String[] args) {
        MaxProductSubArray maxProductSubArray = new MaxProductSubArray();
        int[] nums = {2,3,-2,4};
        //o/p is 6
        System.out.println(maxProductSubArray.maxProduct(nums));
        System.out.println(maxProductSubArray.maxProductWithoutDp(nums));
        nums = new int[]{-2,0,-1};
        //o/p is 0
        System.out.println(maxProductSubArray.maxProduct(nums));
        System.out.println(maxProductSubArray.maxProductWithoutDp(nums));
        nums = new int[]{-2};
        //o/p is -2
        System.out.println(maxProductSubArray.maxProduct(nums));
        System.out.println(maxProductSubArray.maxProductWithoutDp(nums));
        nums = new int[]{-2,3,-4};
        //o/p is 24
        System.out.println(maxProductSubArray.maxProduct(nums));
        System.out.println(maxProductSubArray.maxProductWithoutDp(nums));
        nums = new int[]{7,-2,-4};
        //o/p is 56
        System.out.println(maxProductSubArray.maxProduct(nums));
        System.out.println(maxProductSubArray.maxProductWithoutDp(nums));
        nums = new int[]{-1,-2,-9,-6};
        //o/p is 108
        System.out.println(maxProductSubArray.maxProduct(nums));
        System.out.println(maxProductSubArray.maxProductWithoutDp(nums));
    }
}
