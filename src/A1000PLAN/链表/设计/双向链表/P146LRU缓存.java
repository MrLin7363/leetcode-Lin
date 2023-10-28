package A1000PLAN.链表.设计.双向链表;

import java.util.HashMap;
import java.util.Map;

/**
 * desc: LinkedHashMap = 双向链表+哈希
 * LRU (最近最少使用) 缓存
 * @since 2023/10/11
 **/
public class P146LRU缓存 {
    class LRUCache {
        // 双向链表结构
        private class DLinkedNode {
            private DLinkedNode prev;

            private DLinkedNode next;

            private int key;

            private int val;

            public DLinkedNode() {
            }

            public DLinkedNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private DLinkedNode head;

        private DLinkedNode tail;

        private int capacity;

        private int size;

        private Map<Integer, DLinkedNode> cache = new HashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            } else {
                moveToHead(node);
                return node.val;
            }
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                node = new DLinkedNode(key, value);
                cache.put(key, node);
                addToHead(node);
                size++;
                if (size > capacity) {
                    cache.remove(tail.prev.key);
                    removeHead(tail.prev);
                    size--;
                }
            } else {
                // 节点存在，移动到头部
                node.val = value;
                moveToHead(node);
            }
        }

        private void moveToHead(DLinkedNode node) {
            removeHead(node);
            addToHead(node);
        }

        private void removeHead(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
    }
}
