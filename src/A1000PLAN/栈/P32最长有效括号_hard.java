package A1000PLAN.栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc: 栈/DP
 *
 * @author Lin
 * @since 2023/10/27
 **/
public class P32最长有效括号_hard {
    /*
3.无额外空间 - 推荐  - 平衡法
*/
    public int longestValidParentheses1(String s) {
        int max = 0;
        int left = 0;
        int right = 0;

        // 单单这个无法解决((()的情况，从后往前能解决
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else {
                right++;
            }
            if (right > left) {
                left = right = 0;
            }
            if (left == right) {
                max = Math.max(max, right * 2);
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else {
                right++;
            }
            if (left > right) {
                left = right = 0;
            }
            if (left == right) {
                max = Math.max(max, right * 2);
            }
        }
        return max;
    }

    /*
    1.栈 -难理解 记录下标
    https://leetcode.cn/problems/longest-valid-parentheses/solutions/922407/zhan-zui-jian-jie-yi-dong-de-dai-ma-cjav-xa7v/?envType=study-plan-v2&envId=top-100-liked
     */
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        int start = -1; // 表示第一次或重新计算合法括号的开始节点 如第一次遇到() 或者 重新计算i=3时 ))()()
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                // )不如栈，记录start
                if (stack.isEmpty()) {
                    start = i;
                } else {
                    stack.pop();
                    max = Math.max(max, stack.isEmpty() ? i - start : i - stack.peek());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        new P32最长有效括号_hard().longestValidParentheses("(()())");
        new P32最长有效括号_hard().longestValidParentheses("))(())");
        new P32最长有效括号_hard().longestValidParentheses1("()))()()");
    }

    /*
    2.DP  https://leetcode.cn/problems/longest-valid-parentheses/solutions/206995/dong-tai-gui-hua-si-lu-xiang-jie-c-by-zhanganan042/?envType=study-plan-v2&envId=top-100-liked
    dp[i] 表示以下标 i 字符结尾的最长有效括号的长度
    s[i]==( 无法形成有效对，dp[i]=0
    s[i]==)
        s[i-1]==(    dp[i]=dp[i-2]+2
        s[i-1]==)
            i-dp[i-1]-1>=0 && s[i-dp[i-1]-1]=(
                dp[i]=dp[i-1]+2
            还有一种情况  ()(()) 前面的也是有效子串
            i-dp[i-1]-2>=0
                dp[i]=dp[i-1]+2 + dp[i-dp[i-1]-2]
    */
    public int longestValidParentheses2(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
                } else {
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2;
                        if (i - dp[i - 1] - 2 >= 0) {
                            dp[i] = dp[i] + dp[i - dp[i - 1] - 2];
                        }
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
