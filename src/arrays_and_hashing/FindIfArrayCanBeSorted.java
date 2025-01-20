package arrays_and_hashing;

public class FindIfArrayCanBeSorted {

    //O(32 * n) ~ O(n) TC
    public boolean canSortArray(int[] nums) {
        //We need to find min and max in a segment till set bits are same
        //We also need to keep track of max of prev segment whose set bits count was different from current
        //When we find a number whose bits are different than prev set bits, we compare the currentMin of our segment just before finding a new set bit count with the prev max of the segment and former should be greater than latter for the array to remain sorted till this point.
        //Why do we care only care about max and min?
        //Because, we can only swap adjacent elements if set bits count is same.
        //Let's say [8, 4, 2, 30, 15]
        //Swap 8 and 4 => [4, 8, 2]
        //Swap 8 and 2 => [4, 2, 8]
        //Swap 4 and 2 => [2, 4, 8]
        //Since we don't need to actually sort, we can just keep track of max and min here, which are 8 and 2 respectively
        //And now, remaining [30, 15]
        //Swap [30, 15] => [15, 30]
        //current min = 15 >= prevMax = 8 ? Yes, so array is sorted
        int prevMax = Integer.MIN_VALUE;
        int currentMin = nums[0];
        int currentMax = nums[0];
        int prevBitsCount = numberOfSetDigits(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int currentBitsCount = numberOfSetDigits(nums[i]);
            if (currentBitsCount == prevBitsCount) {
                currentMin = Math.min(currentMin, nums[i]);
                currentMax = Math.max(currentMax, nums[i]);
            } else {
                if (currentMin >= prevMax) {
                    prevMax = currentMax;
                    currentMin = nums[i];
                    currentMax = nums[i];
                    prevBitsCount = currentBitsCount;
                } else {
                    return false;
                }
            }
        }
        if (currentMin >= prevMax) {
            return true;
        }
        return false;
    }

    //O(32) TC
    private int numberOfSetDigits(int num) {
        int count = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num = num >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {8, 4, 2, 30, 15};
        FindIfArrayCanBeSorted findIfArrayCanBeSorted = new FindIfArrayCanBeSorted();
        System.out.println(findIfArrayCanBeSorted.canSortArray(nums));
    }
}
