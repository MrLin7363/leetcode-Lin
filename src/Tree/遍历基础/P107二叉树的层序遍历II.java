package Tree.遍历基础;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/4
  *@Describe: 自底向上，每层从左到右地输出遍历集合
 */

import Construct.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P107二叉树的层序遍历II {
    /*
    bfs82 + 15
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrder = new LinkedList<List<Integer>>();
        if (root == null) {
            return levelOrder;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            // 将指定的元素插入此列表中的指定位置（可选操作）。 将当前在该位置的元素（如果有）和任何后续元素右移（将其索引加一）。
            // 0为头元素，每次都插入到队列的头部 直接过程中反转的效果
            levelOrder.add(0, level);
        }
//            Collections.reverse(levelOrder);  //  最后再反转
        return levelOrder;
    }

    // dfs也行，最后再反转
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(4);
        System.out.println(new P107二叉树的层序遍历II().levelOrderBottom(node));
    }
}
