package trees;

public class DiameterOfBinaryTree {

    //heightOfTree function is called for each node exactly once, and it calculates both the height and diameter along the way.
    // Therefore, the time complexity remains O(n)

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        heightOfTree(root);
        return maxDiameter;
    }

    //Returns height
    public int heightOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //left height
        int left = heightOfTree(root.left);
        //right height
        int right = heightOfTree(root.right);
        //Diameter is number of nodes between the two leaf nodes, which is height of left tree + height of right tree
        int diameter = left + right;
        //Store the max of diameters
        maxDiameter = Math.max(diameter, maxDiameter);
        //Return height
        return 1 + Math.max(left, right);
    }
}
