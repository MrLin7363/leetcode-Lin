package A1000PLAN.树.easy;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/13
 **/
public class P572另一棵树的子树 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        // 以每个结点为起点去检查是否是同一颗树
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
    // dfs P100
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
