package A1000PLAN.链表.设计.双向链表;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:  最不经常使用（LFU）
 *
 * @author
 * @since 2023/10/21
 **/
public class P460LFU缓存_hard {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        // cache.put(1, 1);
        // cache.put(2, 2);
        // System.out.println(cache.get(1));
        // cache.put(3, 3);
        // System.out.println(cache.get(2));
        // System.out.println(cache.get(3));
        // cache.put(4, 4);
        // System.out.println(cache.get(1));
        // System.out.println(cache.get(3));
        // System.out.println(cache.get(4));
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

    /*
    双哈希表+双向链表
    题目要求：计数器记录每个key的使用次数，优先淘汰次数最低，次数一样的情况淘汰最久未使用的
     */
    static class LFUCache {
        // 可以通过keyTable.size记录当前共有多少个节点了
        private Map<Integer, Node> keyTable = new HashMap<>();

        private Map<Integer, DLinkedNode> freqTable = new HashMap<>();

        private int minFreq;// 最小频率

        private int capacity;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.minFreq = 0;
        }

        public int get(int key) {
            if (capacity == 0) {
                return -1;
            }
            if (!keyTable.containsKey(key)) {
                return -1;
            }
            Node node = keyTable.get(key);
            int freq = node.freq;
            int val = node.val;
            // 删除在原先freq频次上的node
            freqTable.get(freq).remove(node);

            // 如果当前freq是最后一个元素，那么minFreq+1
            if (freqTable.get(freq).size == 0) {
                if (minFreq == freq) {
                    minFreq += 1;
                }
            }

            // 新建新freq的node
            // 注意是需要新建node的，因为链表的引用是node，所以之前的node会因为新的put被取消引用
            Node newNode = new Node(key, val, freq + 1);
            DLinkedNode list = freqTable.getOrDefault(freq + 1, new DLinkedNode());
            list.addFirst(newNode);
            freqTable.put(freq + 1, list);
            keyTable.put(key, newNode);
            return node.val;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            if (!keyTable.containsKey(key)) {
                // 缓存满了,删除节点
                if (keyTable.size() == capacity) {
                    Node removeNode = freqTable.get(minFreq).getTail();
                    keyTable.remove(removeNode.key);
                    freqTable.get(minFreq).remove(removeNode);
                }

                // 已存在又put是新增一个频率为1的node
                Node newNode = new Node(key, value, 1);
                DLinkedNode list = freqTable.getOrDefault(1, new DLinkedNode());
                list.addFirst(newNode);
                freqTable.put(1, list);
                keyTable.put(key, newNode);
                minFreq = 1;
            } else {
                // 和get一样,删除在原先freq频次上的node
                Node node = keyTable.get(key);
                int freq = node.freq;
                freqTable.get(freq).remove(node);
                // 如果当前freq是最后一个元素，那么minFreq+1
                if (freqTable.get(freq).size == 0) {
                    if (minFreq == freq) {
                        minFreq += 1;
                    }
                }

                Node newNode = new Node(key, value, freq + 1);
                DLinkedNode list = freqTable.getOrDefault(freq + 1, new DLinkedNode());
                list.addFirst(newNode);
                freqTable.put(freq + 1, list);
                keyTable.put(key, newNode);
            }
        }

        // Node结构
        private class Node {
            private Integer key;

            private Integer val;

            // 频率
            private Integer freq;

            private Node prev;

            private Node next;

            public Node() {
                key = -1;
                val = -1;
                freq = 0;
            }

            public Node(int key, int val, int freq) {
                this.key = key;
                this.val = val;
                this.freq = freq;
            }
        }

        // 双向链表结构
        private class DLinkedNode {
            private Node head;

            private Node tail;

            private int size;

            public DLinkedNode() {
                head = new Node();
                tail = new Node();
                head.next = tail;
                tail.prev = head;
                size = 0;
            }

            public void remove(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.prev = null;
                node.next = null;
                size--;
            }

            public void addFirst(Node node) {
                head.next.prev = node;
                node.next = head.next;
                head.next = node;
                node.prev = head;
                size++;
            }

            public Node getTail() {
                return tail.prev;
            }
        }
    }
}