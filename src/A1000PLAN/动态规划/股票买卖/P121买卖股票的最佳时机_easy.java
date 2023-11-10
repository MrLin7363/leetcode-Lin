package A1000PLAN.动态规划.股票买卖;

/**
 * desc: 股票问题推荐  全部用 DP
 *
 * @author Lin
 * @since 2023/10/30
 **/
public class P121买卖股票的最佳时机_easy {
    /* https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solutions/476791/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
    DP定义： dp[i] [0,i]买卖的最大收益
    转移方程：状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润   没有股票/卖状态
            dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润（ i 从 0 开始）  有股票/买状态
            1. dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，即 dp[i−1][0]，
                或者前一天结束的时候手里持有一支股票，即 dp[i−1][1]，这时候我们要将其卖出，并获得 prices[i] 的收益
            2.dp[i][1] = Math.max(dp[i - 1][1],  - prices[i]);
                按照同样的方式考虑转移状态，那么可能的转移状态为前一天已经持有一支股票，即 dp[i−1][1]，
                或者一直没有买入股票，第一次买入
    初始化：  dp[0][0] = 0; // 卖    dp[0][1] = -prices[0]; // 买
    遍历顺序：
    返回结果：
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0; // 卖
        dp[0][1] = -prices[0]; // 买
        for (int i = 1; i < n; i++) {
            // 前一天没有股票/ 前一天有股票，今天卖掉
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 前一天有股票/  今天买入     相当于 1. 买入不动  2.第一次买入
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]); // 注意这里不能算前一天卖出的收益，因为只能买一次
        }
        // 第i天交易完成手里没有股票的收益
        return dp[n - 1][0];
    }

    // 贪心：找最低点的同时记录最大值
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int minprice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minprice);
            }
        }
        return maxProfit;
    }
}
