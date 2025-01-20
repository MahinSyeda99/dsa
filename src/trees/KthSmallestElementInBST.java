package trees;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInBST {
    private int count = 0;

    //O(n) T.C, O(n) S.C
    public int kthSmallest(TreeNode root, int k) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root, list, k);
        return list.get(k - 1).val;
    }

    public void inorder(TreeNode root, List<TreeNode> list, int k) {
        if (root == null) {
            return;
        }
        if (list.size() == k) {
            return;
        }
        inorder(root.left, list, k);
        if (list.size() != k) {
            list.add(root);
        }
        inorder(root.right, list, k);
    }

    //O(n) T.C, O(1) S.C + O(h) recursive stack space
    public int kthSmallestUsingCounter(TreeNode root, int k) {
        int[] ans = new int[1];
        inorderUsingCounter(root, ans, k);
        return ans[0];
    }

    public void inorderUsingCounter(TreeNode root, int[] ans, int k) {
        if (root == null) {
            return;
        }
        if (count >= k) {
            return;
        }
        inorderUsingCounter(root.left, ans, k);
        count++;
        if (count == k) {
            ans[0] = root.val;
        }
        inorderUsingCounter(root.right, ans, k);
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
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(6);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(4);
        treeNode.left.left.left = new TreeNode(1);
        KthSmallestElementInBST kthSmallestElementInBST = new KthSmallestElementInBST();
        //Expected o/p: 3
//        System.out.println(kthSmallestElementInBST.kthSmallest(treeNode, 3));
        System.out.println(kthSmallestElementInBST.kthSmallestUsingCounter(treeNode, 3));
    }
}
