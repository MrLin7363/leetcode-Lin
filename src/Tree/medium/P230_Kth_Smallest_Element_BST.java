package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/7
  *@Describe:
 */

import Construct.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P230_Kth_Smallest_Element_BST {
    /*
    100 + 23 递归DFS
     */
    public int kthSmallest2(TreeNode root, int k) {
        List<Integer> res= inorder(root,new ArrayList<>());
        return res.get(k-1);
    }
    public List<Integer> inorder(TreeNode node,List<Integer> res){
        if (node==null) return null;
        inorder(node.left,res);
        res.add(node.val);
        inorder(node.right,res);
        return res;
    }
        // 迭代DFS 左子树最小，中序遍历倒数输出 第 k 小的数
    // 100 + 82
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root!=null){
            while (root!=null){
                stack.offerLast(root);
                root=root.left;
            }
            root=stack.pollLast();
            k--;
            if (0==k){
                return root.val;
            }
            root=root.right;
        }
        return root.val;
    }


}
