package trees;

import java.util.HashMap;
import java.util.Map;

public class ConstructBSTFromInorderAndPreOrder {
    private int preIndex = 0;
    private Map<Integer, Integer> mp = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            mp.put(inorder[i], i);
        }
        return buildTreeH(preorder, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeH(int[] preorder, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        /* Pick current node from Preorder traversal using preIndex
           and increment preIndex */
        TreeNode newNode = new TreeNode(preorder[preIndex++]);
        /* If this node has no children then return the node*/
        if (inStart == inEnd) {
            return newNode;
        }
        //find the index of this node in Inorder traversal
        int inIndex = mp.get(newNode.val);
        //Using index in Inorder traversal, construct left and right subtrees
        newNode.left = buildTreeH(preorder, inorder, inStart, inIndex - 1);
        newNode.right = buildTreeH(preorder, inorder, inIndex + 1, inEnd);
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

    public void printPreOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
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
        int[] inorder = {9, 3, 15, 20, 7};
        int[] preorder = {3, 9, 20, 15, 7};
        ConstructBSTFromInorderAndPreOrder constructBSTFromInorderAndPreOrder = new ConstructBSTFromInorderAndPreOrder();
        TreeNode treeNode = constructBSTFromInorderAndPreOrder.buildTree(preorder, inorder);
        constructBSTFromInorderAndPreOrder.printInorder(treeNode);
        System.out.println();
        constructBSTFromInorderAndPreOrder.printPreOrder(treeNode);
    }
}
