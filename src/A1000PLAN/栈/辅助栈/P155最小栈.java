package A1000PLAN.栈.辅助栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/24
 **/
public class P155最小栈 {
    /*
    辅助栈：正常栈+最小栈
     */
    class MinStack {
        private Deque<Integer> stack;

        private Deque<Integer> minStack;

        public MinStack() {
            this.stack = new ArrayDeque<>();
            this.minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                int temp = val > minStack.peek() ? minStack.peek() : val;
                minStack.push(temp);
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
