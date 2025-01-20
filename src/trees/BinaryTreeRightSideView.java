package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * https://leetcode.com/problems/binary-tree-right-side-view/description/
 */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        //Algo Used: Pre - order traversal. We can use any traversal over here, we are not using BFS bcz in that the SC can be halk of N at times,
        // here the SC is O(Height) , TC: O(N)
        // In pre-order we do Ro-L-R but for printing right view we are doing Ro-R-L,
        // for printing left view we can use Ro-L-R
        rightSideViewH(root, 0, result);
        return result;
    }

    public List<Integer> rightSideViewQueueSoln(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        //Using Breadth First Search or level order traversal
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(q.size() != 0) {
            int size = q.size();
            while(size > 0) {
                if(q.peek().left != null) {
                    q.add(q.peek().left);
                }
                if(q.peek().right != null) {
                    q.add(q.peek().right);
                }
                if(size == 1) {
                    result.add(q.peek().val);
                }
                q.remove();
                size--;
            }
        }
        return result;
    }

    public void rightSideViewH(TreeNode root, int level, List<Integer> result) {
        if (root == null) {
            return;
        }
        //level == result.size(), this will ensure that we are adding the first element only of that level  from the right side in the list.
        if (result.size() == level) {
            result.add(root.val);
        }
        rightSideViewH(root.right, level + 1, result); //Go right
        rightSideViewH(root.left, level + 1, result); //Go left
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        //[1,2,3,null,5,null,4] , o/p: [1,3,4]
        TreeNode treeNode = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        treeNode.left = left1;
        treeNode.right = right1;
        TreeNode right2 = new TreeNode(5);
        left1.right = right2;
        TreeNode right3 = new TreeNode(4);
        right1.right = right3;
        BinaryTreeRightSideView binaryTreeRightSideView = new BinaryTreeRightSideView();
        List<Integer> result = binaryTreeRightSideView.rightSideView(treeNode);
        System.out.println(result);

        result = binaryTreeRightSideView.rightSideViewQueueSoln(treeNode);
        System.out.println(result);
    }
}
