package Design.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/17
  *@Describe:
 */

import java.util.LinkedList;
import java.util.Queue;

public class P225_Stack_using_Queues {
    // 一个队列，先进先出的队列 100 + 89
    class MyStack {
        Queue<Integer> queue; // 这里不用双端队列，否则太简单，这个队列先进先出
        /** Initialize your data structure here. */
        public MyStack() {
            queue=new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            int n=queue.size();
            queue.offer(x); // 添加到队尾
            // 将剩余队列结点移动到队列尾部
            for (int i = 0; i < n; i++) {
                queue.offer(queue.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
