package A1000PLAN.树.广度;

import Construct.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/18
 **/
public class P623在二叉树中增加一行 {
    /*
    DFS
     */
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (root == null) {
            return null;
        }
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        if (depth == 2) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
        } else {
            root.left = addOneRow(root.left, val, depth - 1);
            root.right = addOneRow(root.right, val, depth - 1);
        }
        return root;
    }

    /*
    BFS-层次遍历
     */
    public TreeNode addOneRow2(TreeNode root, int val, int depth) {
        // 特殊情况depth==1
        if (depth == 1) {
            // TreeNode node = new TreeNode(val);
            // node.left = root != null ? root : null;
            return new TreeNode(val, root, null);
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int curDepth = 1;
        while (!queue.isEmpty() && curDepth != depth - 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            curDepth++;
        }

        // 要插入的上一层的所有结点
        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            // 构造方法简化
            parent.left = new TreeNode(val, parent.left, null);
            parent.right = new TreeNode(val, null, parent.right);
            // TreeNode leftNode = new TreeNode(val);
            // TreeNode rightNode = new TreeNode(val);
            // if (parent.left != null) {
            //     leftNode.left = parent.left;
            // }
            // if (parent.right != null) {
            //     rightNode.right = parent.right;
            // }
            // parent.left = leftNode;
            // parent.right = rightNode;
        }

        return root;
    }
}
