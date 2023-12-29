package A1000PLAN.树.遍历基础.中序遍历;

import Construct.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *desc:
 *@author   
 *@since 2023/11/2
 **/
public class P230二叉搜索树中第K小的元素 {
    /*
    左根右
     */
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode node = root;
        while (!queue.isEmpty() || node != null) {
            while (node != null) {
                queue.push(node);
                node = node.left;
            }
            node = queue.pop();
            // 找到元素
            k--;
            if (0 == k) {
                return node.val;
            }
            node = node.right;
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        new P230二叉搜索树中第K小的元素().kthSmallest(root, 2);
    }
}
