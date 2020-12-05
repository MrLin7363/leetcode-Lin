package Tree.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/5
  *@Describe:
 */

import Construct.Node;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P590N_Tree_Postorder_Traversal {
    /*
    迭代BFS 26+ 12
     */
    public static List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        Deque<Node> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            // 头插法，根先进，右再进，左最后进，最后顺序就是左右根
            res.add(0, node.val);
            // 从左到右进栈，出栈就是从右到左
            for (Node child : node.children) {
                // 为空也会添加进去
                if (child != null) {
                    stack.push(child);
                }
            }
        }
        return res;
    }


    /*
    100 + 19
     */
    public List<Integer> postorder(Node root) {
        List<Integer> res=new LinkedList<>();
        if (root!=null){
            recursion(root,res);
        }
        return res;
    }
    public void recursion(Node node,List<Integer> res){
        if (node==null){
            return;
        }
        for (Node child:node.children){
            recursion(child,res);
        }
        res.add(node.val);
    }

}
