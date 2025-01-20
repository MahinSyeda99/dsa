package trees;

public class BalancedBinaryTree {

    private boolean balanced = true;

    //O(n) TC
    public boolean isBalanced(TreeNode root) {
        heightOfTree(root);
        return balanced;
    }

    public int heightOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = heightOfTree(root.left);
        int right = heightOfTree(root.right);
        //If difference in heights is greater than 1, then its not balanced
        if (Math.abs(left - right) > 1) {
            balanced = false;
        }
        return Math.max(left, right) + 1;
    }
}
