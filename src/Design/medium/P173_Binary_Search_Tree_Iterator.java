package Design.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/17
  *@Describe: 二叉树迭代器左根右，中序
 */

import Construct.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P173_Binary_Search_Tree_Iterator {
    /*
    先全部转化为链表，再遍历 7 + 43
     */
    class BSTIterator {
        List<Integer> tree;
        int pos=0;
        public BSTIterator(TreeNode root) {
            tree=inorderTraversalDFS(root);
        }

        public int next() {
            return tree.get(pos++);
        }

        public boolean hasNext() {
            return pos<tree.size();
        }
        // 迭代方法BFS 100 + 14
        public List<Integer> inorderTraversalDFS(TreeNode root) {
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
    /*
    受控递归： 初始化，不用一下子都转化为链表，过程中转。转化用BFS算法
    8 + 99
     */
    class BSTIterator2 {
        Deque<TreeNode> stack;
        public BSTIterator2(TreeNode root) {
            stack = new LinkedList<>();
            leftmostInorder(root);
        }

        public int next() {
            TreeNode node=stack.pop();
            // 添加右结点
            if (node.right!=null){
                leftmostInorder(node.right);
            }
            return node.val; // 最左边的结点
        }

        public boolean hasNext() {
            return stack.size()>0;
        }
        // 添加左结点
        private void leftmostInorder(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }
    }
}
