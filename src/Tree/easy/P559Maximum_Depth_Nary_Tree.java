package Tree.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/29
  *@Describe:
 */

import Construct.Node;
import Construct.TreeNode;
import javafx.util.Pair;

import java.util.*;

public class P559Maximum_Depth_Nary_Tree {
    // 栈迭代 5 +62
    public int maxDepth2(Node root) {
        if (root==null) return 0;
//        Queue<Pair<Node,Integer>> queue=new LinkedList<>();  广度优先
        Deque<Pair<Node,Integer>> queue=new LinkedList<>(); // 深度优先，但是是先优先每一层的最右节点，也就是 右左根
        queue.add(new Pair(root,1));
        int depth=0;
        while (!queue.isEmpty()){
            Pair<Node,Integer> cur=queue.poll();
            root=cur.getKey();
            int cur_depth=cur.getValue();
            if (root!=null){
                depth=Math.max(depth,cur_depth);
                for(Node node:root.children){
                    queue.add(new Pair(node,depth+1));
                }
            }
        }
        return depth;
    }


    /*
     递归 33+77
     */
    public int maxDepth(Node root) {
        if (root==null){
            return 0;
        }else if (root.children.isEmpty()){
            return 1;
        }else{
            LinkedList<Integer> heights=new LinkedList<>();
            for (Node item:root.children){
                heights.add(maxDepth(item));
            }
            return Collections.max(heights)+1;
        }
    }
}
