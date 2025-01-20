package greedy;

/**
 * https://leetcode.com/problems/merge-triplets-to-form-target-triplet/
 */
public class MergeTriplets {

    //O(n) solution
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        //Initial index values
        int indexx = -1;
        int indexy = -1;
        int indexz = -1;
        for(int i = 0; i < triplets.length; i++) {
            //For each triplet value, we check if the ai value is same as target's x value, then we found the index for x. But, the b, c values should wither be less than or equal to traget's y and z values, to actually find the target. Because, higher b, c values means we will have values higher than y and z
            if(triplets[i][0] == target[0] && triplets[i][1] <= target[1] && triplets[i][2] <= target[2]) {
                indexx = i;
            }
            if(triplets[i][1] == target[1] && triplets[i][0] <= target[0] && triplets[i][2] <= target[2]) {
                indexy = i;
            }
            if(triplets[i][2] == target[2] && triplets[i][0] <= target[0] && triplets[i][1] <= target[1]) {
                indexz = i;
            }
            //Verify if we have already found all the indices
            if(indexx != -1 && indexy != -1 && indexz != -1) {
                return true;
            }
        }
        //If we never found the indices, then no solution
        return false;
    }

    public static void main(String[] args) {
        int[][] triplets = {{2, 5, 3}, {1, 8, 4}, {1, 7, 5}};
        int[] target = {2, 7, 5};
        MergeTriplets mergeTriplets = new MergeTriplets();
        //Output should be true
        System.out.println(mergeTriplets.mergeTriplets(triplets, target));
        triplets = new int[][]{{3,4,5},{4,5,6}};
        target = new int[]{3,2,5};
        //Output should be false
        System.out.println(mergeTriplets.mergeTriplets(triplets, target));
    }
}
