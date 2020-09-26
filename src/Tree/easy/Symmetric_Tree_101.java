package Tree.easy;

import Construct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/01 14:02
 * @Describe:
 */
public class Symmetric_Tree_101 {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode t1=queue.poll();
            TreeNode t2=queue.poll();
            //如果都为空，对的，继续扫描
            if (t1==null&&t2==null) continue;
            if (t1==null || t2==null || t1.val!=t2.val) return false;
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }
}

   /* public boolean isMirror(TreeNode t1,TreeNode t2){
        //如果都为空
        if (t1==null && t2==null) return true;
        //单单一个空或者值不相等
        if (t1==null || t2==null || t1.val!=t2.val) return false;
        return isMirror(t1.left,t2.right) && isMirror(t1.right,t2.left);
    }
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }*/






    /*错误的尝试代码
    public boolean check(TreeNode l,TreeNode r){
        if (l.left==null && r.right==null) return true;
        if (r.left==null && l.right==null) return true;
        if (l.left.val==r.right.val) return true;
        if (r.left.val==l.right.val) return true;
        return false;
    }
    public boolean isSymmetric(TreeNode root) {
        //根为空
        if (root==null) return false;
        //只有一个节点时
        if (root.left==null && root.right==null) return true;
        if (!check(root.left,root.right)) return false;
        //定义队列
        ArrayDeque<TreeNode> deque=new ArrayDeque<TreeNode>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            root=deque.removeFirst();
            if (!check(root.left,root.right)) return false;
            if (root.left!=null){
                deque.addLast(root.left);
            }
            if (root.right!=null){
                deque.addLast(root.right);
            }
        }
        return true;
    }*/
