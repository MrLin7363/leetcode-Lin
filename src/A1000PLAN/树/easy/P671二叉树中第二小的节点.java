package A1000PLAN.树.easy;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/13
 **/
public class P671二叉树中第二小的节点 {
    /*
    1.DFS,记录值，剪枝叶 - 推荐
    特性：子节点一定>=root.val    root.val = min(root.left.val, root.right.val) 总成立。
     */
    private int ans;

    private int rootVal;

    public int findSecondMinimumValue(TreeNode root) {
        rootVal = root.val;
        ans = -1;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        // 剪枝:如果已经>=第二的值，再往下递归也是>=
        if (ans != -1 && root.val >= ans) {
            return;
        }
        if (root.val > rootVal) {
            ans = root.val;
        }
        dfs(root.left);
        dfs(root.right);
    }

}
