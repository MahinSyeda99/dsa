package trees;

public class SubTreeOfAnotherTree {


    //TC is O(m * n) where, m is the number of nodes in the root tree and n is the number of nodes in the subRoot tree
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null && subRoot != null) {
            return false;
        }
        if (root.val == subRoot.val) {
            if (isSameTree(root, subRoot)) {
                return true;
            }
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        } else {
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null && q == null)) {
            return true;
        }
        if ((p != null && q == null) || (p == null && q != null)) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
