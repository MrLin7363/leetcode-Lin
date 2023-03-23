package Tree.遍历基础;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/4
  *@Describe: 多叉树层次遍历
 */

import Construct.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P429N叉树的层序遍历 {
    // DFS  90 + 5
    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> res=new LinkedList<>();
        if (root!=null){
            dfs(root,0,res);
        }
        return res;
    }
    public void dfs(Node node,int level, List<List<Integer>> res ) {
        // level 0开始 ,res.size()从1开始
        if (level >= res.size()){
            // 添加一层
            res.add(new ArrayList<>());
        }
        // 添加结点值
        res.get(level).add(node.val);
        if (node.children!=null){
            for (Node child : node.children) {
                dfs(child,level+1,res);
            }
        }
    }

    // BFS 85 + 86
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queueNode = new LinkedList<>();
        queueNode.offer(root);
        while (!queueNode.isEmpty()) {
            int size = queueNode.size();
            List<Integer> levelNode = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node curNode = queueNode.poll();
                levelNode.add(curNode.val);
                for (Node node : curNode.children) {
                    queueNode.offer(node);
                }
            }
            res.add(levelNode);
        }
        return res;
    }
}
