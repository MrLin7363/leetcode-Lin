package Tree.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/6
  *@Describe:
 */

import Construct.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P257_Binary_Tree_Paths {
    /*
    dfs 99 + 19
     */
    List<String> ans=new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root!=null) {
            dfs(root,"");
        }
        return ans;
    }
    // 参数用String，不然用StringBuilder 是引用类型要回溯复原麻烦
    public void dfs(TreeNode node, String path) {
        if (node == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(path);
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            ans.add(sb.toString());
        }
        sb.append("->");
        dfs(node.left, sb.toString());
        sb.append("->");
        dfs(node.right, sb.toString());
    }

    /*
    BFS 75 + 12
     */
    public List<String> binaryTreePathsBFS(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        if (root == null) {
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<String> pathQueue = new LinkedList<String>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if (node.left == null && node.right == null) {
                paths.add(path);
            } else {
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    pathQueue.offer(new StringBuffer(path).append("->").append(node.left.val).toString());
                }

                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    pathQueue.offer(new StringBuffer(path).append("->").append(node.right.val).toString());
                }
            }
        }
        return paths;
    }
}
