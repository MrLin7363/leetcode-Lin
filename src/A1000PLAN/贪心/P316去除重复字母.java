package A1000PLAN.贪心;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/15
 **/
public class P316去除重复字母 {
    /*
    去除重复字母，保证字典序最小
    栈+贪心+字母转int[26]
    1. 逐个进栈，遇到比栈顶小的，while 判断能否移除栈顶，通过每个相同元素的最后的下标判断
       2.如果栈中已经有了这个元素，则不用进栈
     */
    public String removeDuplicateLetters(String s) {
        // 字母的最后下标数组如 a 100
        int[] lastIndex = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            lastIndex[chars[i] - 'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();
        // 栈中已有元素
        boolean[] visited = new boolean[26];

        for (int i = 0; i < chars.length; i++) {
            // 2.相同的不进栈
            if (visited[chars[i] - 'a']) {
                continue;
            }
            // 1.条件
            while (!stack.isEmpty() && chars[i] - stack.peekLast() < 0 && lastIndex[stack.peekLast() - 'a'] > i) {
                visited[stack.peekLast() - 'a'] = false;
                stack.pollLast();
            }
            stack.offer(chars[i]);
            visited[chars[i] - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new P316去除重复字母().removeDuplicateLetters("ecbacba");
    }
}
