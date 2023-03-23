package Tree.路径问题;
    
/**
  *@Author JunLin
  *@Date 2020/11/29
  *@Describe:  是否有 和为target 的路径
 */
import Construct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class P112路径总和 {

    /*
    递归 100+ 14
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        // 叶子节点
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        // 有一个true返回true
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /*
    BFS 两个队列，增加移除统一进行，就对应起来了  12+64
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null)
            return false;
        Queue<TreeNode> queNode = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode cur = queNode.poll();
            int temp = queVal.poll();
            // 叶子节点
            if (cur.left == null && cur.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (cur.left != null) {
                queNode.offer(cur.left);
                queVal.offer(cur.left.val + temp);
            }
            if (cur.right != null) {
                queNode.offer(cur.right);
                queVal.offer(cur.right.val + temp);
            }
        }
        return false;
    }
}
