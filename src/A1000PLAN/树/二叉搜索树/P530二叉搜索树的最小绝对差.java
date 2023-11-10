package A1000PLAN.树.二叉搜索树;

import Construct.TreeNode;

/**
 *desc:
 *@author   
 *@since 2023/11/3
 **/
public class P530二叉搜索树的最小绝对差 {
    /*
    中序遍历的相邻元素的差值中 求最小
    左根右，因为中序遍历数组是升序的

    每次需要记录上一个遍历到的结点， 如案例遍历到第一个结点104时，不比较，遍历到236时，前一个结点时227
     */
    private int min = Integer.MAX_VALUE;

    TreeNode prev = null;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev == null) {  // 如果前一个节点还未被遍历到，则当前节点的值就是前一个节点的值
            prev = root;
        } else { // 如果前一个节点已经被遍历到，则计算当前节点值与前一个节点值的绝对差，并更新ans
            min = Math.min(min, Math.abs(root.val - prev.val));
            prev = root;
        }
        dfs(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(236);
        root.left = new TreeNode(104);
        root.right = new TreeNode(701);
        root.left.right = new TreeNode(227);
        root.right.right = new TreeNode(911);
        new P530二叉搜索树的最小绝对差().getMinimumDifference(root);
    }
}
