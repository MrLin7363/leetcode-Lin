package A1000PLAN.动态规划.子序列;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/27
 **/
public class P1143最长公共子序列 {
    /*
    DP定义：dp[i][j] 下标[0,i]text1与下标范围[0,j]text2的最长公共子序列长度
    转移方程： test[i]==test[j] : dp[i][j]=dp[i-1][j-1]+1
            test[i]!=test[j] :  dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
    初始化：
    遍历顺序: 依赖于-1,->  i正序,j正序
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        new P1143最长公共子序列().longestCommonSubsequence("abcde", "ace");
    }
}
