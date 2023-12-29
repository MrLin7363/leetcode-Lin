package A1000PLAN.树.直径路径问题.自底向上;

import Construct.TreeNode;

/**
 *desc:
 *@author   
 *@since 2023/11/2
 **/
public class P543二叉树的直径 {
    // 左子树最大直径+右子树最大直径
    // 不一定经过根节点，所以过程中记录最大值
    private int max;

    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        maxLength(root);
        return max - 1;
    }

    // 这是是经过的最多节点数
    private int maxLength(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int L = maxLength(node.left);
        int R = maxLength(node.right);
        max = Math.max(L + R + 1, max);
        return Math.max(L, R) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        new P543二叉树的直径().diameterOfBinaryTree(root);
    }
}
