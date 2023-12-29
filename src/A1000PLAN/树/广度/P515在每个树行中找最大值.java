package A1000PLAN.树.广度;

import Construct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/18
 **/
public class P515在每个树行中找最大值 {
    /*
    BFS-层次遍历
     */
    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(max);
        }
        return ans;
    }

    // DFS 左根右，高度等于当前列表的大小就是新进入这一层的意思
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        dfs(root, ans, 0);
        return ans;
    }

    private void dfs(TreeNode node, List<Integer> ans, int curHeight) {
        if (node == null) {
            return;
        }
        // 新进入到这一层
        if (curHeight == ans.size()) {
            ans.add(node.val);
        } else {
            ans.set(curHeight, Math.max(ans.get(curHeight), node.val));
        }
        dfs(node.left, ans, curHeight + 1);
        dfs(node.right, ans, curHeight + 1);
    }
}
