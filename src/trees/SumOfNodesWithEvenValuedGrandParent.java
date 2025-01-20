package trees;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SumOfNodesWithEvenValuedGrandParent {

    public int sumEvenGrandparentDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (root.val%2 == 0 && root.left != null && root.left.left != null) {
            sum = sum + root.left.left.val;
        }
        if (root.val%2 == 0 && root.left != null && root.left.right != null) {
            sum = sum + root.left.right.val;
        }
        if (root.val%2 == 0 && root.right != null && root.right.left != null) {
            sum = sum + root.right.left.val;
        }
        if (root.val%2 == 0 && root.right != null && root.right.right != null) {
            sum = sum + root.right.right.val;
        }
        return sum + sumEvenGrandparent(root.left) + sumEvenGrandparent(root.right);
    }


    public int sumEvenGrandparent(TreeNode root) {
        Set<TreeNode> s1 = new HashSet<>();
        Set<TreeNode> s2 = new HashSet<>();
        if (root == null) {
            return 0;
        }
        int level = 0;
        Queue<Pair<TreeNode, TreeNode>> q = new LinkedList<>();
        q.add(new Pair<>(root, null));
        if ((root.val & 1) == 0) {
            s1.add(root);
        }
        level++;
        int sum = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            Set<TreeNode> s1New = new HashSet<>();
            Set<TreeNode> s2New = new HashSet<>();
            Pair<TreeNode, TreeNode> curr;
            while(size > 0) {
                curr = q.poll();
                if (curr.getKey().left != null) {
                    q.add(new Pair<>(curr.getKey().left, curr.getKey()));
                    if (level > 1) {
                        if ((level & 1) == 0) {
                            if (s1.contains(curr.getValue())) {
                                sum = sum + curr.getKey().left.val;
                            }
                        } else {
                            if (s2.contains(curr.getValue())) {
                                sum = sum + curr.getKey().left.val;
                            }
                        }
                    }
                    if ((curr.getKey().left.val & 1) == 0) {
                        if ((level & 1) == 0) {
                            s1New.add(curr.getKey().left);
                        } else {
                            s2New.add(curr.getKey().left);
                        }
                    }
                }
                if (curr.getKey().right != null) {
                    q.add(new Pair<>(curr.getKey().right, curr.getKey()));
                    if (level > 1) {
                        if ((level & 1) == 0) {
                            if (s1.contains(curr.getValue())) {
                                sum = sum + curr.getKey().right.val;
                            }
                        } else {
                            if (s2.contains(curr.getValue())) {
                                sum = sum + curr.getKey().right.val;
                            }
                        }
                    }
                    if ((curr.getKey().right.val & 1) == 0) {
                        if ((level & 1) == 0) {
                            s1New.add(curr.getKey().right);
                        } else {
                            s2New.add(curr.getKey().right);
                        }
                    }
                }
                size--;
            }
            if ((level & 1) == 0) {
                s1 = s1New;
            } else {
                s2 = s2New;
            }
            level++;
        }
        return sum;
    }

    class Pair<T, V> {
        T key;
        V value;

        Pair(T first, V second) {
            this.key = first;
            this.value = second;
        }

        public T getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }

    public static void main(String[] args) {
        SumOfNodesWithEvenValuedGrandParent sumOfNodesWithEvenValuedGrandParent = new SumOfNodesWithEvenValuedGrandParent();

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(7);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(3);
        root.left.left.left = new TreeNode(9);
        root.left.right.left = new TreeNode(1);
        root.left.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        System.out.println(sumOfNodesWithEvenValuedGrandParent.sumEvenGrandparentDFS(root));


        TreeNode rootNew = new TreeNode(79);
        rootNew.left = new TreeNode(52);
        rootNew.right = new TreeNode(86);
        rootNew.left.left = new TreeNode(71);
        rootNew.left.right = new TreeNode(12);
        rootNew.right.right = new TreeNode(2);
        rootNew.left.right.right = new TreeNode(81);
        rootNew.right.right.left = new TreeNode(91);
        rootNew.right.right.right = new TreeNode(1);
        rootNew.right.right.right.right = new TreeNode(93);
        System.out.println(sumOfNodesWithEvenValuedGrandParent.sumEvenGrandparentDFS(rootNew));
    }
}
