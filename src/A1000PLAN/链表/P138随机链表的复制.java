package A1000PLAN.链表;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/19
 **/
public class P138随机链表的复制 {
    class Node {
        int val;

        Node next;

        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 1.迭代-哈希：记录访问过的节点 - 推荐
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node oldNode = head;
        // 复制
        Node newNode = new Node(head.val);
        // 标志访问过
        visitedHash.put(oldNode, newNode);
        // 遍历迭代
        while (oldNode != null) {
            newNode.next = getCloneNode(oldNode.next);
            newNode.random = getCloneNode(oldNode.random);

            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return visitedHash.get(head);
    }

    private Node getCloneNode(Node node) {
        if (node == null) {
            return null;
        }
        if (visitedHash.containsKey(node)) {
            return visitedHash.get(node);
        } else {
            Node newNode = new Node(node.val);
            visitedHash.put(node, newNode);
            return newNode;
        }
    }

    Map<Node, Node> visitedHash = new HashMap<>();

    // 2.递归-哈希：记录访问过的节点 -可推荐
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!visitedHash.containsKey(head)) {
            Node newNode = new Node(head.val);
            visitedHash.put(head, newNode);
            newNode.next = copyRandomList(head.next);
            newNode.random = copyRandomList(head.random);
            return newNode;
        }
        return visitedHash.get(head);
    }

    // 3.    O 1 迭代  100 + 91
    //     1.遍历拷贝next相间的结点  A > A' > B > B'
    //     2.更新新节点的 A'.random = A.random.next
    //     3. 断开新旧结点next连接，恢复原链表
    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
        }

        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = node.next;
            newNode.random = node.random != null ? node.random.next : null;
        }

        Node newHead = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node newNode = node.next;
            node.next = newNode.next;
            newNode.next = newNode.next != null ? newNode.next.next : null;
        }
        return newHead;
    }
}
