package Offer;

import Construct.TreeNode;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/07 15:00
 * @Describe: 递归调用
 *  1、树为空，返回true
 *  2、其左右孩子树深度差若超过1则返回false
 *  3、递归调用遍历每一个结点
 */
public class 判断平衡二叉树39 {

        public int TreeDepth(TreeNode root){

        return root==null?0:1+Math.max(TreeDepth(root.left),TreeDepth(root.right));
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if(root==null) return false;
        if(Math.abs(TreeDepth(root.left)-TreeDepth(root.right))>1){
            return false;
        }
        return IsBalanced_Solution(root.left)&&IsBalanced_Solution(root.right);
    }
}
