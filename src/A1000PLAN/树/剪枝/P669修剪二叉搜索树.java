package A1000PLAN.树.剪枝;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/19
 **/
public class P669修剪二叉搜索树 {
    /*
    1.递归
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            // root及其左子树都不符合
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            // root及其右子树都不符合
            return trimBST(root.left, low, high);
        } else {
            // 在区间内
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    /*
    2.迭代
     */
    public TreeNode trimBST2(TreeNode root, int low, int high) {
        // 找到第一个区间内的root
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else if (root.val > high) {
                root = root.left;
            }
        }
        if (root == null) {
            return null;
        }

        // 对左子树进行裁剪
        for (TreeNode node = root; node.left != null; ) {
            // node.left及其左子树都不符合，裁剪node.left的右子树
            if (node.left.val < low) {
                node.left = node.left.right;
            } else {
                node = node.left;
            }
        }

        // 右子树裁剪同理
        for (TreeNode node = root; node.right != null; ) {
            if (node.right.val > high) {
                node.right = node.right.left;
            } else {
                node = node.right;
            }
        }
        return root;
    }
}
