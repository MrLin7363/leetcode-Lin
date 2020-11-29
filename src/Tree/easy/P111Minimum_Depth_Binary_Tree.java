package Tree.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/29
  *@Describe: DFS BFS  最小深度
  注意第一个根节点不可以作为叶子节点
  [2,null,3,null,4,null,5,null,6] 如这种，链式树形结构 深度为6
  就是要考虑叶子节点的问题
 */

import Construct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class P111Minimum_Depth_Binary_Tree {
    //BFS 100+79
    public int minDepth3(TreeNode root) {
        if (root==null) return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int ans=1;
        while (!queue.isEmpty()){
            int size=queue.size();
            while (size!=0){
                TreeNode cur=queue.poll();
                // 遍历到无叶子节点的层
                if (cur.left==null && cur.right==null){
                    return ans;
                }
                if (cur.left!=null) {
                    queue.offer(cur.left);
                }
                if (cur.right!=null){
                    queue.offer(cur.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }


        // DFS 54+ 37
    public int minDepth2(TreeNode root) {
        if (root==null) return 0;
        if (root.left==null) return minDepth(root.right)+1;
        if (root.right==null) return minDepth(root.left)+1;
        else return Math.min(minDepth(root.left),minDepth(root.right))+1;
    }

        // DFS 40 + 52
    public int minDepth(TreeNode root) {
        if (root==null) return 0;
        if (root.left==null && root.right==null) return 1;
        int minDepth=Integer.MAX_VALUE;
        if (root.left!=null){
            minDepth=Math.min(minDepth(root.left),minDepth);
        }
        if (root.right!=null){
            minDepth=Math.min(minDepth(root.right),minDepth);
        }
        return minDepth+1;
    }
}
