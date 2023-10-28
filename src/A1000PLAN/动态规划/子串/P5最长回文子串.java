package A1000PLAN.动态规划.子串;

/**
 * desc:值
 *
 * @author Lin
 * @since 2023/9/5
 **/
public class P5最长回文子串 {
    /*https://leetcode.cn/problems/longest-palindromic-substring/solutions/7792/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
    注意点：这题不是求长度，类似最长回文子序列那题，因为长度无法得到最后的子串，所以判断是否是回文子串，过程中记录最大值
    解释：如果它是回文子串，并且长度大于 2，那么将它首尾的两个字符去除之后，它仍然是个回文子串
    dp定义：用 dp[i][j]表示字符串 s 的下标 [i,j] 的字符串是否是回文子串
    转移方程： 相等时：dp[i][j] = dp[i + 1][j - 1] / <3的情况是true
             不等： = false
    初始化：长度为1都是子串
    遍历顺序: dp取决于i+1,j-1  所以i行倒序，j列正序
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int maxLen = 0;
        int begin = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 与最长回文子序列不一样的地方：这里如果有不一样就不成立,dp不会继续推了
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    // 三个元素内首尾相等都是子串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 相等继承 内区间是不是子串
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                    // 是子串
                    if (dp[i][j] && j - i > maxLen) {
                        begin = i;
                        maxLen = j - i;
                    }
                }
            }
        }
        return s.substring(begin, begin + maxLen + 1);
    }

    public static void main(String[] args) {
        System.out.println("abc".substring(0, 1));
        System.out.println(new P5最长回文子串().longestPalindrome2("babad"));
        System.out.println(new P5最长回文子串().longestPalindrome2("cbbd"));
        System.out.println(new P5最长回文子串().longestPalindrome("cbbd"));
    }

    /*
    中心扩展法：中心点由 1个或2个点组成中心点，奇偶，枚举每一个中心点然后两侧扩展
     */
    public String longestPalindrome2(String s) {
        int n = s.length();
        int begin = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            int lenOdd = expand(s, i, i);
            int lenEven = expand(s, i, i + 1);
            int cur = Math.max(lenEven, lenOdd);
            if (cur > end - begin) {
                begin = i - (cur - 1) / 2;
                end = i + (cur / 2);
            }
        }
        return s.substring(begin, end + 1);
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
