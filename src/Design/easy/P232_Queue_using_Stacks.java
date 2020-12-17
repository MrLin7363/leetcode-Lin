package Design.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/17
  *@Describe:
 */

import java.util.Stack;

public class P232_Queue_using_Stacks {
    /*
    1.两个栈，倒两次就变成先进先出了，分别push，这样栈的先进后出就是队列的先进先出 100 + 56
    2.两个栈  当第二个栈为空时才将第一个栈的push进，瘫环分析少 100 +5
     */
    class MyQueue {
        Stack<Integer> stack1; // 存数据
        Stack<Integer> stack2;
        /** Initialize your data structure here. */
        public MyQueue() {
            stack1=new Stack<>();
            stack2=new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            stack1.push(x);
            while (!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return stack1.pop();
        }

        /** Get the front element. */
        public int peek() {
            return stack1.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty();
        }
    }
    //-------------------------------------------------------------------- 100 + 5
    class MyQueue2 {
        Stack<Integer> stack1; // 存数据
        Stack<Integer> stack2;
        /** Initialize your data structure here. */
        public MyQueue2() {
            stack1=new Stack<>();
            stack2=new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
           stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stack2.isEmpty()){
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (stack2.isEmpty()){
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty()&&stack2.isEmpty();
        }
    }
}
