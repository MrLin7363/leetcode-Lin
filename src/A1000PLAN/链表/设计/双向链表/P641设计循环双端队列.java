package A1000PLAN.链表.设计.双向链表;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/19
 **/
public class P641设计循环双端队列 {
    // 双向链表
    class MyCircularDeque {
        private class DlinkedNode {
            private DlinkedNode prev;

            private DlinkedNode next;

            private int val;

            public DlinkedNode(int val) {
                this.val = val;
            }
        }

        private DlinkedNode head;

        private DlinkedNode tail;

        private int size;

        private int capacity;

        public MyCircularDeque(int k) {
            capacity = k;
            size = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            DlinkedNode node = new DlinkedNode(value);
            if (size == 0) { // head == null  不可以用这个判断
                head = tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            DlinkedNode node = new DlinkedNode(value);
            if (size == 0) {
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
            size--;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return head.val;
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return tail.val;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }
    }
}
