package A1000PLAN.树.二叉搜索树.easy;

import Construct.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 *desc:
 *@author   
 *@since 2023/11/6
 **/
public class P653两数之和IV输入二叉搜索树 {

    private Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (!set.contains(root.val)) {
            set.add(k - root.val);
        } else {
            return true;
        }
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
