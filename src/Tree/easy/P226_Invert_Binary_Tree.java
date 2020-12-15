package Tree.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/5
  *@Describe:
 */

import Construct.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

public class P226_Invert_Binary_Tree {
    /*
    DFS 递归 100 + 5
     */
    public TreeNode invertTreeDFS(TreeNode root) {
        if (root==null) return null;
        TreeNode left=invertTreeDFS(root.left);
        TreeNode right=invertTreeDFS(root.right);
        root.left=right;
        root.right=left;
        return root;
    }


    /*
    BFS迭代 逐层交换  100 + 5
     */
    public static TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queueNode = new LinkedList<>();
        TreeNode node = root;
        if (root != null) {
            queueNode.offer(node);
        }
        while (!queueNode.isEmpty()) {
            int size = queueNode.size();
            // 遍历一层
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queueNode.poll();
                if (curNode.left != null) {
                    queueNode.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queueNode.offer(curNode.right);
                }
                // 交换层之间的子节点
                invert(curNode);
            }
        }
        return root;
    }
    public static void invert(TreeNode node){
        TreeNode left=node.left;
        TreeNode right=node.right;
        node.left=right;
        node.right=left;
    }


    public static void main(String[] args) {
        // 不填默认为 null
        TreeNode node=new TreeNode(1);
        node.left=new TreeNode(5);
        node.right=new TreeNode(4);
        node.left.left=new TreeNode(2);
        node.left.left.left=new TreeNode(3);
        System.out.println(invertTree(node));
    }

}
