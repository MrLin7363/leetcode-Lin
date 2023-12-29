package A1000PLAN.树.遍历基础.层次遍历;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * desc: 和116题目一样
 *
 * @author 
 * @since 2023/10/19
 **/
public class P117填充每个节点的下一个右侧节点指针II {
    class Node {
        public int val;

        public Node left;

        public Node right;

        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    // 层次遍历 - 推荐
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                // 非末尾节点,next为queue里面的下一个节点/ 或者用一个前节点记录
                if (prev != null) {
                    prev.next = cur;
                }
                prev = cur;


                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }
}
