package A1000PLAN.树.easy;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/13
 **/
public class P606根据二叉树创建字符串 {
    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }

    private void dfs(TreeNode node, StringBuilder str) {
        if (node == null) {
            return;
        }
        str.append(node.val);
        if (node.left == null && node.right == null) {
            return;
        }
        str.append("(");
        dfs(node.left, str);
        str.append(")");

        // 省去不必要的右()
        if (node.right != null) {
            str.append("(");
            dfs(node.right, str);
            str.append(")");
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(4);
        new P606根据二叉树创建字符串().tree2str(root);
    }
}

