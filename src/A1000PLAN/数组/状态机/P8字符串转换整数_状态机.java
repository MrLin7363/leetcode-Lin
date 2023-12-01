package A1000PLAN.数组.状态机;

import java.util.HashMap;
import java.util.Map;

/**
 *desc:
 *@author lin
 *@since 2023/11/23
 **/
public class P8字符串转换整数_状态机 {
    /* https://leetcode.cn/problems/string-to-integer-atoi/
    状态机:
    start -> number -> number
    start -> +/- -> signed
    signed -> number -> number
    signed -> _/+/-/other -> end
    number -> _/+/-/other -> end
    start -> other -> end
    ......
     */
    public int myAtoi(String s) {
        AutoMachine autoMachine = new AutoMachine();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            autoMachine.process(chars[i]);
        }
        return (int) autoMachine.ans * autoMachine.sign;
    }

    private class AutoMachine {
        private long ans = 0;

        private int sign = 1;

        private String state = "start";

        // +/- 2; number 1; 空格/字母 0
        private Map<String, String[]> map = new HashMap<String, String[]>() {
            {
                put("start", new String[] {"start", "signed", "number", "end"});
                put("signed", new String[] {"end", "end", "number", "end"});
                put("number", new String[] {"end", "end", "number", "end"});
                put("end", new String[] {"end", "end", "end", "end"});
            }
        };

        public void process(char c) {
            state = map.get(state)[getCol(c)];
            if ("number".equals(state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, Integer.MAX_VALUE) : Math.min(ans, -(long)Integer.MIN_VALUE);
            } else if ("signed".equals(state)) {
                sign = c == '+' ? 1 : -1;
            }
        }

        private int getCol(char c) {
            if (c == ' ') {
                return 0;
            } else if (c == '+' || c == '-') {
                return 1;
            } else if (Character.isDigit(c)) {
                return 2;
            } else {
                return 3;
            }
        }
    }

    /*
    数学 %10+cur  自己写的很臃肿
    Character.getNumericValue(chars[i])  -> chars[i]-'0'
     */
    public int myAtoi2(String s) {
        char[] chars = s.toCharArray();
        int res = 0;
        int state = 0; // 0没遇到+- ; 1 + ; 2 -
        for (int i = 0; i < chars.length; i++) {
            if (state != 0 && (chars[i] == '+' || chars[i] == '-' || chars[i] == ' ')) {
                return res;
            }
            if (Character.isAlphabetic(chars[i]) || chars[i] == '.') {
                return res;
            }
            if (chars[i] == '+') {
                state = 1;
            } else if (chars[i] == '-') {
                state = 2;
            } else if (Character.isDigit(chars[i])) {
                int num = chars[i] - '0';
                if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && -num < Integer.MIN_VALUE % 10)) {
                    return Integer.MIN_VALUE;
                }
                if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                    return Integer.MAX_VALUE;
                }
                if (state == 2) {
                    res = res * 10 - num;
                } else {
                    state = 1;
                    res = res * 10 + num;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(-Integer.MAX_VALUE);
        System.out.println(-Integer.MIN_VALUE % 10);
        System.out.println(-Character.getNumericValue('7'));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2("-2147483647"));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2("2147483646"));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2("2147483648"));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2("   +0 123"));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2("00000-42a1234"));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2("3.14159"));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2("+-12"));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2("+1"));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2(" -42"));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2("words and 987"));
        System.out.println(new P8字符串转换整数_状态机().myAtoi2(" -4193 with words"));
    }
}
