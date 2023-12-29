package A1000PLAN.树.easy;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/14
 **/
public class P965单值二叉树 {
    private int val;

    public boolean isUnivalTree(TreeNode root) {
        val = root.val;
        return dfs(root);
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.val != val) {
            return false;
        }
        return dfs(root.left) && dfs(root.right);
    }
}
