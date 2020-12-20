package Design.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/19
  *@Describe:
 */

public class P641_Design_Circular_Deque {
    /*
    数组实现，注意多加一个元素是为了使 队列为空 和 队列已满 的判断条件不冲突
     (tail-1 + capacity) % capacity;   -1 操作的前面要加上capacity 否则可能为负数
    100 + 27
     */
    static class MyCircularDeque {
        private int[] queue;
        private int capacity;
        private int head; // 队头索引
        private int tail; // 队尾索引，上一个元素有值，当前tail 无值

        public MyCircularDeque(int k) {
            capacity=k+1;
            queue=new int[capacity];
            head=tail=0;
        }

        public boolean insertFront(int value) {
            // 队列已满
            if ( (tail + 1) % capacity == head ){
                return false;
            }
            head = (head -1 + capacity) % capacity ;
            queue[head]=value;
            return true;
        }

        public boolean insertLast(int value) {
            // 队列已满
            if ( (tail + 1) % capacity == head ){
                return false;
            }
            queue[tail]=value;   // 注意先赋值，再移位
            tail = (tail+1) % capacity;
            return true;
        }

        public boolean deleteFront() {
            // 队列为空
            if (head==tail){
                return false;
            }
            head = (head+1) % capacity;
            return true;
        }

        public boolean deleteLast() {
            // 队列为空
            if (head==tail){
                return false;
            }
            tail = (tail-1 + capacity) % capacity;
            return true;
        }

        public int getFront() {
            if (head==tail){
                return -1;
            }
            return queue[head];
        }

        public int getRear() {
            if (head==tail){
                return -1;
            }
            return queue[( tail-1 +capacity) % capacity]; // 注意最后一个元素是空的
        }

        public boolean isEmpty() {
            return head==tail;
        }

        public boolean isFull() {
            return (tail+1) % capacity == head;
        }
    }

    public static void main(String[] args) {
        MyCircularDeque myCircularDeque=new MyCircularDeque(3);
        myCircularDeque.insertFront(9);
        System.out.println(myCircularDeque.getRear());

    }
}
