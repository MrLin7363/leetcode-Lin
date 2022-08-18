 

package hw.字符串;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/13
 **/
public class P227基本计算器2 {

    static  Deque<Integer> numsStack = new ArrayDeque<>();

    static  Deque<Character> opStack = new ArrayDeque<>();

    static Map<Character, Integer> map = new HashMap<>(); // 存放优先级

    public static int calculate(String s) {
        s = '0' + s; // 对负数的处理+0 如果开头有负数 如  -1  那么在栈里就是 -1
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            // 数字，找到当前的一个完整的数字 如 111
            if (c >= '0' && c <= '9') {
                int x = 0;
                int j = i;
                while (j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    x = x * 10 + s.charAt(j) - '0';
                    j++;
                }
                i = j - 1;
                numsStack.push(x);
            } else {
                // 栈顶非空且栈顶操作符优先级大于等于当前操作符C的优先级
                // 注意下面不能用IF 要用WHILE
                while (!opStack.isEmpty() && map.get(opStack.peek()) >= map.get(c)) {
                    calculate();
                }
                // 常规进栈
                opStack.push(c);
            }
        }
        while (!opStack.isEmpty()) {
            calculate();
        }
        return numsStack.pop();
    }

    public static void main(String[] args) {
        System.out.println(calculate("1*2-3/4+5*6-7*8+9/10"));
    }
    private static void calculate() {
        int x = numsStack.pop();
        int y = numsStack.pop();
        int op = opStack.pop();
        int res = 0;
        if (op == '+') {
            res = x + y;
        }
        if (op == '-') {
            res = y - x;
        }
        if (op == '*') {
            res = x * y;
        }
        if (op == '/') {
            res = y / x;
        }
        numsStack.push(res);
    }

}
