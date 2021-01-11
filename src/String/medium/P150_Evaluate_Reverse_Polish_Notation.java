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
    栈 + - * / 数字有效
     */
    public int evalRPN(String[] tokens) {
        Deque<Character> stack=new ArrayDeque<>();
        for (char c:tokens.toString().toCharArray()){
            // 数字
            if (c-'0'>=0&&c-'0'<=9){
                stack.push(c);
            }
            int num1=stack.poll();
            int num2=stack.poll();
            if (c=='+' ){

            }
            if (c=='+' || c=='-' || c=='*' || c=='/'){
            }
            switch (c){
//                case '+': stack.push();
            }
        }
        return 0;
    }

}