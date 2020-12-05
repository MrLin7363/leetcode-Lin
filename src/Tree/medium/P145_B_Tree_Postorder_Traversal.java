package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/5
  *@Describe:
 */

import Construct.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P145_B_Tree_Postorder_Traversal {

    /*
    Mirror 算法 注意要一个点记住根节点  100 + 14
     */
    public static List<Integer> postorderTraversalM(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;
        TreeNode node=root;
        while (node != null) {
            // 左结点不为空
            if (node.left != null) {
                // 寻找左子树中，中序遍历root的先驱结点
                predecessor = node.left;
                while (predecessor.right != null && predecessor.right != node) {
                    predecessor = predecessor.right;
                }
                // 先驱结点右子结点为空
                if (predecessor.right == null) {
                    predecessor.right = node;
                    node = node.left;
                }
                // 先驱结点右子结点不为空，说明遍历到有右连接叶子结点
                //倒序输出从当前节点的左子节点到该前驱节点这条路径上的所有节点
                else {
                    //注意这里必须断开链接，否则addPath会把右边的结点重复添加
                    predecessor.right = null;
                    addPath(res, node.left);
                    node = node.right;
                }
            }
            // 左节点为空
            else {
                node = node.right;
            }
        }
        // 最后一个结点还要在输入一次，注意是root根节点
        addPath(res, root);
        return res;
    }
    /*
    倒序输入，将倒序的结果存入结果集，此题和饭庄链表不一样
     */
    public static void addPath(List<Integer> res, TreeNode node) {
        int count=0;
        while (node!=null){
            count++;
            res.add(node.val);
            node=node.right;
        }
        // 双指针交换元素，倒序
        int left=res.size()-count,right=res.size()-1;
        while (left<right){
            int temp = res.get(left);
            res.set(left, res.get(right));
            res.set(right, temp);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        TreeNode node=new TreeNode(1);
        node.left=null;
        node.right=new TreeNode(2);
        node.right.left=new TreeNode(3);
        System.out.println(postorderTraversalM(node));
    }

    // 迭代DFS   100 + 72
    // 前驱结点是为了判断当前的根节点是否可以加入结果队列，如果根结点的右结点=前驱结点，说明已经访问过，那么可以
    public List<Integer> postorderTraversalDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev=null;
        while (!stack.isEmpty() || root != null){
            // 遍历到最左结点
            while (root!=null){
                stack.offerLast(root);
                root=root.left;
            }
            // 遍历到叶子结点的左空结点,取出左叶子结点
            root=stack.pollLast();
            // 说明左右结点已经访问过或者没有左右结点
            if (root.right==null || root.right==prev){
                res.add(root.val);
                prev=root; // prev还是原先root的值，结点不变，只是这个指向引用变
                root=null; // 置为null，下一循环，取出栈值
            }else{ // 存在右结点或者右结点还没被访问过
                stack.offerLast(root);
                root=root.right;
            }
        }
        return res;
    }


    // 迭代方法BFS    100 +72  pop push 换 offerLast pollLast比
    public  List<Integer> postorderTraversalBFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            // 头插法，根先进，右再进，左最后进，最后顺序就是左右根
            res.add(0,node.val);
            if (node.left != null) {
                stack.offerLast(node.left);
            }
            if (node.right != null) {
                stack.offerLast(node.right);
            }
        }
        return res;
    }
}
