package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    //O(n*4^n) T.C, because for each digit max no.of possibilities is 4 and if length of digits is n, total no.of possibilities is 4^n
    //To form each combination of length n, O(n). Hence, total T.C = O(n*4^n)
    //S.C: O(n). Because, max stack length will be length of digits which is n
    public List<String> letterCombinationsUsingBacktracking(String digits) {
        //Map of digit to letters
        Map<Character, String> mp = new HashMap<>();
        mp.put('2', "abc");
        mp.put('3', "def");
        mp.put('4', "ghi");
        mp.put('5', "jkl");
        mp.put('6', "mno");
        mp.put('7', "pqrs");
        mp.put('8', "tuv");
        mp.put('9', "wxyz");

        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }
        letterCombinationsBacktrackingH(digits, 0, "", result, mp);
        return result;
    }

    private void letterCombinationsBacktrackingH(String digits, int i, String current, List<String> result, Map<Character, String> mp) {
        //If we have formed one combination with all digits, then add it to result
        if (i == digits.length()) {
            result.add(current);
            return;
        }

        //Get the letters representing the digit at index i
        String numberLetters = mp.get(digits.charAt(i));
        for (int j = 0; j < numberLetters.length(); j++) {
            //For each of the letters, we add it to current
            current = current + numberLetters.charAt(j);
            //We then call recursively for the next digit
            letterCombinationsBacktrackingH(digits, i + 1, current, result, mp);
            //Backtrack, remove the letter
            current = current.substring(0, current.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        //Map of digit to letters
        Map<Character, String> mp = new HashMap<>();
        mp.put('2', "abc");
        mp.put('3', "def");
        mp.put('4', "ghi");
        mp.put('5', "jkl");
        mp.put('6', "mno");
        mp.put('7', "pqrs");
        mp.put('8', "tuv");
        mp.put('9', "wxyz");

        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }
        //Form the initial result for the 1st digit
        String numberLetters = mp.get(digits.charAt(0));
        for (int j = 0; j < numberLetters.length(); j++) {
            result.add(String.valueOf(numberLetters.charAt(j)));
        }
        //Find teh combinations using rest of the digits
        for (int i = 1; i < digits.length(); i++) {
            result = letterCombinationsH(digits, i, result, mp);
        }
        return result;
    }

    private List<String> letterCombinationsH(String digits, int i, List<String> result, Map<Character, String> mp) {
        String numberLetters = mp.get(digits.charAt(i));
        List<String> newResult = new ArrayList<>();

        //For each combination in result, find the combinations with the current digit letters
        for (int j = 0; j < result.size(); j++) {
            for (int k = 0; k < numberLetters.length(); k++) {
                newResult.add(result.get(j) + numberLetters.charAt(k));
            }
        }
        return newResult;
    }

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        //["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(letterCombinations.letterCombinations("23"));
        //["adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh", "afi", "bdg", "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh", "bfi", "cdg", "cdh", "cdi", "ceg", "ceh", "cei", "cfg", "cfh", "cfi"]
        System.out.println(letterCombinations.letterCombinations("234"));
        //[]
        System.out.println(letterCombinations.letterCombinations(""));

        //Using Backtracking
        //["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(letterCombinations.letterCombinationsUsingBacktracking("23"));
        //["adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh", "afi", "bdg", "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh", "bfi", "cdg", "cdh", "cdi", "ceg", "ceh", "cei", "cfg", "cfh", "cfi"]
        System.out.println(letterCombinations.letterCombinationsUsingBacktracking("234"));
        //[]
        System.out.println(letterCombinations.letterCombinationsUsingBacktracking(""));

    }
}
