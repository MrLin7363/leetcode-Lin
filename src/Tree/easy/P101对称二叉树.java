package Tree.easy;

import Construct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/01 14:02
 * @Describe:
 */
public class P101对称二叉树 {
    // 迭代
    public boolean isSymmetric(TreeNode root) {
        // linkedList.add 可以添加为null的元素
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            //如果都为空，对的，继续扫描
            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null || t1.val != t2.val) {
                return false;
            }
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }

    public static void main(String[] args) {
        new P101对称二叉树().isSymmetric(new TreeNode(2));
    }

    // DFS
    public boolean isSymmetric2(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {
        // 如果都为空
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null || node1.val != node2.val) { // 单单一个空或者值不相等
            return false;
        } else {
            return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
        }
    }

}