package A1000PLAN.树.遍历基础.层次遍历;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/19
 **/
public class P116填充每个节点的下一个右侧节点指针 {
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

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                // 非末尾节点,next为queue里面的下一个节点
                if (i < size - 1) {
                    cur.next = queue.peek();
                }

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

    // 层次遍历 - 自己想的，不推荐可以优化为上面的
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();

            // 通过prev记录同一层的上一个节点，如果上一个节点不为空，则当前节点是上一个节点的next节点
            Node prev = null;
            // 遍历每一层
            while (size > 0) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    Node left = cur.left;
                    queue.add(left);
                    if (prev == null) {
                        prev = left;
                    } else {
                        prev.next = left;
                        prev = left;
                    }
                }
                if (cur.right != null) {
                    Node right = cur.right;
                    queue.add(right);
                    if (prev == null) {
                        prev = right;
                    } else {
                        prev.next = right;
                        prev = right;
                    }
                }
                size--;
            }
        }
        return root;
    }
}
