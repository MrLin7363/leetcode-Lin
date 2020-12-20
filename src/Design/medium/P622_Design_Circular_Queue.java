package Design.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/19
  *@Describe: 设计循环队列
 */

import Construct.ListNode;
import java.util.concurrent.locks.ReentrantLock;

public class P622_Design_Circular_Queue {
    /*
    数组  94 + 64  注意 % 前都要加 ()
     (headIndex +count-1 )% capacity  队尾结点
    (headIndex+count) % capacity   列尾结点下一个
     */
    class MyCircularQueue {
        private int[] queue;
        private int capacity; // 数组长度
        private int count; // 队列长度，已有元素
        private int headIndex;
        private ReentrantLock lock = new ReentrantLock(); // 添加加了锁后  8 + 27

        public MyCircularQueue(int k) {
            queue=new int[k];
            capacity=k;
            count=0;
            headIndex=0;
        }
        public boolean enQueue(int value) {
            lock.lock();
            try {
                if (count == capacity) {
                    return false;
                }
                queue[(headIndex + count) % capacity] = value; // 队列尾结点下一个
                count++;
            }finally {
                lock.unlock();
            }
            return true;
        }
        // 直接移动头结点，没有标志就是删除
        public boolean deQueue() {
            if (count==0){
                return false;
            }
            headIndex= (headIndex+1) % capacity;
            --count;
            return true;
        }
        // 第一个元素
        public int Front() {
            if (count==0){
                return -1;
            }
            return queue[headIndex];
        }
        // 最后一个元素
        public int Rear() {
            if (count==0){
                return -1;
            }
            return queue[ (headIndex +count-1) % capacity];  // 队尾结点
        }

        public boolean isEmpty() {
            return count==0;
        }

        public boolean isFull() {
            return count==capacity;
        }
    }

    /*
    单链表  94 + 80
     */
    class MyCircularQueue2 {
        private ListNode head,tail;
        private int count;
        private int capacity;
        private ReentrantLock lock = new ReentrantLock(); // 添加加了锁后  8 + 51

        public MyCircularQueue2(int k) {
            capacity=k;
        }

        public boolean enQueue(int value) {
            lock.lock();
            try {
                if (count == capacity) {
                    return false;
                }
                ListNode node = new ListNode(value);
                if (count == 0) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    tail = node;
                }
                count++;
            }finally {
                lock.unlock();
            }
            return true;
        }

        public boolean deQueue() {
            if (count==0){
                return false;
            }
            head=head.next;
            count--;
            return true;
        }

        public int Front() {
            if (count==0){
                return -1;
            }
            return head.val;
        }

        public int Rear() {
            if (count==0){
                return -1;
            }
            return tail.val;
        }

        public boolean isEmpty() {
            return count==0;
        }

        public boolean isFull() {
            return count==capacity;
        }
    }
}
