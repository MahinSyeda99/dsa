package greedy;

import java.util.Stack;

public class ValidParenthesis {

    //O(n) TC - Greedy using two pointers, O(1) - S.C
    public boolean checkValidString(String s) {
        //Max left parenthesis possible
        int leftMax = 0;
        //Min left parenthesis possible
        int leftMin = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                //If left parenthesis, then increment both leftMin and leftMax
                leftMax++;
                leftMin++;
            } else if(s.charAt(i) == ')') {
                //Then increment both leftMin and leftMax
                leftMax--;
                leftMin--;
            } else {
                //Considering * as (, increment leftMax
                leftMax++;
                //Considering * as ) , decrement leftMin
                leftMin--;
            }
            //If left max is less than zero, then we can't match the right bracket
            if (leftMax < 0) {
                return false;
            }
            //If left min is less than zero, then set leftMin to zero
            //In this case it doesn't make any sense for us to turn every asterisk into a right parenthesis because
            // it will make the whole string invalid, that's why we treat one asterisk as an empty string and reset our leftMin to 0
            if (leftMin < 0) {
                leftMin = 0;
            }
        }
        //Only when all left are matched, its valid parenthesis
        return leftMin == 0;
    }

    //O(n) TC - Greedy, O(n) - S.C (due to stack)
    public boolean checkValidStringUsingTwoStack(String s) {
        //Stack to keep track of indices of open parenthesis
        Stack<Integer> openStk = new Stack<>();
        //Stack to keep track of indices of *
        Stack<Integer> starStack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                //Add left parenthesis index to openStk
                openStk.add(i);
            } else if (s.charAt(i) == '*') {
                //Add * index to starStack
                starStack.add(i);
            } else {
                //If right parenthesis is seen, then we first check if we found a match in openStk
                if(!openStk.isEmpty()) {
                    openStk.pop();
                } else {
                    //If no left parenthesis is available, then consider * as left parenthesis and pop
                    if (!starStack.isEmpty()) {
                        starStack.pop();
                    } else {
                        //If no * is available, then we can't match the right parenthesis with any left parenthesis
                        return false;
                    }
                }
            }
        }
        //If there are left parenthesis to matched considering there are * available
        while (!openStk.isEmpty() && !starStack.isEmpty()) {
            //If index of left parenthesis is greater than that of *, then * can't be considered as left parenthesis, hence no match
            if(openStk.peek() > starStack.peek()) {
                return false;
            }
            //Otherwise consider * as right parenthesis and pop both left parenthesis and star
            openStk.pop();
            starStack.pop();
        }
        //If all the open parenthesis are matched, then valid parenthesis
        return openStk.isEmpty();
    }

    //O(3^n) backtracking solution
    public boolean checkValidStringBackTracking(String s) {
        int count = 0;
        return checkValidStringH(s, 0, count);
    }

    public boolean checkValidStringH(String s, int i, int count) {
        //If we have completed string traversal and count of left parenthesis is zero, then valid string
        if(i >= s.length()) {
            return count == 0;
        }
        //If left parenthesis, then increment count and call the function to check for remaining string
        if(s.charAt(i) == '(') {
            count++;
            return checkValidStringH(s, i+1, count);
        } else if(s.charAt(i) == ')') {
            //If right parenthesis, then decrement count if count > 0, otherwise, we don't have any left for right parenthesis, so return false. Else, call the function to check for remaining string
            if(count > 0) {
                count--;
            } else {
                return false;
            }
            return checkValidStringH(s, i+1, count);
        } else {
            //Consider * as empty string
            if (checkValidStringH(s, i + 1, count)) {
                return true;
            }
            //Consider * as left parenthesis
            count++;
            if (checkValidStringH(s, i + 1, count)) {
                return true;
            }
            //Decrement as its not left parenthesis
            count--;
            //Consider * as right parenthesis
            if(count > 0) {
                count--;
            } else {
                return false;
            }
            return checkValidStringH(s, i+1, count);
        }
    }

    public static void main(String[] args) {
        //String length is 100
        String s = "**************************************************))))))))))))))))))))))))))))))))))))))))))))))))))";
        ValidParenthesis validParenthesis = new ValidParenthesis();
        System.out.println(validParenthesis.checkValidString(s));
    }
}
