package A1000PLAN.树.构建;

import Construct.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/15
 **/
public class P654最大二叉树 {
    /*
    1. 推荐比较好理解 On^2：分治递归建树 和中前后序无关
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int index = findMax(nums, left, right);
        TreeNode root = new TreeNode(nums[index]);
        root.left = build(nums, left, index - 1);
        root.right = build(nums, index + 1, right);
        return root;
    }

    private int findMax(int[] nums, int left, int right) {
        int index = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] > nums[index]) {
                index = i;
                nums[index] = nums[i];
            }
        }
        return index;
    }

    /*
    2.单调栈，On  不好理解 记不住就算了
    https://leetcode.cn/problems/maximum-binary-tree/solutions/1762400/zhua-wa-mou-si-by-muse-77-myd7/
     */
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            TreeNode node = new TreeNode(nums[i]);
            while (!deque.isEmpty()) {
                TreeNode topNode = deque.peekLast();
                // 小，进栈
                if (node.val < topNode.val) {
                    topNode.right = node;
                    deque.offerLast(node);
                    break;
                } else {
                    node.left = topNode;
                    deque.removeLast();
                }
            }
            if (deque.isEmpty()) {
                deque.offerLast(node);
            }
        }
        return deque.peekFirst();
    }

    public static void main(String[] args) {
        new P654最大二叉树().constructMaximumBinaryTree(new int[] {3, 2, 1, 6, 0, 5});
    }
}
