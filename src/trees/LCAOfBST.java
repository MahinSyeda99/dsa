package trees;

/**
 * Given a binary search tree (BST) where all node values are unique, and two nodes from the tree p and q, return the lowest common ancestor (LCA) of the two nodes.
 *
 * The lowest common ancestor between two nodes p and q is the lowest node in a tree T such that both p and q as descendants. The ancestor is allowed to be a descendant of itself.
 *
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LCAOfBST {

    //TC: O(logN)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null) {
            //If p,q values are lower tha root val, then result in left tree
            if(p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if(p.val > root.val && q.val > root.val) {
                //If p,q values are greater tha root val, then result in right tree
                root = root.right;
            } else {
                //If p,q values are lower and greater tha root val or greater and lower than root   val, then result is root
                break;
            }
        }
        return root;
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
        //[3,1,4,null,2]
        // p = 2, q = 3 Result: 3
        // p = 2, q = 4 Result: 3
        TreeNode treeNode = new TreeNode(3);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(4);
        treeNode.left = left1;
        treeNode.right = right1;
        TreeNode right2 = new TreeNode(2);
        left1.right = right2;
        LCAOfBST lcaOfBST = new LCAOfBST();
        TreeNode result = lcaOfBST.lowestCommonAncestor(treeNode, right2, right1);
        System.out.println(result.val);
    }
}
