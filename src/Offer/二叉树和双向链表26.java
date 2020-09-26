package Offer;

import Construct.TreeNode;

import java.util.Stack;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/04 21:06
 * @Describe: 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *  用一个指针记住前面结点，然后与下一个结点进行左右连接
 */
public class 二叉树和双向链表26 {
    public TreeNode Convert(TreeNode root) {
        if (root==null) return null;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode p = root;
        TreeNode pre=null;
        boolean isFirst=true;
        while (p!=null || !stack.isEmpty()){
            while (p!=null){
                //将节点依次放入栈，遍历到最左边叶子结点
                stack.push(p);
                p=p.left;
            }
            p=stack.pop();
            //将最左边的叶子结点作为双链表的头结点，此处借助一个标志位
            if (isFirst){
                root=p;
                pre=root;
                isFirst=false;
            }else {
                //节点左右连接
                pre.right = p;
                p.left = pre;
                pre = p;
            }
            //这里的p=p.right要放在循环外面就是第一种情况时，刚找到最左头结点如果最左结点还有右结点这行代码就有效了。
            p=p.right;
        }
        return root;
    }
}
