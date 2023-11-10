package A1000PLAN.动态规划.股票买卖;

import java.util.Arrays;

/**
 * desc:
 * https://programmercarl.com/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92-%E8%82%A1%E7%A5%A8%E9%97%AE%E9%A2%98%E6%80%BB%E7%BB%93%E7%AF%87.html#%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAiii
 *
 * @author Lin
 * @since 2023/10/30
 **/
public class P123买卖股票的最佳时机III_h {
    /*
    DP + 5个常量/一维数组
    */
    public int maxProfit4(int[] prices) {
        int n = prices.length;
        int[] dp = new int[5];
        dp[0] = 0;
        dp[1] = -prices[0];
        dp[2] = 0;
        dp[3] = -prices[0];
        dp[4] = 0;
        for (int i = 1; i < n; i++) {
            dp[0] = dp[0];
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
            dp[2] = Math.max(dp[2], dp[1] + prices[i]);
            dp[3] = Math.max(dp[3], dp[2] - prices[i]);
            dp[4] = Math.max(dp[4], dp[3] + prices[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /*
     DP + 二维数组
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        if (prices == null || n < 2) {
            return 0;
        }
        // 天数，0-4代替：初始，买1，卖1，买2，卖2
        int[][] dp = new int[n][5];
        // 初始化第1天
        dp[0][0] = 0; // 初始状态
        dp[0][1] = -prices[0]; // 第一次买入
        dp[0][2] = 0; // 第一次卖出
        dp[0][3] = -prices[0]; // 第二次买入
        dp[0][4] = 0; // 第二次卖出
        for (int i = 1; i < n; i++) {
            // 第1次买入= max ( 第1次买入=不动，初始状态-当天价格=根本没买过 )
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        // 返回最大值,上面五个数的最大值
        int a = Math.max(dp[n - 1][1], dp[n - 1][2]);
        int b = Math.max(dp[n - 1][3], dp[n - 1][4]);
        return Math.max(a, b);// max(a,b)
    }

    /*
    DP + 三维数组  + 下面的初始状态可以和之前的一样，用卖+买替代，如上面的方法DP+二维
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (prices == null || n < 2) {
            return 0;
        }
        // 天数，交易次数, 1买入 0卖出，
        int[][][] dp = new int[n][3][2];
        // 初始化第1天
        dp[0][0][0] = 0; // 初始状态
        dp[0][0][1] = -prices[0]; // 第一次买入，交易还未变成1次
        dp[0][1][0] = 0; // 第一次卖出时，交易为1次
        dp[0][1][1] = -prices[0]; // 第二次买入时
        dp[0][2][0] = 0; // 第二次卖出
        for (int i = 1; i < n; i++) {
            //dp[i][0][0]相当于初始状态，它只能从初始状态转换来,初始状态不动
            dp[i][0][0] = dp[i - 1][0][0];
            // 第一次买入=max ( 第i-1天第一次买入不动 ，  初始状态 - 当天价格)
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][0][0] - prices[i]);
            // 第一次卖出=max( 第i-1天第一次卖出不动， 第i-1天第一次买入+当天价格)
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][0][1] + prices[i]);
            //  第二次买入=max ( 第i-1天第二次买入=不动 ， 第i-1天第一次卖出 - 当天价格)
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][1][0] - prices[i]);
            // 第二次卖出=max( 第i-1天第一次卖出=不动  , 第i-1天第二买入+ 当天价格 )
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][1][1] + prices[i]);
        }
        // 返回最大值,上面五个数的最大值,到了最后一天都不买就是初始状态，这都可能是最大收益
        int a = Math.max(dp[n - 1][0][0], dp[n - 1][0][1]);//初始状态一直不买和第一次买比
        int b = Math.max(dp[n - 1][1][0], dp[n - 1][1][1]);//第一次卖和第二次买比
        return Math.max(Math.max(a, b), dp[n - 1][2][0]);// max(a,b)和第二次卖出比
    }
}
