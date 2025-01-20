package trees;

import java.util.HashMap;
import java.util.Map;

public class ConstructBSTFromInorderAndPostOrder {
    private int postIndex;
    private Map<Integer, Integer> mp = new HashMap<>();

    public TreeNode buildTree(int[] postorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            mp.put(inorder[i], i);
        }
        postIndex = postorder.length - 1;
        return buildTreeH(postorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeH(int[] postorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        /* Pick current node from Preorder traversal using preIndex
           and decrement preIndex */
        TreeNode newNode = new TreeNode(postorder[postIndex--]);
        /* If this node has no children then return the node*/
        if (inStart == inEnd) {
            return newNode;
        }
        //find the index of this node in Inorder traversal
        int inIndex = mp.get(newNode.val);
        //Using index in Inorder traversal, construct left and right subtrees
        newNode.right = buildTreeH(postorder, inIndex + 1, inEnd);
        newNode.left = buildTreeH(postorder, inStart, inIndex - 1);
        return newNode;
    }

    public void printInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }

    public void printPostOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.val + " ");
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int[] postorder = {8, 4, 5, 2, 6, 7, 3, 1};
        int[] inorder = {4, 8, 2, 5, 1, 6, 3, 7};
        ConstructBSTFromInorderAndPostOrder constructBSTFromInorderAndPostOrder = new ConstructBSTFromInorderAndPostOrder();
        TreeNode treeNode = constructBSTFromInorderAndPostOrder.buildTree(postorder, inorder);
        constructBSTFromInorderAndPostOrder.printInorder(treeNode);
        System.out.println();
        constructBSTFromInorderAndPostOrder.printPostOrder(treeNode);
    }
}
