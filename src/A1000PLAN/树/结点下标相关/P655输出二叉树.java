package A1000PLAN.树.结点下标相关;

import Construct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/18
 **/
public class P655输出二叉树 {
    /*
    模拟： 1.计算树高
          2.初始化矩阵
          3.DFS树结点，分成两部分，填充矩阵
          注意：题目高度从0开始,第一个结点高度为0
     */
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> ans = new ArrayList<>();
        int height = calDepth(root);
        int rows = height + 1;
        int cols = (1 << (height + 1)) - 1; //  Math.pow(2,4) == 1<<4
        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<String>();
            for (int j = 0; j < cols; j++) {
                row.add("");
            }
            ans.add(row);
        }
        dfs(root, ans, 0, (cols - 1) / 2, height);
        return ans;
    }

    // 这里求高度是从0开始
    private int calDepth(TreeNode root) {
        int h = 0;
        if (root.left != null) {
            h = Math.max(h, calDepth(root.left) + 1);
        }
        if (root.right != null) {
            h = Math.max(h, calDepth(root.right) + 1);
        }
        return h;
    }

    private void dfs(TreeNode node, List<List<String>> ans, int r, int c, int height) {
        if (node == null) {
            return;
        }
        ans.get(r).set(c, Integer.toString(node.val));
        dfs(node.left, ans, r + 1, c - (1 << (height - r - 1)), height);
        dfs(node.right, ans, r + 1, c + (1 << (height - r - 1)), height);
    }

    // 这里求深高度是从1开始
    private int maxHeight(TreeNode root) {
        return root == null ? 0 : Math.max(maxHeight(root.left), maxHeight(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        new P655输出二叉树().printTree(root);
    }
}
