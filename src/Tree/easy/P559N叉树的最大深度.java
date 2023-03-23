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

public class P559N叉树的最大深度 {
    // 栈迭代 5 +62
    public int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Node node = queue.poll();
                List<Node> children = node.children;
                for (Node child : children) {
                    queue.offer(child);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    // 递归
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int maxChildDepth = 0;
        List<Node> children = root.children;
        for (Node child : children) {
            int childDepth = maxDepth(child);
            maxChildDepth = Math.max(maxChildDepth, childDepth);
        }
        return maxChildDepth + 1;
    }

    /*
     自底向上递归 33+77
     */
    public int maxDepth3(Node root) {
        if (root == null) {
            return 0;
        } else if (root.children.isEmpty()) {
            return 1;
        } else {
            LinkedList<Integer> heights = new LinkedList<>();
            for (Node item : root.children) {
                heights.add(maxDepth3(item));
            }
            return Collections.max(heights) + 1;
        }
    }
}
