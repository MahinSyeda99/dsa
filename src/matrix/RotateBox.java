package matrix;

/**
 * https://leetcode.com/problems/rotating-the-box/description/
 */
public class RotateBox {

    //O(m*n) TC solution
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] result = new char[n][m];

        //For each row
        for (int i = 0; i < m; i++) {
            //Keep a track of start of stones, if a stone is encountered
            int start = 0;
            //Our right pointer
            int end = 0;
            while (start < n && end < n) {
                if (box[i][end] == '.') {
                    //Empty case
                    if (start == end) { //No stone encountered, so just assign empty in result
                        result[end][m - 1 - i] = '.';
                        //Move both the pointers
                        end++;
                        start++;
                        continue;
                    } else {
                        //Stone encountered, so move the stones to current empty
                        result[end][m - 1 - i] = '#'; //Current will become stone
                        result[start][m - 1 - i] = '.'; //Start will become empty
                        //Move both the pointers
                        start++;
                        end++;
                    }
                } else if (box[i][end] == '#') {
                    //Stone case
                    result[end][m - 1 - i] = '#'; //Assign stone to result
                    end++; //Move right pointer only, start pointer indicating the start of stones
                } else {
                    //Obstacle case
                    result[end][m - 1 - i] = '*'; //Assign obstacle to result
                    //Move right pointer
                    end++;
                    //Reset start pointer to right pointer
                    start = end;
                }
            }
        }
        return result;
    }
}
