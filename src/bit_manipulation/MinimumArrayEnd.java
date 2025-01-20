package bit_manipulation;

public class MinimumArrayEnd {

    /**
     * Find the binary representation of n-1. (Let say 1011)
     * Find binary representation of x. (Let say 0000001001)
     * Put the binary representation of n-1 in zeroes of binary representation of x.
     * Here it will be replaced as :-
     * _________0000001001 (binary of x)
     * _____________10_11_ (put binary of n at zeroes of x from right)
     * _________0000101111 {This in decimal is our required answer}
     */

    //O(n) TC solution
    public long minEnd(int n, int x) {
        n = n - 1;
        long result = 0;
        int i = 0;
        for (i = 0; i < 32; i++) {
            if ((x & (1 << i)) == 0) {
                //If bit is zero, then we add the current bit in n to result
                result = result + ((long) (n & 1) << i);
                //RIght shift n
                n = n >> 1;
            } else {
                //If the bit is set in x, then keep it untouched, so we just add that position to result
                result = result + ((long) 1 << i);
            }
        }
        //If n is not zero, then keep adding current bit in n to result
        while (n != 0) {
            result = result + ((long) (n & 1) << i);
            n = n >> 1;
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        MinimumArrayEnd minimumArrayEnd = new MinimumArrayEnd();
        System.out.println(minimumArrayEnd.minEnd(3, 4));
        System.out.println(minimumArrayEnd.minEnd(2, 7));
    }
}
