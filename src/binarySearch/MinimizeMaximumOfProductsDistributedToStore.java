package binarySearch;

public class MinimizeMaximumOfProductsDistributedToStore {

    //Problem is exactly same as Koko Eating bananas problem
    //O(mlogk) approach, k is max number of quantity and m is number of quantities
    //Using Binary search
    public int minimizedMaximum(int n, int[] quantities) {
        //Find total quantity
        int totalQuantity = 0;
        //Max quantity
        int max = 0;
        for (int quantity : quantities) {
            totalQuantity = totalQuantity + quantity;
            max = Math.max(max, quantity);
        }

        //Minimum products to distribute will be the average value you can distribute to n stores
        int averageQuantity = (int) Math.ceil((double) totalQuantity / (double) n);

        //We want value k value such that all products are distributed to the stores and each store gets only one type of product and quantity not exceeding k
        int low = averageQuantity;
        int high = max;
        int k = 0;
        //Do binary search
        while (low <= high) {
            int mid = low + (high - low) / 2;
            //How many stores we can distribute it to using k value = mid
            int nNew = 0;
            for (int i = 0; i < quantities.length; i++) {
                nNew = nNew + (int) Math.ceil((double) quantities[i] / (double) mid);
            }
            //For k value equal to mid, find if we can distribute products to atleast n stores
            if (nNew > n) {
                //Since number of stores > given n, Increase the k value(products distributed), so increase low
                low = mid + 1;
            } else {
                //We found k such that products can be distributed to atleast n stores(Because problem says a store can get possibly zero products)
                k = mid;
                //We want minimum k value, so decrease high
                high = mid - 1;
            }
        }
        return k;
    }
}
