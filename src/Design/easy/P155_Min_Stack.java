package Design.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/17
  *@Describe:
 */

import java.util.Deque;
import java.util.LinkedList;

public class P155_Min_Stack {

    /*
    辅助栈 同步 / 不同步
    题目限定栈空不会pop ，所以不用检验 isEmpty()
    同步法 都放入   14 + 7
     */
     class MinStack {
        Deque<Integer> dataDeque; // 数据栈
        Deque<Integer> minDeque; // 存最小值的辅助栈
        /** initialize your data structure here. */
        public MinStack() {
            dataDeque=new LinkedList<>();
            minDeque=new LinkedList<>();
            minDeque.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            dataDeque.push(x);
            minDeque.push(Math.min(minDeque.peek(),x)); // 如果 x 比栈顶最小元素小
        }

        public void pop() {
            dataDeque.pop();
            minDeque.pop();
        }

        public int top() {
            return dataDeque.peek();
        }

        public int getMin() {
            return minDeque.peek();
        }
    }

}
