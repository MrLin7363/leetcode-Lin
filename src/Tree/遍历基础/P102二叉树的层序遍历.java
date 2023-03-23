package Tree.遍历基础;
    
/**
  *@Author JunLin
  *@Date 2020/12/4
  *@Describe: 层次遍历
 */
import Construct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P102二叉树的层序遍历 {
    /*
    DFS  55 + 79
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root != null) {
            dfs(root, 0, res);
        }
        return res;
    }

    public void dfs(TreeNode node, int level, List<List<Integer>> res) {
        // level 0开始 ,res.size()从1开始
        if (level >= res.size()) {
            // 添加一层
            res.add(new ArrayList<>());
        }
        // 添加结点值
        res.get(level).add(node.val);
        if (node.left != null) {
            dfs(node.left, level + 1, res);
        }
        if (node.right != null) {
            dfs(node.right, level + 1, res);
        }
    }

    /*
    BFS 100 +64   100 + 79
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queueNode = new LinkedList<>();
        queueNode.offer(root);
        while (!queueNode.isEmpty()) {
            int size = queueNode.size();
            List<Integer> levelNode = new LinkedList<>();
            while (size > 0) {
                // for (int i = 0; i < size; i++) {
                TreeNode curNode = queueNode.poll();
                levelNode.add(curNode.val);
                if (curNode.left != null) {
                    queueNode.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queueNode.offer(curNode.right);
                }
                size--;
            }
            res.add(levelNode);
        }
        return res;
    }
}
