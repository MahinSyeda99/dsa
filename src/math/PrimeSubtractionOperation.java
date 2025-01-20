package math;

public class PrimeSubtractionOperation {

    //TODO: Sieve of Eratosthenes algorithm of finding prime numbers

    //Array to store if numbers from 0 to 1000 are prime or not
    boolean[] dp = new boolean[1001];

    //TC ~ O(n^2) and SC ~ O(numMax) ~ O(n)
    public boolean primeSubOperation(int[] nums) {
        //Populate prime number array
        populatePrimeNum();

        //O(n * numMax) ~ O(n^2) as numMax = 1000 = nMax
        for (int i = nums.length - 2; i >= 0; i--) {
            //If we find that current element is greater than or equal to next number in array
            if (nums[i] >= nums[i + 1]) {
                //Find the smallest prime number p such that nums[i] - p < nums[i + 1]
                int smallestPrime = smallestPrime(nums[i], nums[i + 1]);
                //If there is no such prime number, return false
                if (smallestPrime == -1) {
                    return false;
                }
                //Update nums[i] as nums[i] - p
                nums[i] = nums[i] - smallestPrime;
            }
        }
        //Return true as we found strictly increasing array
        return true;
    }

    //O(n)
    private int smallestPrime(int num, int max) {
        //Iterate from prime number 2 till < num (as we want strictly less prime number than p)
        //We want prime number p for number num such that (num - p) < next number in array(here max)
        //We want to find the maximum such number which will satisfy. So we start from minimum prime number
        for (int p = 2; p < num; p++) {
            //If it is prime number and (num - p) < max, then return the prime number
            if (dp[p] && ((num - p) < max)) {
                return p;
            }
        }
        return -1;
    }

    //O(n^2), since nMax = 1000
    private void populatePrimeNum() {
        dp[2] = true;
        dp[3] = true;
        dp[5] = true;
        dp[7] = true;
        dp[11] = true;
        dp[13] = true;
        dp[17] = true;
        dp[19] = true;
        dp[23] = true;
        dp[29] = true;
        dp[31] = true;
        dp[37] = true;
        dp[41] = true;
        dp[43] = true;
        dp[47] = true;
        boolean isPrime;
        for (int num = 52; num <= 1000; num++) {
            isPrime = true;
            //To find if num is prime or not, find if it is divisible any number between 2 and num/2
            // (num/2 because, this is the maximum number that can divide num, other than num(which we don't want))
            for (int i = 2; i <= (num / 2); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            dp[num] = isPrime;
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 8, 3};
        PrimeSubtractionOperation primeSubtractionOperation = new PrimeSubtractionOperation();
        System.out.println(primeSubtractionOperation.primeSubOperation(nums));
    }
}
