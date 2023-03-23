package Tree.路径问题;
    
/**
  *@Author JunLin
  *@Date 2020/12/1
  @Describe: 求根到叶子节点数字拼接之和
  结点0-9
*/

import Construct.TreeNode;
import java.util.*;

public class P129求根节点到叶节点数字之和 {

    /*
    双队列BFS  25 + 28
     */
    public int sumNumbers3(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queueNode.offer(root);
        queueSum.offer(root.val);
        int res = 0;
        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int curSum = queueSum.poll();
            if (node.left == null && node.right == null) {
                res += curSum;
            } else {
                if (node.left != null) {
                    queueNode.offer(node.left);
                    queueSum.offer(curSum * 10 + node.left.val);
                }
                if (node.right != null) {
                    queueNode.offer(node.right);
                    queueSum.offer(curSum * 10 + node.right.val);
                }
            }
        }
        return res;
    }
    /*
    自己写的递归 DFS
    到达叶子节点一  10+39
     */
    public static int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        return countPath(root, new StringBuilder(), 0);
    }

    public static int countPath(TreeNode node, StringBuilder sb, int sum) {
        if (node == null)
            return 0;
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            int res = Integer.parseInt(sb.toString());
            sb.deleteCharAt(sb.length() - 1); // 每次叶子结点，要去除当前叶子节点的值
            return res;
        }
        sum = countPath(node.left, sb, sum) + countPath(node.right, sb, sum);
        //        sum+=countPath(node.left,sb,sum);
        //        sum+=countPath(node.right,sb,sum); //如果这样不行，多重循环回溯的时候会重复加
        sb.deleteCharAt(sb.length() - 1); // 每一次双重回缩的时候，也就是左右子结点都遍历完的时候
        return sum;
    }

    public static void main(String[] args) {
        TreeNode node=new TreeNode(4);
        node.left=new TreeNode(9);
        node.left.left=new TreeNode(5);
        node.left.right=new TreeNode(1);
        node.right=new TreeNode(0);
//        node.right.left=null;
//        node.right.right=new TreeNode(6);
        System.out.println(sumNumbers(node));
    }

    /*
    官方DFS  100 + 77
     */
    public int sumNumbers2(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

}
