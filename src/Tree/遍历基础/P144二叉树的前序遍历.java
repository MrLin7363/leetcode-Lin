package Tree.遍历基础;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/5
  *@Describe:
  递归的话参考N叉树遍历
  根左右
 */

import Construct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P144二叉树的前序遍历 {

    /**
     * 迭代DFS   100 +55
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            // 遍历到最左结点
            while (node != null) {
                res.add(node.val);
                stack.offerLast(node);
                node = node.left;
            }
            // 遍历到左叶子结点的左空结点,取出左叶子结点
            node = stack.pollLast();
            // 遍历右结点
            node = node.right;
        }
        return res;
    }

    // 迭代方法BFS  100 + 79    - > 100 +95  pop push 换 offerLast pollLast比
    public static List<Integer> postorder2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            // 顺序插法，根先进结果队列，右先进栈，左再进栈，最后顺序就是根左右
            res.add(node.val);
            if (node.right != null) {
                stack.offerLast(node.right);
            }
            if (node.left != null) {
                stack.offerLast(node.left);
            }
        }
        return res;
    }

    /*
    Mirror 算法  倒序输出当前结点左子结点到前驱路径的所有结点
    100 + 55
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;
        while (root != null) {
            // 左结点不为空
            if (root.left != null) {
                // 寻找左子树中，中序遍历root的先驱结点
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // 先驱结点右子结点为空
                if (predecessor.right == null) {
                    res.add(root.val);
                    predecessor.right = root;
                    root = root.left;
                }
                // 先驱结点右子结点不为空，说明遍历到做叶子结点
                else {
                    root = root.right;
                    //                    predecessor.right=null; 可以不断开链接，少一步操作
                }
            }
            // 左节点为空
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = null;
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(4);
        System.out.println(postorder2(node));
    }
}
