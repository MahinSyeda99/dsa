package twoPointers;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (!isASCII(s.charAt(i))) {
                i++;
            } else if (!isASCII(s.charAt(j))) {
                j--;
            } else {
                if (Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isASCII(char c) {
        return ((int) c >= 97 && (int) c <= 122) || ((int) c >= 48 && (int) c <= 57) || ((int) c >= 65 && (int) c <= 90);
    }
}
