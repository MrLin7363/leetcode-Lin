package String.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: Junlin Chen
 * @Date: 2021/07/03 14:13
 * @Describe:
 */
public class P32_Longest_Valid_Parentheses {

    /*
    Without extra space 100+63
    */
    public int longestValidParentheses3(String s) {
        int left = 0, right = 0, maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left < right) {
                left = right = 0;
            } else if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left > right) {
                left = right = 0;
            } else if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            }
        }
        return maxLength;
    }

    /*
    Stack 73+92
    */
    public int longestValidParentheses2(String s) {
        int max=0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==')'){
                stack.push(i);
            }else{
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                }else{
                    max=Math.max(max,i-stack.peek());
                }
            }
        }
        return max;
    }
    /*
    DP 100+63
     */
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()]; // dp[0]默认为0
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2; // >=2 时就是 ()()的情况 或者 (())() ...
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') { //   ()()(())
                    dp[i] = dp[i - 1] + ((i - dp[i - 1] - 1 >= 2) ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}
