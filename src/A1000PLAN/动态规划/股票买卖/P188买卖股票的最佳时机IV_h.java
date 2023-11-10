package A1000PLAN.动态规划.股票买卖;

/**
 * desc: https://programmercarl.com/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92-%E8%82%A1%E7%A5%A8%E9%97%AE%E9%A2%98%E6%80%BB%E7%BB%93%E7%AF%87.html#%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAiv
 *
 * @author Lin
 * @since 2023/10/31
 **/
public class P188买卖股票的最佳时机IV_h {
    /*
    DP + 二维
    状态： 0:不操作 -> 1:第一次买入 -> 2:第一次卖出 ->3:第二次买入 ->4:第二次卖出 ->5:第三次买入 ......
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // 天数,状态
        // 比如k==2,状态有5种,+1是初始状态   除0以外，偶数卖出，奇数买入
        int[][] dp = new int[n][2 * k + 1];

        // 初始化第一次买入的股票
        for (int i = 1; i < 2 * k; i += 2) {
            dp[0][i] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2 * k - 1; j += 2) {
                // 买入 = max  前一天持有股票不动 / 前一天无股票，今天买入
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                // 卖出 = max 前一天卖出不动，无股票 / 前一天有股票，今天卖出
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        return dp[n - 1][2 * k];
    }

    /*
    DP + 一维
    状态： 0:不操作 -> 1:第一次买入 -> 2:第一次卖出 ->3:第二次买入 ->4:第二次卖出 ->5:第三次买入 ......
    */
    public int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        // 状态:第k次操作
        int[] dp = new int[2 * k + 1];

        // 初始化第一次买入的股票
        for (int i = 1; i < 2 * k; i += 2) {
            dp[i] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2 * k - 1; j += 2) {
                // 买入 = max  前一天持有股票不动 / 前一天无股票，今天买入
                dp[j + 1] = Math.max(dp[j + 1], dp[j] - prices[i]);
                // 卖出 = max 前一天卖出不动，无股票 / 前一天有股票，今天卖出
                dp[j + 2] = Math.max(dp[j + 2], dp[j + 1] + prices[i]);
            }
        }
        return dp[2 * k];
    }
}
