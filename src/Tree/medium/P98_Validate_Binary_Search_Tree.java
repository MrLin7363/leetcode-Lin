package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/9
  *@Describe:
 */

import Construct.TreeNode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class P98_Validate_Binary_Search_Tree {
    /*
    递归  BST   2,1,3,NULL,NULL,2,3  不是BST 如要右子树所有结点都大于根节点
    这个方法相当于递归 区间  100 + 36
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root,null,null);
    }
    public boolean isValid(TreeNode root,Integer lower,Integer high){
        if (root==null) return true;
        int val=root.val;
        // 遍历右子树
        if (lower!=null&&root.val<=lower){
            return false;
        }
        // 左子树
        if (high!=null&&root.val>=high){
                return false;
        }
        // 注意右边的high 不能为 null
        if (!isValid(root.right,val,high)){
            return false;
        }
        if (!isValid(root.left,lower,val)){
            return false;
        }
        return true;
    }
    /*
    中序遍历  30 + 49
     */
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack=new LinkedList<>();
        // 因为 Integer.MIN_VALUE <= 树结点的值  >=  Integer.MAX_VALUE
        double inorder=-Double.MAX_VALUE;
        while (!stack.isEmpty() || root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if (root.val<=inorder){
                return false;
            }
            inorder=root.val;
            root=root.right;
        }
        return true;
    }


}
