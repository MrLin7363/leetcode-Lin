package A1000PLAN.链表;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/19
 **/
public class P430扁平化多级双向链表 {
    static class Node {
        public int val;

        public Node prev;

        public Node next;

        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    /*
    将 node 与 node 的下一个节点 next 断开
    将 node 与 child 相连
    将 扁平化的链表的最后一个节点last 与 next 相连
    注意：扁平化后node的child成员置空
    */
    // dfs
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    private Node dfs(Node node) {
        if (node == null) {
            return null;
        }
        Node cur = node;
        Node last = cur;
        while (cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                Node lastChild = dfs(cur.child);

                // 1. node与node下一个节点断开
                next = cur.next;
                // cur.next = null; 可以不用，因为下面会连接新的

                // 2. node与child相连
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;

                // 3.last chile与node下一个节点连接
                if (next != null) {
                    lastChild.next = next;
                    next.prev = lastChild;
                }
                last = lastChild;
            } else {
                last = cur;
            }
            cur = next; // 如果child有一串，可以直接连接后跳到最后，避免=next继续一个个遍历
        }
        return last;
    }

    // 1,2,4,5,3
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node2.prev = node1;

        node2.next = node3;
        node3.prev = node2;

        node2.child = node4;

        Node node5 = new Node(5);
        node4.next = node5;
        node5.prev = node4;
        // Node node1 = new Node(1);
        // Node node2 = new Node(2);
        // Node node3 = new Node(3);
        // node1.next = node2;
        // node2.prev = node1;
        // node1.child = node3;
        Node flatten = new P430扁平化多级双向链表().flatten(node1);
        System.out.println(flatten);
    }
}
