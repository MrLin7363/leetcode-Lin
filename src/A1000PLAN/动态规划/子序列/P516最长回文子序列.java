package A1000PLAN.动态规划.子序列;

/**
 * desc: 首尾回文-长度
 *
 * @author Lin
 * @since 2023/9/5
 **/
public class P516最长回文子序列 {
    /*
    不适应子串
    解释：对于一个子序列而言，如果它是回文子序列，并且长度大于 2，那么将它首尾的两个字符去除之后，它仍然是个回文子序列。因此可以用动态规划的方法计算给定字符串的最长回文子序列。
    状态定义：用 dp[i][j]表示字符串 s 的下标范围 [i,j] 内的最长回文子序列的长度
    状态转移：i<j时，考虑s[i]和s[j]的是否相等
        相等：首先得到 [i+1,j-1]内最长回文子序列，然后在该子序列首尾加上s[i],s[j]依然是回文子序列，dp[i][j]=dp[i+1][j-1]+2;
        不同：s[i],s[j]不能作为同一个回文序列的首尾  dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
    初始化： dp[i][i]=1  一个元素就是回文子序列
    顺序： 转移方程由短序列向长序列转移，且是由i+1,j-1推导来  所以-> i倒序，j正序
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 与最长回文子串不一样的地方：这里如果不等于，继续推导继承前面的
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        new P516最长回文子序列().longestPalindromeSubseq("bbbab");
    }
}
