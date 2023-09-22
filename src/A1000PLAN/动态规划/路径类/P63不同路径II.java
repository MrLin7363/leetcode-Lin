package A1000PLAN.动态规划.二维表格类.路径类;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/19
 **/
public class P63不同路径II {
    /*
    只能往下，往右移动到最右下角，但是遇到 =1 是障碍物
    dp定义： dp[i][j] 到达方格的路径数
    转移方程：dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    初始化：没有障碍物的情况初始化第一行第一列 dp[0][i]=1,dp[i][0]=1
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 初始化
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int j = 0; j < m; j++) {
            if (obstacleGrid[j][0] == 1) {
                break;
            }
            dp[j][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // 一维DP
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        // 初始化
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (j >= 1) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        new P63不同路径II().uniquePathsWithObstacles2(new int[][] {{0}, {1}});
        new P63不同路径II().uniquePathsWithObstacles2(new int[][] {{1}, {0}});
    }
}
