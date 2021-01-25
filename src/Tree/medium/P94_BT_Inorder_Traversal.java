package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/5
  *@Describe:
  中序遍历没有BFS，用DFS，  BFS一般用于多叉树
  左根右
 */

import Construct.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P94_BT_Inorder_Traversal {
    /*
  Morris 解法  100+ 99
   */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        // root中序遍历的前驱结点
        TreeNode predecessor = null;
        while (root!=null){
            // 有左孩子，找predecessor
            if (root.left!=null){
                // 找 predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor=root.left;
                // root.right != root 是防止已经连接后找到root结点
                while (predecessor.right!=null && predecessor.right != root){
                    predecessor=predecessor.right;
                }
                // 让 predecessor 的右指针指向 root，继续遍历左子树，此处建立连接
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，对应根节点
                else{
                    res.add(root.val);
                    // 可以断开链接也可以不断开，断开链接空间复杂度更高
//                    predecessor.right=null;
                    root=root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子  对应左叶子结点
            else{
                res.add(root.val);
                root=root.right;
            }
        }
        return res;
    }

    // 迭代方法DFS 100 + 14
    public  List<Integer> inorderTraversalDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root!=null) {
           // 遍历到最左结点
            while (root!=null){
                stack.offerLast(root);
                root=root.left;
            }
            root=stack.pollLast();
            res.add(root.val);
            root=root.right;
        }
        return res;
    }

}
