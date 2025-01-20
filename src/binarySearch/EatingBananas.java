package binarySearch;

/**
 * https://leetcode.com/problems/koko-eating-bananas/description/
 */
class EatingBananas {

    //O(nlogn) TC
    public int minEatingSpeed(int[] piles, int h) {
        //The minimum integer
        int k = 0;
        long sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < piles.length; i++) {
            sum += piles[i];
            max = Math.max(max, piles[i]);
        }
        //Minimum rate
        int low = (int) Math.ceil((double) sum / (double) h);
        //Maximum is the max number of piles
        int high = max;
        while (low <= high) {
            int mid = (low + high) / 2;
            int hrs = 0;
            //Calculate time taken using rate = mid
            for (int i = 0; i < piles.length; i++) {
                hrs = hrs + (int) Math.ceil((double) piles[i] / (double) mid);
            }
            //If time taken is greater than given h, then increase the rate
            if (hrs > h) {
                low = mid + 1;
            } else {
                //Decrease rate if time taken is less than given h
                //We want minimum rate, so keep decreasing till we find the minimum rate
                k = mid;
                high = mid - 1;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        EatingBananas eatingBananas = new EatingBananas();
        int[] piles = new int[]{980628391, 681530205, 734313996, 168632541};
        System.out.println(eatingBananas.minEatingSpeed(piles, 819870953));
    }
}
