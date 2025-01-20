package slidingWindow;

import java.util.Stack;

/**
 * Problem Statement:
 * You are given a string, s and integer k. k is the number of operations you can perform.
 * You need to find the lexicographically smallest string possible by performing operation: removing duplicate character.
 * A character should be included at least once in the result.
 * Ex1:  s = "zzabyy", k = 2 , o/p : zaby (By removing z at index 0 and y at index 5)
 * Ex2: s = "aaaabbc", k = 2,  o/p: aaaabbc (Because, if we remove 'a' at index 0 by doing 1 operation, then s = "aaabbc".
 * Then, one more operation, remove 'a' at index 0 => s = "aabbc". But the lexicographically smaller one is the input itself "aaaabbc)"
 * Ex3: "cabba", k = 2, o/p : caba
 */
public class LexicographicallySmallestStringWithKOperations {

    //O(n) TC and O(n) SC
    public String smallestSubstringWithKOperations(String s, int k) {
        //Find the frequency of characters
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        //Stack to store visited elements
        Stack<Character> stk = new Stack<>();
        int r = 0;
        StringBuilder sb = new StringBuilder();
        int operations = 0;
        while (r < s.length()) {
            //If current character is less than stack's peek, and if the stack's peek count is greater than 1(so that it will be included at-least once) and if operations is less than k,
            //then we can do one remove operation, to make it lexicographically smallest
            while (!stk.isEmpty() && (stk.peek() > s.charAt(r)) && count[stk.peek() - 'a'] > 1 && operations < k) {
                //Decrement its count
                count[stk.peek() - 'a']--;
                //Remove from stack
                stk.pop();
                //Remove it from result
                sb.deleteCharAt(sb.length() - 1);
                //Increase operations count
                operations++;
            }
            //Add the character to stack
            stk.push(s.charAt(r));
            //Append it to result
            sb.append(s.charAt(r));
            //Move right
            r++;
        }
        //If the last characters are same, then we can remove till count of the character is 1
        if (!stk.isEmpty()) {
            char end = stk.peek();
            stk.pop();
            while (!stk.isEmpty() && stk.peek() == end && count[stk.peek() - 'a'] > 1 && operations < k) {
                count[stk.peek() - 'a']--;
                stk.pop();
                sb.deleteCharAt(sb.length() - 1);
                operations++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LexicographicallySmallestStringWithKOperations lexicographicallySmallestStringWithKOperations = new LexicographicallySmallestStringWithKOperations();
        //o/p should be zaby
        System.out.println(lexicographicallySmallestStringWithKOperations.smallestSubstringWithKOperations("zzabyy", 2));
        //o/p should be aaaabbc (no operations required, because if you perform operation, then it will be aabbc which is not lexicographically small as input is the smaller one
        System.out.println(lexicographicallySmallestStringWithKOperations.smallestSubstringWithKOperations("aaaabbc", 2));
        //o/p should be aaaabbc
        System.out.println(lexicographicallySmallestStringWithKOperations.smallestSubstringWithKOperations("aaaabbcc", 2));
        //o/p should be caba
        System.out.println(lexicographicallySmallestStringWithKOperations.smallestSubstringWithKOperations("cabba", 2));
        //o/p should be cabbac
        System.out.println(lexicographicallySmallestStringWithKOperations.smallestSubstringWithKOperations("cccabbac", 2));
        //o/p should be abbac
        System.out.println(lexicographicallySmallestStringWithKOperations.smallestSubstringWithKOperations("cccabbac", 3));
        //o/p should be abac
        System.out.println(lexicographicallySmallestStringWithKOperations.smallestSubstringWithKOperations("cccabbac", 4));
        //o/p should be abac
        System.out.println(lexicographicallySmallestStringWithKOperations.smallestSubstringWithKOperations("cccabbac", 5));
    }
}
