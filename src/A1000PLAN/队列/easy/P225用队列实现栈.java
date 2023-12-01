package A1000PLAN.队列.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 *desc:
 *@author lin
 *@since 2023/12/1
 **/
public class P225用队列实现栈 {
    class MyStack {
        Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<Integer>();
        }

        public void push(int x) {
            int n = queue.size();
            queue.offer(x);
            for (int i = 0; i < n; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
