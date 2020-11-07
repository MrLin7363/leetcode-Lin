package Array.hard;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe:
  参考123 ， 这里只写    DP+空间优化（滚动数组）
 */

public class Buy_and_Sell_Stock_IV_188 {
    public int maxProfit3(int k,int[] prices) {
        if(prices==null || prices.length==0) {
            return 0;
        }
        int n = prices.length;
        //当k非常大时转为无限次交易 122 题
        if (k>=n/2) {
            int dp0 = 0, dp1 = -prices[0];
            for (int i = 0; i < n; i++) {
                int temp = dp0;
                dp0 = Math.max(dp0, dp1 + prices[i]);
                dp1 = Math.max(dp1, temp - prices[i]);
            }
            return Math.max(dp0,dp1);
        }
        //定义二维数组，交易了多少次、当前的买卖状态
        int[][] dp=new int[k+1][2];
        //  初始化每一次的买入卖出
        for (int i = 0; i <= k; i++) {
            dp[i][0]=0; // 卖=0
            dp[i][1]=-prices[i]; // 买
        }
        // 遍历天数
        for (int i = 1; i < n; i++) {
            // 遍历第几次交易
            for (int j = k; j >0; --j) {
                //处理第k次买入,0是第一次买入
                dp[j-1][1]=Math.max( dp[j-1][1],dp[j-1][0]-prices[i] );
                //处理第k次卖出
                dp[j][0]=Math.max( dp[j][0],dp[j-1][1]+prices[i] );
            }
        }
        // 第k次卖出
        return dp[k][0];
    }

}
