package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/5
  *@Describe: 二叉树无顺序
  题目限定每个结点的值都是唯一的
 */


import Construct.TreeNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P236LCA_OF_BT {
    /*
    DFS哈希存储父节点，两次寻找父节点，第二次遇见就是LCA
    19 + 90
     */
    Map<TreeNode,TreeNode> parent=new HashMap<>();
    Set<TreeNode> visited=new HashSet<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p!=null){
            visited.add(p);
            p=parent.get(p);
        }
        while (q!=null){
            if (visited.contains(q)){
                return q;
            }
            q=parent.get(q);
        }
        return null;
    }
    // 遍历记住每个结点的父节点
    public void dfs(TreeNode root){
        if (root.left!=null){
            parent.put(root.left,root);
            dfs(root.left);
        }
        if (root.right!=null){
            parent.put(root.right,root);
            dfs(root.right);
        }
    }

    /*
    DFS 100 + 72
     */
    private TreeNode ans;
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs2(root,p,q);
        return this.ans;
    }
    public boolean dfs2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs2(root.left, p, q);
        boolean rson = dfs2(root.right, p, q);
        // 第二个条件是祖先是其中一个结点的情况
        if ((lson && rson) || ((root == p || root == q) && (lson || rson))) {
            ans=root;
        }
        return lson || rson || root==p || root==q;
    }

}
