package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/30
  *@Describe: 找出父节点到子节点的路径个数
  前缀和
 */

import Construct.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

public class P437Path_Sum_III {
    int res=0;
    Deque<Integer> path = new LinkedList<Integer>();

    public int pathSum(TreeNode root, int sum) {
        dfs(root, sum);
        return res;
    }
    public void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        sum-=root.val;
        if ( sum == 0) {
            res++;
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
        path.pollLast();
    }
}
