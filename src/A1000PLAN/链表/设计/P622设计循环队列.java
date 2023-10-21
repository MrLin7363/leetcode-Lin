package A1000PLAN.链表.设计;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/17
 **/
public class P622设计循环队列 {
    /*
    链表
    head：链表的头节点，队列的头节点。
    tail：链表的尾节点，队列的尾节点。
    capacity：队列的容量，即队列可以存储的最大元素数量。
    size：队列当前的元素的数量。
     */
    class MyCircularQueue {
        private ListNode head;

        private ListNode tail;

        private int capacity;

        private int size;

        public MyCircularQueue(int k) {
            capacity = k;
            size = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            ListNode node = new ListNode(value);
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            head = head.next;
            size--;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return head.val;
        }

        public int Rear() {
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

    /*
    数组
    elements：一个固定大小的数组，用于保存循环队列的元素。
    capacity：循环队列的容量，即队列中最多可以容纳的元素数量。
    front：队列首元素对应的数组的索引。
    rear：队列尾元素对应的索引的下一个索引。
     */
    class MyCircularQueue2 {
        private int[] elements;

        private int front;

        private int rear;

        private int capacity;

        public MyCircularQueue2(int k) {
            this.capacity = k + 1;
            elements = new int[capacity];
            front = rear = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            elements[rear] = value;
            rear = (rear + 1) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % capacity;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return elements[front];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return elements[(rear - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public boolean isFull() {
            return (rear + 1) % capacity == front;
        }
    }
}
