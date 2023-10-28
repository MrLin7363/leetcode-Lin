package A1000PLAN.栈.辅助栈;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/24
 **/
public class P394字符串解码 {
    /*
    辅助栈-推荐 字母栈，数字栈
     */
    public String decodeString1(String s) {
        Deque<Integer> digitStack = new ArrayDeque<>();
        Deque<StringBuilder> letterStack = new ArrayDeque<>();
        int multi = 0;
        StringBuilder ans = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                multi = multi * 10 + c - '0';
            } else if (c == '[') {
                digitStack.push(multi);
                letterStack.push(ans);
                ans = new StringBuilder();
                multi = 0;
            } else if (Character.isLetter(c)) {
                ans.append(c);
            } else {
                StringBuilder prevString = letterStack.pop();
                Integer repTime = digitStack.pop();
                while (repTime-- > 0) {
                    prevString.append(ans);
                }
                ans = prevString;
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(new P394字符串解码().decodeString("3[a]2[bc]"));
        System.out.println(new P394字符串解码().decodeString("3[a2[c]]"));
        System.out.println(new P394字符串解码().decodeString1("3[a2[c]]"));
        System.out.println(new P394字符串解码().decodeString1("3[a]2[bc]"));
    }

    /*
     一个栈
    1.遇到数字，进栈
    2.遇到字母或[ 进栈
    3.遇到 ] 出栈
        计算字母，直到[
        再出栈重复次数数字
        拼接好再重新入栈，知道遇到新的[
     */
    private int index;

    public String decodeString2(String s) {
        LinkedList<String> stack = new LinkedList<>(); // 方便重头开始遍历
        index = 0;
        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                stack.addLast(getDigit(s));
            } else if (Character.isLetter(s.charAt(index)) || s.charAt(index) == '[') {
                stack.addLast(String.valueOf(s.charAt(index++)));
            } else { // ]
                index++;
                // 计算字母
                LinkedList<String> letterList = new LinkedList<>();
                while (!"[".equals(stack.peekLast())) {
                    letterList.addLast(stack.removeLast());
                }
                Collections.reverse(letterList);
                String letter = getListString(letterList);

                // [  括号出栈
                stack.removeLast();

                // 计算重复次数
                int repTime = Integer.valueOf(stack.removeLast());
                StringBuilder sb = new StringBuilder();
                while (repTime-- > 0) {
                    sb.append(letter);
                }
                stack.addLast(sb.toString());
            }
        }
        return getListString(stack);
    }

    private String getDigit(String s) {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(s.charAt(index))) {
            sb.append(s.charAt(index++));
        }
        return sb.toString();
    }

    private String getListString(LinkedList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    /*
    递归 -推荐
    遇到数字，过滤[,递归获取字母，过滤]
    结束条件： ] 或者 到结尾
     */
    private int idx;

    public String decodeString(String s) {
        idx = 0;
        return dfs(s);
    }

    private String dfs(String s) {
        StringBuilder cur = new StringBuilder();
        int curNum = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                curNum = curNum * 10 + s.charAt(idx++) - '0';
            } else if (s.charAt(idx) == ']') {
                break;
            } else if (Character.isLetter(s.charAt(idx))) {
                cur.append(s.charAt(idx++));
            } else {
                // 去掉左括号
                idx++;
                String temp = dfs(s);
                for (int i = 0; i < curNum; i++) {
                    cur.append(temp);
                }
                curNum = 0;
                // 去掉右括号
                idx++;
            }
        }
        return cur.toString();
    }
}
