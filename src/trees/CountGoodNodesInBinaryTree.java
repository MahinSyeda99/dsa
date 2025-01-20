package trees;

import java.util.List;

/**
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/
 */
public class CountGoodNodesInBinaryTree {

    private int count = 0;

    public int goodNodes(TreeNode root) {
        goodNodesH(root, Integer.MIN_VALUE);
        return count;
    }

    public void goodNodesH(TreeNode root, int max) {
        if(root == null) {
            return;
        }
        if(root.val >= max) {
            max = root.val;
            count++;
        }
        if(root.left != null) {
            goodNodesH(root.left, max);
        }
        if(root.right != null) {
            goodNodesH(root.right, max);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        //[3,1,4,3,null,1,5] , o/p: 4
        TreeNode treeNode = new TreeNode(3);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(4);
        treeNode.left = left1;
        treeNode.right = right1;
        TreeNode left2 = new TreeNode(3);
        left1.left = left2;
        TreeNode left3 = new TreeNode(1);
        right1.left = left3;
        TreeNode right2 = new TreeNode(5);
        right1.right = right2;
        CountGoodNodesInBinaryTree countGoodNodesInBinaryTree = new CountGoodNodesInBinaryTree();
        int count = countGoodNodesInBinaryTree.goodNodes(treeNode);
        System.out.println(count);
    }
}
