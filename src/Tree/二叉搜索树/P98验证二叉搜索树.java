package Tree.二叉搜索树;

import Construct.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P98验证二叉搜索树 {
    /**
     * 1.递归
     *     如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值；
     *     若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；它的左右子树也为二叉搜索树。
     *     100 + 91
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValid(TreeNode node, long lower, long high) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= high) {
            return false;
        }
        return isValid(node.left, lower, node.val) && isValid(node.right, node.val, high);
    }

    /**
     * 2.中序迭代 27 + 96
     */
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        // 因为 Integer.MIN_VALUE <= 树结点的值  >=  Integer.MAX_VALUE
        // Double.MIN_VALUE是正数，-Double.MAX_VALUE是double类型的最小数  可以直接用Long.MIN_VALUE
        double inorder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    /**
     * 3.常规中序递归后判断数组  19 + 80
     */

    // 左根右中序数组是升序的
    public boolean isValidBST3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        for (int i = 1; i < res.size(); i++) {
            if (res.get(i) <= res.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }
}
