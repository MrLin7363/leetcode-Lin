package A1000PLAN.动态规划.TrueFalse;

/**
 *desc:
 *@author lin
 *@since 2023/11/16
 **/
public class P97交错字符串 {
    /*
    DP定义：dp[i,j] s1前i个字符和s2前j个字符能否交错形成 s3前i+j的字符
    转移方程：dp[i][j]= (dp[i-1][j]&&s1[i]==s3[i+j-1]) || (dp[i][j-1]&&s2[j]==s3[i+j-1])
    遍历顺序：
    初始化：
    结果返回:dp[n,m]
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int len = s3.length();
        if (n + m != len) {
            return false;
        }

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        // 初始化第一行和第一列
        // s1无字符
        for (int i = 1; i <= m; i++) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        // s2无字符
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int p = i + j - 1;
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p))
                    || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        new P97交错字符串().isInterleave("aabcc", "dbbca", "aadbbcbcac");
    }
}
