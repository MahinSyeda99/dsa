package trees;

public class LowestCommonAncestorOfBST {

    //O(n) TC
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            //If p,q values are lower tha root val, then result in left tree
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                //If p,q values are greater tha root val, then result in right tree
                root = root.right;
            } else {
                //If p,q values are lower and greater tha root val or greater and lower than root val, then result is root
                break;
            }
        }
        return root;
    }
}
