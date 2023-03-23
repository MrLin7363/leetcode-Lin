package Tree.easy;

import Construct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/30 20:22
 * @Describe:
 */
public class P100相同的树 {
    // dfs
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

    // bfs
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            // 每次比较两个点
            p = queue.poll();
            q = queue.poll();
            if (p == null && q == null)
                continue;
            if (p == null || q == null || p.val != q.val)
                return false;
            queue.add(p.left); // LinkedList可以添加null元素,ArrayDeque不行
            queue.add(q.left);
            queue.add(p.right);
            queue.add(q.right);
        }
        return true;
    }

    public static void main(String[] args) {
        new P100相同的树().isSameTree2(new TreeNode(2),new TreeNode(2));
    }
}

