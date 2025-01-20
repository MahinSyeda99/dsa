package trees;

public class BinaryTreeMaxPathSum {

    //O(n) TC
    public int maxPathSum(TreeNode root) {
        int[] res = new int[1];
        //Initialize with minimum integer
        res[0] = Integer.MIN_VALUE;
        maxPathSumH(root, res);
        return res[0];
    }

    public int maxPathSumH(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        //Find the left max path sum without splitting. If it's negative, then our max is 0
        int leftMax = Math.max(maxPathSumH(root.left, res), 0);
        //Find the right max path sum without splitting. If it's negative, then our max is 0
        int rightMax = Math.max(maxPathSumH(root.right, res), 0);

        //If this is the point where we split, then our result could be this
        int sumWithSplitting = root.val + leftMax + rightMax;
        res[0] = Math.max(res[0], sumWithSplitting);

        //Return max value with splitting
        return root.val + Math.max(leftMax, rightMax);
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
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        BinaryTreeMaxPathSum binaryTreeMaxPathSum = new BinaryTreeMaxPathSum();
        //Expected output is 20+15+7 = 42. i.e optimal path is 15->20->7
        System.out.println(binaryTreeMaxPathSum.maxPathSum(root));
    }
}
