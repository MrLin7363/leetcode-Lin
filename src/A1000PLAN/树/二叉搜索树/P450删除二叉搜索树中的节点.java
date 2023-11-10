package A1000PLAN.树.二叉搜索树;

import Construct.TreeNode;

/**
 *desc:
 *@author   
 *@since 2023/11/6
 **/
public class P450删除二叉搜索树中的节点 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        new P450删除二叉搜索树中的节点().deleteNode(root, 7);
    }

    /*
    思路：1.找到右子树的最小节点，然后把左子树放它左边  2.找到左子树最大节点，再把右子树接到它右边
    递归 1.
    https://leetcode.cn/problems/delete-node-in-a-bst/solutions/1529700/shan-chu-er-cha-sou-suo-shu-zhong-de-jie-n6vo/
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            // = 是删除操作
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            // 找右子树最大结点
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            // 需要删除这个最大结点，直接连接有环
            root.right = deleteNode(root.right, successor.val);
            successor.left = root.left;
            successor.right = root.right;
            return successor;
        }
        return root;
    }
}
