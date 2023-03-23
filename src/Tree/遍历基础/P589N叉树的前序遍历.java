package Tree.遍历基础;/*
    
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

public class P589N叉树的前序遍历 {
    // 100 + 55
    public List<Integer> preorder(Node root) {
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
        res.add(node.val);
        for (Node child:node.children){
            recursion(child,res);
        }
    }
    /*
      迭代BFS  41 + 66
       */
    public static List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        Deque<Node> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            // 顺序插法，根先进，左再进，右最后进，最后顺序就是根左右
            res.add( node.val);
            // 从右到左，进栈，出栈就是从左到右,也可以先反转children再从左到右入栈
            for (int i = node.children.size()-1; i >= 0; i--) {
                // 为空也会添加进去
                if ( node.children.get(i) != null) {
                    stack.push(node.children.get(i));
                }
            }
        }
        return res;
    }
}
