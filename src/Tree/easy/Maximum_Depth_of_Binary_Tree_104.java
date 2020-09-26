package Tree.easy;

import Construct.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/01 14:57
 * @Describe:
 */
public class Maximum_Depth_of_Binary_Tree_104 {
    public int maxDepth(TreeNode root) {
        //定义一个键值对都有意义的栈,poll()是删除并弹出第一个元素
        Queue<Pair<TreeNode,Integer>> queue=new LinkedList<>();
        //将首节点加入
        if (root!=null){
            queue.add(new Pair(root,1));
        }
        int depth=0;
        int currentDepth=0;
        while (!queue.isEmpty()){
            Pair<TreeNode,Integer> current=queue.poll();
            currentDepth=current.getValue();
            root=current.getKey();
            if (root!=null){
                depth=Math.max(depth,currentDepth);
                queue.add(new Pair<>(root.left,currentDepth+1));
                queue.add(new Pair<>(root.right,currentDepth+1));
            }
        }
        return depth;
    }
}

//        return root==null?0:Math.max(maxDepth(root.left),maxDepth(root.right))+1;
