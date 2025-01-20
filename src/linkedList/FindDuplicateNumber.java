package linkedList;

public class FindDuplicateNumber {

    //Using FLoyd's Algo to detect cycle in LL
    //Consider values as next pointer's and indexes as pointer's of LL
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]]; //fast = 2*slow
            if (slow == fast) {
                //We found our first point of intersection
                break;
            }
        }
        //Take a new slow pointer at the beginning
        //When new slow and old slow pointer's meet, that id our duplicate or where the cycle starts
        int slow2 = 0;
        while (slow != slow2) {
            slow = nums[slow];
            slow2 = nums[slow2];
        }
        return slow;
    }
}
