package A1000PLAN.动态规划.二维表格类;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/6
 **/
public class P221最大正方形 {
    /*
    dp定义：dp[i][j]标识以(i,j)为右下角的只包含1正方形的最大边长
    转移方程： value=0: dp=0
            value=1 : dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1  三者最小
    初始化： i==0||j==0 且value=1时 dp=1
    返回: 全局max记录最大边长
     */
    public int maximalSquare(char[][] matrix) {
        int ans = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans * ans;
    }
}
