package Array.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/11/05 0:06
 * @Describe: DP历史最低点是动态变化的
 *
 */
public class P121买卖股票的最佳时机 {
    // 贪心
    public int maxProfit(int[] prices) {
        int minprice=Integer.MAX_VALUE;
        int maxprofit=0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i]<minprice){ // 价格比历史最低点小
                minprice=prices[i];
            }else if (prices[i]-minprice>maxprofit){ // 价格差比最大收益还要大
                maxprofit=prices[i]-minprice;
            }
        }
        return maxprofit;
    }
    // DP
    public int maxProfitDP(int[] prices) {
        if(prices==null || prices.length==0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i=1;i<n;++i) {
            //第i天卖出收益 = max(第i-1天卖出收益，第i-1天买入收益+当天股价)
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            //第i天买入收益 = max(第i-1天买入收益，-当天股价)   相当于 1. 买入不动 2.当天开始买入
            dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
        }
        return dp[n-1][0];
    }
}
