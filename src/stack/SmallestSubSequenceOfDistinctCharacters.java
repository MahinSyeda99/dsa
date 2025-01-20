package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/description/
 * https://leetcode.com/problems/remove-duplicate-letters/description/
 */
public class SmallestSubSequenceOfDistinctCharacters {

    //O(26 * n) TC, i.e. O(n) TC and O(26) SC
    public String smallestSubsequence(String s) {
        //stack will store the distinct characters in order
        Stack<Integer> stack = new Stack<>();

        //'last' stores the last index of a character in string
        int[] last = new int[26];
        //'seen' is a check that represents if we have added the character in stack or not.
        boolean[] seen = new boolean[26];

        //Result
        StringBuilder sb = new StringBuilder();

        //Populate last array
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            //If we have already included the current character into the stack, just skip it.
            if (seen[c]) {
                continue;
            }
            //If we haven't included the element, we have to include it anyhow. Also we are marking it as 'seen'.. by updating the value by 1 at that index.
            seen[c] = true;

            //If the incoming character is smaller && we are sure that the peek() element will occur in future as well.. then we will just pop that element and make the seen false for that element.
            while (!stack.empty() && stack.peek() > c && i < last[stack.peek()]) {
                seen[stack.pop()] = false;
                //Remove it from result as well
                sb.deleteCharAt(sb.length() - 1);
            }
            //since it was a new character, we have to include it.
            stack.push(c);
            //Add it to result
            sb.append((char) (c + 'a'));
        }

        // String result = "";
        //Now our stack contains the desired result, append each into the string.
        //Note: we are just traversing the stack.. not popping, so the elements are in fifo style.
        // for (int c : stack) {
        //     result = result + (char) (c + 'a');
        // }
        return sb.toString();
    }
}
