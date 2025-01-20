package trees;

import java.util.ArrayList;
import java.util.List;

public class RightSideViewOfBinaryTree {

    //O(n) TC
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        //Algo Used: Pre - order traversal. We can use any traversal over here, we are not using BFS bcz in that the SC can be half of N at times,
        // here the SC is O(Height) , TC: O(N)
        // In pre-order we do Ro-L-R but for printing right view we are doing Ro-R-L,
        // for printing left view we can use Ro-L-R
        rightSideViewH(root, 0, result);
        return result;
    }

    public void rightSideViewH(TreeNode root, int level, List<Integer> result) {
        if (root == null) {
            return;
        }
        //level == result.size(), this will ensure that we are adding the first element only of that level from the right side in the list.
        if (result.size() == level) {
            result.add(root.val);
        }
        rightSideViewH(root.right, level + 1, result); //Go right
        rightSideViewH(root.left, level + 1, result); //Go left
    }
}
