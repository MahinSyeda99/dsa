package trees;

public class SameTree {

    //TC is O(n), where n is the minimum number of nodes in either tree p or q. Since each node is visited once, the time complexity is O(n)
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
