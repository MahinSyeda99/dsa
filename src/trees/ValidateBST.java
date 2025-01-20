package trees;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/description/
 */
public class ValidateBST {

    //This can also be solved using inorder traversal
    //Do inorder traversal and add the values to a list
    //Iterate through the list and check if list[i] > list[i - 1]. If not, then it's not a valid BST

    //O(n) TC
    public boolean isValidBST(TreeNode root) {
        return isBSTH(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //A Binary Search Tree (BST) has these properties:
    //The left subtree of a node contains only nodes with values less than the node's value.
    //The right subtree of a node contains only nodes with values greater than the node's value.
    //Both the left and right subtrees must also be BSTs.
    public boolean isBSTH(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        //If root.val <= min or root.val >= max, return false because it means the node's value is out of the allowed range.
        //This maintains that all nodes in the left subtree are strictly less than the parent node, and all nodes in the right subtree are strictly greater.
        if (root.val <= min || root.val >= max) {
            return false;
        }
        //For the left subtree, the allowed maximum (max) is updated to root.val. This ensures all nodes in the left subtree are less than root.val
        //For the right subtree, the allowed minimum (min) is updated to root.val. This ensures all nodes in the right subtree are greater than root.val
        //The function returns true only if both the left and right subtrees are valid BSTs
        return isBSTH(root.left, min, root.val) && isBSTH(root.right, root.val, max);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(4);
        treeNode.left = left1;
        treeNode.right = right1;
        TreeNode left2 = new TreeNode(3);
        TreeNode right2 = new TreeNode(6);
        right1.left = left2;
        right1.right = right2;
        ValidateBST validateBST = new ValidateBST();
        //Expected o/p: false
        System.out.println(validateBST.isValidBST(treeNode));
    }
}
