package A1000PLAN.树.遍历基础;

import Construct.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/11
 **/
public class P114二叉树展开为链表 {
    // 2、前序迭代遍历 - 推荐多写迭代
    public void flatten(TreeNode root) {
        List<TreeNode> orders = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                orders.add(node);
                stack.offerLast(node);
                node = node.left;
            }
            node = stack.pollLast();
            node = node.right;
        }

        TreeNode prev;
        for (int i = 1; i < orders.size(); i++) {
            prev = orders.get(i - 1);
            prev.left = null;
            prev.right = orders.get(i);
        }
    }

    // 1、前序递归遍历
    public void flatten2(TreeNode root) {
        List<TreeNode> orders = new ArrayList<>();
        preOrder(root, orders);

        TreeNode prev;
        for (int i = 1; i < orders.size(); i++) {
            prev = orders.get(i - 1);
            prev.left = null;
            prev.right = orders.get(i);
        }
    }

    private void preOrder(TreeNode root, List<TreeNode> res) {
        if (root == null) {
            return;
        }
        res.add(root);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }
}
