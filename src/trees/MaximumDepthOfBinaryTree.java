package trees;

public class MaximumDepthOfBinaryTree {

    //The maximum depth of a binary tree is the maximum number of steps to reach a leaf node from the root node.
    // Time complexity : we visit each node exactly once, thus the time complexity is O(N), where N is the number of nodes.
    //Visits each node once to calculate the tree’s maximum depth without duplicating work. Linear O(n) complexity because each node is only visited once.
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
