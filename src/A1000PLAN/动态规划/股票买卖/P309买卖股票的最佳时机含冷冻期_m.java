package A1000PLAN.动态规划.股票买卖;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/30
 **/
public class P309买卖股票的最佳时机含冷冻期_m {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = 0; // 卖
        dp[0][1] = -prices[0]; // 买
        dp[0][2] = 0; // 冷冻期
        for (int i = 1; i < n; i++) {
            // 今天卖出：前一天没有股票/ 前一天有股票，今天卖掉
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 今天买入：前一天有股票/  前一天冷冻期，今天买入  只有前一天是冷冻期才买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] - prices[i]);
            // 今天冷冻期： 前一天股票卖出了/无股票
            dp[i][2] = dp[i - 1][0];
        }
        // 第i天交易完成手里没有股票的收益
        return Math.max(dp[n - 1][0], dp[n - 1][2]);
    }

    // DP空间优化
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int dp0 = 0; // 卖
        int dp1 = -prices[0]; // 买
        int dp2 = 0; // 冷冻期
        for (int i = 1; i < n; i++) {
            int temp = dp0;
            // 今天卖出：前一天没有股票/ 前一天有股票，今天卖掉
            dp0 = Math.max(dp0, dp1 + prices[i]);
            // 今天买入：前一天有股票/  前一天冷冻期，今天买入  只有前一天是冷冻期才买入
            dp1 = Math.max(dp1, dp2 - prices[i]);
            // 今天冷冻期： 前一天股票卖出了
            dp2 = temp;
        }
        // 第i天交易完成手里没有股票的收益
        return Math.max(dp0, dp2);
    }
}
