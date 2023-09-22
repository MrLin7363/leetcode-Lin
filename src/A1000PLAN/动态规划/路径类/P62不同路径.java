package A1000PLAN.动态规划.二维表格类.路径类;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/19
 **/
public class P62不同路径 {
    /*
    只能往下，往右移动到最右下角
    dp定义： dp[i][j] 到达方格的路径数
    转移方程：dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    初始化：dp[0][i]=1,dp[i][0]=1
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 初始化
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int j = 0; j < m; j++) {
            dp[j][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
    依靠i-1和j-1可以化为一维DP
     */
    public int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
