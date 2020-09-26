package String.easy;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/25 10:42
 * @Describe:
 */
public class Valid_Parentheses_20 {

    //用hashtable存每个括号
    private HashMap<Character,Character> mappings;

    //初始化三个括号
    public Valid_Parentheses_20(){
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')','(');
        this.mappings.put(']','[');
        this.mappings.put('}','{');
    }

    public boolean isValid(String s) {
        //初始化栈
        Stack<Character> stack=new Stack<Character>();
        for(int i=0;i<s.length();i++){
            //取到当前的值
            char c=s.charAt(i);
            //如果是一个闭括号
            if(mappings.containsKey(c)){
                //取当前栈顶元素
                char topElement=stack.empty()?'#':stack.pop();
                //如果栈顶元素和当前值不匹配
                if(topElement!=mappings.get(c)){
                    return false;
                }
            }else{ //这是一个开括号
                stack.push(c);
            }
        }
        //如果栈还有元素就不能匹配
        return stack.isEmpty();
    }

}
