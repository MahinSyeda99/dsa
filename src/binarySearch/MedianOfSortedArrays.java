package binarySearch;

class MedianOfSortedArrays {
    //O(log(min(m,n))) TC solution where m is length of nums1 and n is length of nums2
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        int half = (total) / 2;
        //Ensure nums1 is smaller array for simplicity
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int low = 0;
        int high = nums1.length - 1;
        while (true) {
            int mid = (int) Math.floor((double) (low + high) / 2);
            int j = half - (mid + 1) - 1; //index of left partition in nums2
            int Aleft = mid >= 0 ? nums1[mid] : Integer.MIN_VALUE;
            int Aright = mid + 1 < nums1.length ? nums1[mid + 1] : Integer.MAX_VALUE;
            int Bleft = j >= 0 ? nums2[j] : Integer.MIN_VALUE;
            int Bright = j + 1 < nums2.length ? nums2[j + 1] : Integer.MAX_VALUE;
            if (Aleft <= Bright && Bleft <= Aright) {
                if (total % 2 == 0) {
                    //even number of elements
                    double median = (double) (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2;
                    return median;
                } else {
                    //odd number of elements
                    return Math.min(Aright, Bright);
                }
            } else if (Aleft > Bright) {
                //We need to reduce our left partition in A, so we need to reduce mid, hence decrease high
                high = mid - 1;
            } else {
                //We need to reduce our left partition in B, so we need to increase mid, hence increase low
                low = mid + 1;
            }
        }
    }

    public static void main(String[] args) {
        MedianOfSortedArrays medianOfSortedArrays = new MedianOfSortedArrays();
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        System.out.println(medianOfSortedArrays.findMedianSortedArrays(nums1, nums2));
    }
}
