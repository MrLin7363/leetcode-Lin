package A1000PLAN.树.遍历基础;

import Construct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *desc:
 *@author   
 *@since 2023/11/2
 **/
public class P199二叉树的右视图 {
    /*
    BFS 队列,记录每一层的最右结点
     */
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        if (root != null) {
            nodeQueue.add(root);
        }
        List<Integer> res = new ArrayList<>();
        while (!nodeQueue.isEmpty()) {
            int n = nodeQueue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = nodeQueue.poll();
                if (node.left != null) {
                    nodeQueue.add(node.left);
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                }
                if (i == n - 1) {
                    // 当前层的最后一个结点
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    /*
    DFS 根右左
    */
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode node, int depth, List<Integer> res) {
        if (node == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) {
            // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(node.val);
        }
        depth++;
        dfs(node.right, depth, res);
        dfs(node.left, depth, res);
    }

    /*
    复杂抛弃
    BFS 队列,记录每一层的最右结点
     */
    public List<Integer> rightSideView3(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Map<Integer, TreeNode> level = new HashMap<>(); // 每层最右
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        Queue<Integer> depthQueue = new ArrayDeque<>();

        nodeQueue.add(root);
        depthQueue.add(0);
        // 这里的层次遍历也可以for循环那种
        while (!nodeQueue.isEmpty()) {
            // 每层出队是先出左边结点，put的是最右的结点
            TreeNode node = nodeQueue.poll();
            Integer depth = depthQueue.poll();

            level.put(depth, node);

            if (node.left != null) {
                nodeQueue.add(node.left);
                depthQueue.add(depth + 1);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                depthQueue.add(depth + 1);
            }
        }

        List<Integer> res = new ArrayList<>();
        // 头插法倒序
        level.forEach((depth, node) -> {
            res.add(node.val);
        });
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        new P199二叉树的右视图().rightSideView(root);
    }
}
