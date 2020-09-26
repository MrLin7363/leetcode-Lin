package Tree.easy;

import Construct.TreeNode;

import java.util.ArrayDeque;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/30 20:22
 * @Describe:
 */
public class Same_Tree_100 {
    public boolean check(TreeNode p, TreeNode q){
        //如果都为空
        if(p==null && q==null) return true;
        //如果有一个空另一个不空
        if(p==null || q==null) return false;
        //如果值不等
        if(p.val!=q.val) return false;
        return true;
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //要先判断pq是否为空，否则传入可能空指针异常
        if (p==null && q==null) return true;
        //！check()就是返回false，这里判断头结点
        if (!check(p,q)) return false;
        //定义双向队列
        ArrayDeque<TreeNode> deqP=new ArrayDeque<TreeNode>();
        ArrayDeque<TreeNode> deqQ=new ArrayDeque<TreeNode>();
        deqP.addLast(p);
        deqQ.addLast(q);
        //先添加进去才能用isEmpty方法
        while (!deqP.isEmpty()){
            //这里移除出来
            p=deqP.removeFirst();
            q=deqQ.removeFirst();
            if (!check(p,q)) return false;
            //判断都不为空的情况
            if (!check(p.left,q.left)) return false;
            if (p.left!=null){
                deqP.addLast(p.left);
                deqQ.addLast(q.left);
            }
            if (!check(p.right,q.right)) return false;
            if (p.right!=null){
                deqP.addLast(p.right);
                deqQ.addLast(q.right);
            }
        }
        return true;
    }
}
  /*  public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p==null && q==null)
            return true;
        else if (p.val!=q.val){
            return false;
        }
            isSameTree(p.left,p.left);
            isSameTree(p.right,p.right);
        return true;
    }*/
