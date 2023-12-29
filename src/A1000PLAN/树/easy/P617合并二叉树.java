package A1000PLAN.树.easy;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/13
 **/
public class P617合并二叉树 {
    /*
    推荐
    */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        // 都不null
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }

    /*
    递归 自己写的-不好看
     */
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        TreeNode root = new TreeNode();
        if (root1 != null && root2 == null) {
            root.val = root1.val;
        } else if (root2 != null && root1 == null) {
            root.val = root2.val;
        } else {
            root.val = root1.val + root2.val;
        }
        root.left = mergeTrees2(root1 == null ? null : root1.left, root2 == null ? null : root2.left);
        root.right = mergeTrees2(root1 == null ? null : root1.right, root2 == null ? null : root2.right);
        return root;
    }
}
