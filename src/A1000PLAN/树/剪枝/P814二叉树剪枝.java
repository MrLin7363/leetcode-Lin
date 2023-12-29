package A1000PLAN.树.剪枝;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/19
 **/
public class P814二叉树剪枝 {
    /*
    当这三个条件：左子树为空，右子树为空，当前节点的值为0，同时满足时，才表示以当前节点为根的原二叉树的所有节点都为 0，
    1.递归 On - 推荐
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 自底向上
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        // 可以移除,这里移除必须是自底向上，在回溯过程中删除，不然 0 0 0 的情况第一个0删除不了
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    /*
    返回移除了所有不包含 1 的子树的原二叉树。节点 node 的子树为 node 本身加上所有 node 的后代。
    2. 递归:自己写的 On^2
     */
    public TreeNode pruneTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (!haveOne(root)) {
            return null;
        }
        root.left = pruneTree2(root.left);
        root.right = pruneTree2(root.right);
        return root;
    }

    private boolean haveOne(TreeNode node) {
        if (node == null) {
            return false;
        }
        if (node.val == 1) {
            return true;
        }
        return haveOne(node.left) || haveOne(node.right);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(0);
        node.right.right = new TreeNode(1);
        node.right.left = new TreeNode(0);
        new P814二叉树剪枝().pruneTree(node);
    }
}
