package trees;

public class InvertBinaryTree {

    //O(n) TC, Since the inversion needs to happen at each node, where n is the number of nodes in the tree, because each node is visited once
    //O(h) recursive call stack auxiliary space, h is height of tree
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
