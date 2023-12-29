package A1000PLAN.树.验证树;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/12
 **/
public class P331验证二叉树的前序序列化 {
    /*
    On+On 栈
    判断槽位的合法性
     */
    public boolean isValidSerialization2(String preorder) {
        int n = preorder.length();
        int i = 0;
        Deque<Integer> stack = new ArrayDeque();
        stack.push(1);
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            } else if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }

    /*
    On+O1 栈
    维护一个总槽位就行
    */
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            } else if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                i++;
                slots--;
            } else {
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots++; // slots-1+2
            }
        }
        return slots == 0;
    }

    public static void main(String[] args) {
        new P331验证二叉树的前序序列化().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
    }
}
