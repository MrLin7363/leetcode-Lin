package String.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
  *@Author JunLin
  *@Date 2021/1/11
  *@Describe:
 */

public class P150_Evaluate_Reverse_Polish_Notation {
    /*
    栈 + - * / 数字  98+36
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack=new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]){
                case "+":
                    stack.push(stack.poll()+stack.poll());
                    break;
                case "-":
                    int l=stack.poll();
                    stack.push(stack.poll()-l); //第二个栈顶减第一个栈顶
                    break;
                case "*":
                    stack.push(stack.poll()*stack.poll());//第二个栈顶除以第一个栈顶
                    break;
                case "/":
                    int last=stack.poll();
                    stack.push(stack.poll()/last);
                    break;
                    default:
                        stack.push(Integer.parseInt(tokens[i]));
                        break;
            }
        }
        return stack.poll();
    }

}