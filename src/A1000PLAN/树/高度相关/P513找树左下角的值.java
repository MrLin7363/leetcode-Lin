

package A1000PLAN.树.高度相关;

import Construct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/14
 **/
public class P513找树左下角的值 {
    /*
    1.DFS优化版 对同一高度的所有节点，最左节点肯定是最先被遍历到的。
     */
    private int curHeight = 0;

    private int val = 0;

    public int findBottomLeftValue(TreeNode root) {
        recur(root, 0);
        return val;
    }

    private void recur(TreeNode node, int height) {
        if (node == null) {
            return;
        }
        height++;
        recur(node.left, height);
        recur(node.right, height);
        if (height > curHeight) {
            curHeight = height;
            val = node.val;
        }
    }

    /*
    2.BFS-从右往左，最后一层的最后一个就是结果
     */
    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            res = node.val;
        }
        return res;
    }

    /*
    3.DFS-未优化版本，自己写的 层优先：找最低层的结点，列表里第一个就是; 前序遍历
     */
    private Map<Integer, List<TreeNode>> map = new HashMap<>();

    private int maxLevel = 0;

    public int findBottomLeftValue3(TreeNode root) {
        dfs(root, 0);
        return map.get(maxLevel).get(0).val;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        maxLevel = Math.max(maxLevel, level);
        if (node.left == null && node.right == null) {
            if (!map.containsKey(level)) {
                map.put(level, new ArrayList<>());
            }
            map.get(level).add(node);
        }

        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }
}
