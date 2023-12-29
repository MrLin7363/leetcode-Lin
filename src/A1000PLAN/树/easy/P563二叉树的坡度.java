package A1000PLAN.树.easy;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/13
 **/
public class P563二叉树的坡度 {
    /*
    1. map记录当前结点的子节点所有值和(包括当前结点)
    2. 一次遍历树，求和，自底向上，过程中求坡度
     */
    private int res;

    public int findTilt(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftVal = dfs(node.left);
        int rightVal = dfs(node.right);

        res += Math.abs(leftVal - rightVal);
        return node.val + leftVal + rightVal;
    }
}
