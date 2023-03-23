package Tree.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/4
  *@Describe: 每层的平均值
 */

import Construct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P637二叉树的层平均值 {

    /*
    DFS 使用层数记录每层信息 每层个数counts[i] 每层的和sums[i]  每层平均值 counts[i]/sums[i]
    100 + 16
     */
    public static List<Double> averageOfLevels2(TreeNode root) {
        List<Integer> counts = new ArrayList<>();
        List<Double> sums = new ArrayList<>();
        dfs(root, 0, counts, sums);
        List<Double> res = new LinkedList<>();
        // 汇总结果
        for (int i = 0; i < sums.size(); i++) {
            res.add(sums.get(i) / counts.get(i));
        }
        return res;
    }

    public static void dfs(TreeNode node, int level, List<Integer> counts, List<Double> sums) {
        if (node == null)
            return;
        // 进入该层的第二个点
        if (level < counts.size()) {
            sums.set(level, sums.get(level) + node.val);// 该层和
            counts.set(level, counts.get(level) + 1); // 层个数加1
        } else { // 新进入一层
            sums.add(1.0 * node.val); // double + int 转为 double
            counts.add(1);
        }
        dfs(node.left, level + 1, counts, sums);
        dfs(node.right, level + 1, counts, sums);
    }

    /*
    BFS  76 + 84
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new LinkedList<>();
        Queue<TreeNode> queNode = new LinkedList<>();
        queNode.offer(root);
        while (!queNode.isEmpty()) {
            double sum = 0;
            int size = queNode.size(); // 每层长度
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queNode.poll();
                sum += curNode.val;
                if (curNode.left != null) {
                    queNode.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queNode.offer(curNode.right);
                }
            }
            res.add(sum / size); // ifinit of NaN  一般是除法溢出
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right = new TreeNode(3);
        System.out.println(averageOfLevels2(node));
    }
}
