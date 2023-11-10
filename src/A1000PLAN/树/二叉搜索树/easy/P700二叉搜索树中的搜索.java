package A1000PLAN.树.二叉搜索树.easy;

import Construct.TreeNode;

/**
 *desc:
 *@author   
 *@since 2023/11/6
 **/
public class P700二叉搜索树中的搜索 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }
}
