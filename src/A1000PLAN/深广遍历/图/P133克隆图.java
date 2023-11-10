package A1000PLAN.深广遍历.图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *desc:
 *@author lin
 *@since 2023/11/9
 **/
public class P133克隆图 {
    static class Node {
        public int val;

        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // BFS-官方-推荐
    public Node cloneGraph1(Node node) {
        if (node == null) {
            return null;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        // 是否已经创建过访问过
        Map<Node, Node> visited = new HashMap<>();
        visited.put(node, new Node(node.val));

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            for (Node nei : poll.neighbors) {
                if (!visited.containsKey(nei)) {
                    visited.put(nei, new Node(nei.val));
                    queue.add(nei);
                }
                visited.get(poll).neighbors.add(visited.get(nei));
            }
        }
        return visited.get(node);
    }

    // DFS-官方-推荐-不够BFS快
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node cloneNode = new Node(node.val);
        visited.put(node, cloneNode);

        for (Node nei : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(nei));
        }
        return cloneNode;
    }

    // 自己写的，过了 - 不推荐
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return null;
        }
        Node newNode = new Node(node.val);

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        // 是否已经创建过
        Map<Integer, Node> map = new HashMap<>();
        map.put(newNode.val, newNode);

        // 访问
        Set<Integer> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            visited.add(poll.val);
            Node cur = map.get(poll.val);
            for (Node nei : poll.neighbors) {
                if (!map.containsKey(nei.val)) {
                    Node node1 = new Node(nei.val);
                    cur.neighbors.add(node1);
                    map.put(node1.val, node1);
                } else {
                    cur.neighbors.add(map.get(nei.val));
                }
                if (!visited.contains(nei.val) && !queue.contains(nei)) {
                    queue.add(nei);
                }
            }
        }
        return newNode;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node3 = new Node(3);
        node1.neighbors.add(node4);
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        final Node node = new P133克隆图().cloneGraph(node1);
    }
}
