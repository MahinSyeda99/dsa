package trees;

import java.util.*;
import java.util.stream.Collectors;

public class SerializeDeserializeBT {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder serialized = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode v = q.peek();
            q.poll();
            if (serialized.toString().isEmpty()) {
                serialized.append(v == null ? "n" : v.val);
            } else {
                serialized.append(",").append(v == null ? "n" : v.val);
            }
            if (v != null) {
                q.add(v.left);
                q.add(v.right);
            }
        }
        return serialized.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("") || data.equals("n")) {
            return null;
        }
        String[] values = data.split(",");
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        q.add(root);
        for(int i = 1; i < values.length; i++) {
            TreeNode parentNode = q.poll();
            if(!values[i].equals("n")) {
                parentNode.left = new TreeNode(Integer.valueOf(values[i]));
                q.add(parentNode.left);
            }
            if(!values[++i].equals("n")) {
                parentNode.right = new TreeNode(Integer.valueOf(values[i]));
                q.add(parentNode.right);
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
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        SerializeDeserializeBT serializeDeserializeBT = new SerializeDeserializeBT();
        String serialized = serializeDeserializeBT.serialize(root);
        System.out.println(serialized);
        TreeNode newRoot = serializeDeserializeBT.deserialize(serialized);
        System.out.println(serializeDeserializeBT.serialize(newRoot));
    }
}
