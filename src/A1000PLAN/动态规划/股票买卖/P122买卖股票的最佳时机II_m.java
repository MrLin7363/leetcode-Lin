package A1000PLAN.动态规划.股票买卖;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/30
 **/
public class P122买卖股票的最佳时机II_m {
    /* https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solutions/476791/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
    DP定义： dp[i] [0,i]买卖的最大收益
    转移方程：状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润   没有股票/卖状态
        dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润（ i 从 0 开始）  有股票/买状态
        1. dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，即 dp[i−1][0]，
            或者前一天结束的时候手里持有一支股票，即 dp[i−1][1]，这时候我们要将其卖出，并获得 prices[i] 的收益
        2.dp[i][1] = Math.max(dp[i - 1][1], dp[i][0] - prices[i]);
            按照同样的方式考虑转移状态，那么可能的转移状态为前一天已经持有一支股票，即 dp[i−1][1]，
            或者前一天结束时还没有股票，即 dp[i−1][0]，这时候我们要将其买入，并减少 prices[i] 的收益
    初始化：        dp[0][0] = 0; // 卖    dp[0][1] = -prices[0]; // 买
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
            // 前一天有股票/  前一天没有股票，今天买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 第i天交易完成手里没有股票的收益
        return dp[n - 1][0];
    }

    // DP+空间优化
    public static int maxProfit6(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int first = 0; // 卖
        int second = -prices[0]; // 买
        for (int i = 1; i < len; i++) {
            int temp = first;
            first = Math.max(first, second + prices[i]);
            second = Math.max(second, temp - prices[i]);
        }
        return Math.max(first, second);
    }

    // 贪心：找到每一个上坡路，也就是低点 ; 每段上坡路其实是每个 i-1,i 区间的相加， 只要是正数即可
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
