package Tree.easy;

import Construct.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/01 14:57
 * @Describe:
 */
public class P104二叉树的最大深度 {
    // DFS 最好 100+95
    //        return root==null?0:Math.max(maxDepth(root.left),maxDepth(root.right))+1;

    /*
    BFS 12+ 99
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 依次将该层所有的节点入队列
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // 将该层队列的所有节点都添加进队列，size已经固定好了
                size--;
            }
            ans++;
        }
        return ans;
    }

    // BFS 可以不考虑这种
    public int maxDepth2(TreeNode root) {
        //定义一个键值对都有意义的栈,poll()是删除并弹出第一个元素
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        //将首节点加入
        if (root != null) {
            queue.add(new Pair(root, 1));
        }
        int depth = 0;
        int currentDepth = 0;
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> current = queue.poll();
            currentDepth = current.getValue();
            root = current.getKey();
            if (root != null) {
                depth = Math.max(depth, currentDepth);
                queue.add(new Pair<>(root.left, currentDepth + 1));
                queue.add(new Pair<>(root.right, currentDepth + 1));
            }
        }
        return depth;
    }
}

