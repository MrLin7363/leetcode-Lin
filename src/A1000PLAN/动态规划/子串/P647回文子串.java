package A1000PLAN.动态规划.子串;

/**
 * desc:个数
 *
 * @author Lin
 * @since 2023/9/5
 **/
public class P647回文子串 {
    /*
    最长回文子串的个数
    解释：如果它是回文子串，并且长度大于 2，那么将它首尾的两个字符去除之后，它仍然是个回文子串
    DP定义：用 dp[i][j]表示字符串 s 的下标 [i,j] 的字符串是否是回文子串
    转移方程：s[i]!=s[j] : 不是子串
            s[i]==s[j] : <3的情况是回文子串/ dp[i + 1][j - 1]是子串，当前也是子串
    初始化：长度为1的是子串
    遍历顺序: 行倒序，列正序
     */
    public int countSubstrings(String s) {
        int n = s.length();
        int ans = 0;
        boolean[][] dp = new boolean[n][n];
        // 只有一个字符时，比如 a 自然是一个回文串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            ans++;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // false可以省略的
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3 || dp[i + 1][j - 1]) {
                        // 首尾字符相等时，如果是三个元素或者[i + 1][j - 1]也是一个子串，那么当前也是子串
                        dp[i][j] = true;
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        new P647回文子串().countSubstrings2("abc");
        new P647回文子串().countSubstrings("a");
        new P647回文子串().countSubstrings("abc");
    }

    /*
    中心扩展法：中心点由 1个或2个点组成中心点，奇偶，枚举每一个中心点然后两侧扩展
    */
    public int countSubstrings2(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += expand(s, i, i);
            ans += expand(s, i, i + 1);
        }
        return ans;
    }

    private int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
        return count;
    }
}
