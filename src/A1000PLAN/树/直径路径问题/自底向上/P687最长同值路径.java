package A1000PLAN.树.直径路径问题.自底向上;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/20
 **/
public class P687最长同值路径 {
    /*
    DFS
     */
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return ans;
    }

    private int ans = -1;

    // 路径问题大多是返回int
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (node.left != null && node.left.val == node.val) {
            left++;
        } else {
            left = 0;
        }
        if (node.right != null && node.right.val == node.val) {
            right++;
        } else {
            right = 0;
        }
        ans = Math.max(left + right, ans);
        return Math.max(left, right);
    }
}
