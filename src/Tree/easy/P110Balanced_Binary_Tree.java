package Tree.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/29
  *@Describe:  判断平衡二叉树
 */

import Construct.TreeNode;

public class P110Balanced_Binary_Tree {
    /*
    自底向上 100+46
     */
    public boolean isBalanced2(TreeNode root) {
        return height2(root)>=0; // -1则是不平衡，0则是null树
    }
    private int height2(TreeNode root){
        if (root==null) return 0;
        int leftHeight=height2(root.left);
        int rightHeight=height2(root.right);
        // 如果有一个子树不平衡那么整颗树都不平衡，一直返回就可以
        if (leftHeight==-1 || rightHeight ==-1 || Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }else{
            return Math.max(leftHeight,rightHeight)+1;
        }
    }


    /*
    自顶向下 - 极力不推荐  44+ 74
    缺点是每一次的节点都要寻找一次最大的高度，而自顶，找最大高度耗时较多
     */
    public boolean isBalanced(TreeNode root) {
        if (root==null){
            return true;
        }else{
            return Math.abs(height(root.left)-height(root.right))<=1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }
    private int height(TreeNode root){
        return root==null?0:Math.max(height(root.left),height(root.right))+1;
    }
}
