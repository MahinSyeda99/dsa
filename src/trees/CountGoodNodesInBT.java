package trees;

/**
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/
 */
public class CountGoodNodesInBT {

    private int count = 0;

    //TC: O(n)
    public int goodNodes(TreeNode root) {
        //We will maintain max along the path from root to X, so that if we find a node X with value >= max, then we can consider that as good node
        goodNodesH(root, Integer.MIN_VALUE);
        return count;
    }

    public void goodNodesH(TreeNode root, int max) {
        if (root == null) {
            return;
        }
        //If the value of node >= max along path from root of BT to current node
        if (root.val >= max) {
            max = root.val;
            count++;
        }
        //Go left
        if (root.left != null) {
            goodNodesH(root.left, max);
        }
        //Go right
        if (root.right != null) {
            goodNodesH(root.right, max);
        }
    }
}
