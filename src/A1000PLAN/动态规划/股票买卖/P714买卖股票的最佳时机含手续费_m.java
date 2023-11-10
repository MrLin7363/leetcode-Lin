package A1000PLAN.动态规划.股票买卖;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/30
 **/
public class P714买卖股票的最佳时机含手续费_m {
    // 买入的时候交手续费
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0; // 卖
        dp[0][1] = -prices[0] - fee; // 买
        for (int i = 1; i < n; i++) {
            // 今天卖出：前一天没有股票/ 前一天有股票，今天卖掉
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 今天买入：前一天有股票/  前一天没有股票,今天买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        // 第i天交易完成手里没有股票的收益
        return dp[n - 1][0];
    }

    // 卖出的时候交手续费
    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0; // 卖
        dp[0][1] = -prices[0]; // 买
        for (int i = 1; i < n; i++) {
            // 今天卖出：前一天没有股票/ 前一天有股票，今天卖掉
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            // 今天买入：前一天有股票/  前一天没有股票,今天买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 第i天交易完成手里没有股票的收益
        return dp[n - 1][0];
    }
}
