package Array.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/5
  *@Describe: 多次交易，购买前要卖出之前买入的股票
  1.
  2. 峰谷法
  3. one pass贪心
 */

public class Buy_and_Sell_Stock_II_122 {

    /*
    1. DP +二维数组
     */
    public static int maxProfit4(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // 0：卖出
        // 1：买入
        // 状态转移：0 → 1 → 0 → 1 → 0 → 1 → 0
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            // 这两行调换顺序也是可以的
            // 第i天卖出=max( 第i-1天卖出=不动  , 第i-1天买入+ 当天价格 )
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第i天买入=max ( 第i-1天买入=不动 ， 第i-1天买入 - 当天价格)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }
    // DP+空间优化
    public static int maxProfit6(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int first=0; // 不买
        int second=-prices[0]; // 买
        for (int i = 1; i < len; i++) {
            int temp=first;
            first=Math.max(first,second+prices[i]);
            second=Math.max(second,temp-prices[i]);
        }
        return Math.max(first,second);
    }

    public static void main(String[] args) {
        maxProfit5(new int[]{7,1,5});
    }
    /*
    2.峰谷法
     */
    public int maxProfit2(int[] prices) {
        int valley=prices[0];
        int peak=prices[0];
        int i=0;
        int maxprofit=0;
        while (i<prices.length-1){
            // 找谷
            while (i<prices.length&& prices[i]<=prices[i+1])
                i++;
            valley=prices[i];
            // 找峰
            while (i<prices.length && prices[i]>=prices[i+1])
                i++;
            peak=prices[i];
            maxprofit+=peak-valley;
        }
        return maxprofit;
    }
    /*
    one pass 贪心算法
     */
    public int maxProfit(int[] prices) {
        int maxprofit=0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i]>prices[i-1])
                maxprofit+=prices[i]-prices[i-1];
        }
        return maxprofit;
    }

    /*
    暴力回溯法，不推荐-超时
     */
    public static int maxProfit5(int[] prices) {
        if(prices==null || prices.length==0) {
            return 0;
        }
        return dfs(prices,0,false);
    }

    private static int dfs(int[] prices,int index,boolean status) {
        if(index==prices.length) {
            return 0;
        }
        //定义三个变量，分别记录[不动]、[卖] [买]
        int a=0,b=0,c=0;
        a=dfs(prices,index+1,status);
        if (status){
            b=dfs(prices,index+1,false)+prices[index]; // 卖
        }else {
            c=dfs(prices,index+1,true)-prices[index]; // 买 715
        }
        return Math.max(Math.max(a,b),c);
    }

}
