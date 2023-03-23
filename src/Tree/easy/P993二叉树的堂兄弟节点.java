package Tree.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/5
  *@Describe: 堂兄弟结点： 两个节点深度相同，但 父节点不同
 */

import Construct.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class P993二叉树的堂兄弟节点 {
    /*
    父节点和深度同时记录  26 + 5
     */
    static Map<Integer, Integer> depth = new HashMap<>();

    public static boolean isCousins2(TreeNode root, int x, int y) {
        dfs2(root, 0);
        return parent.get(x) != parent.get(y) && depth.get(x) == depth.get(y);
    }

    public static void dfs2(TreeNode root, int level) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            depth.put(root.val, level);
            dfs2(root.left, level + 1);
        } else if (root.right != null) {
            parent.put(root.right.val, root);
            depth.put(root.val, level);
            dfs2(root.right, level + 1);
        } else {
            // 最后一层结点也是要记录的
            depth.put(root.val, level);
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        System.out.println(isCousins2(node, 4, 3));
    }

    /*
    自己写的先记录父结点，再回溯看深度是否一致 100 + 62
     */
    static Map<Integer, TreeNode> parent = new HashMap<>();

    public boolean isCousins(TreeNode root, int x, int y) {
        // 记录路径
        dfs(root);
        // 先判断父结点
        if (parent.get(x) == parent.get(y)) {
            return false;
        }
        // 判断深度 ,能同时回溯到根节点说明深度一致
        TreeNode p = parent.get(x);
        TreeNode q = parent.get(y);
        while (p != null && q != null) {
            if (p == root && q == root) {
                return true;
            }
            p = parent.get(p.val);
            q = parent.get(q.val);
        }
        return false;
    }

    // 遍历记住每个结点的父节点
    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }
}
