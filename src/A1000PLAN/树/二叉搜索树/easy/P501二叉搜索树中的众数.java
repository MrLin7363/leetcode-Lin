package A1000PLAN.树.二叉搜索树.easy;

import Construct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:
 *@author   
 *@since 2023/11/6
 **/
public class P501二叉搜索树中的众数 {
    /*
    中序遍历不使用额外空间
     */
    private List<Integer> res;

    private int count;

    private int prev;

    private int maxCount;

    public int[] findMode(TreeNode root) {
        res = new ArrayList<>();
        inorder(root);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        count(node.val);
        inorder(node.right);
    }

    private void count(int value) {
        if (value == prev) {
            count++;
        } else {
            count = 1;
            prev = value;
        }
        if (count == maxCount) {
            res.add(value);
        }
        if (count > maxCount) {
            res.clear();
            res.add(value);
            maxCount = count;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        new P501二叉搜索树中的众数().findMode(root);
    }
}
