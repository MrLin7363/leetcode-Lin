package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe:
 */

import java.util.HashMap;
import java.util.Map;

public class Buy_and_Sell_Stock_with_Cooldown_309 {

    /*
    DP + 二维数组(三种状态)  55+9
     */
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if (prices == null || n < 2)
            return 0;
        int[][] dp = new int[n][3];
        dp[0][0] = 0; // 卖出
        dp[0][1] = -prices[0]; // 买入
        dp[1][0] = 0; // 冷冻期
        for (int i = 1; i < n; i++) {
            // 第i天卖出=max( 第i-1天卖出=不动  , 第i-1天买入+ 当天价格 )
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第i天买入=max ( 第i-1天买入=不动 ， 第i-1天冷冻期 - 当天价格)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] - prices[i]);
            // 第i天冷冻期=第i-1天卖出最大收益
            dp[i][2] = dp[i - 1][0];
        }
        // 最后一天买入实际上无意义，所以只比较冷冻期和卖出
        return Math.max(dp[n-1][0],dp[n-1][2]);
    }
    /*
   DP + 空间优化  100+9
    */
    public int maxProfit2(int[] prices) {
        int n=prices.length;
        if (prices == null || n < 2)
            return 0;
        int dp0 = 0; // 卖出
        int dp1 = -prices[0]; // 买入
        int dp2 = 0; // 冷冻期
        for (int i = 1; i < n; i++) {
            int temp=dp0;
            // 第i天卖出=max( 第i-1天卖出=不动  , 第i-1天买入+ 当天价格 )
            dp0 = Math.max(dp0, dp1 + prices[i]);
            // 第i天买入=max ( 第i-1天买入=不动 ， 第i-1天冷冻期 - 当天价格)
            dp1 = Math.max(dp1, dp2 - prices[i]);
            // 第i天冷冻期=第i-1天卖出最大收益
            dp2 = temp;
        }
        // 最后一天买入实际上无意义，所以只比较冷冻期和卖出
        return Math.max(dp0,dp2);
    }
    public static void main(String[] args) {
        maxProfitDG(new int[]{1,4,2,5,2});
    }
    /*
    递归剪枝优化版本  17+4
     */
        public static int maxProfitDG(int[] prices) {
            Map<Pair,Integer> map = new HashMap<Pair,Integer>();
            return dfs(map,prices,0,0);
        }

        private static int dfs(Map<Pair,Integer> map,int[] prices,int index,int status) {
            if(index>=prices.length) {
                return 0;
            }
            //Pair对象封装了index和status，作为map的key
            Pair p = new Pair(index,status);
            if(map.containsKey(p)) {
                return  map.get(p); // 返回该天买或不动的最大收益，不再深入递归
            }
            int a=0,b=0,c=0; // 不动，卖，买
            a = dfs(map,prices,index+1,status);
            if(status==1) {
                b = dfs(map,prices,index+2,0)+prices[index]; // 卖出 +2 跳过冷冻期
            } else {
                c = dfs(map,prices,index+1,1)-prices[index];
            }
            map.put(p,Math.max(Math.max(a,b),c)); // 插入最大收益
            return map.get(p);
        }
        //自定义一个Pair类，封装 index和status，代表第index天的持有或者不持有的最大利润，如果该天已算出，不用再往下遍历
        private static class Pair {
            private int index;
            private int status;
            Pair(int index,int status) {
                this.index = index;
                this.status = status;
            }
            //这里需要实现自定义的equals和hashCode函数
            // map.containsKey(p) 会先比较查找是否有此hashcode，如果有则进行equals方法比较
            // 注意方法名不能写错,大小写都是，这个是作为一个类的特有方法，和toString()一样
            public int hashCode() {
                return this.index+this.status;
            }
            public boolean equals(Object obj) {
                Pair other = (Pair)obj;
                if(this.index==other.index && this.status==other.status) {
                    return true;
                }
                return false;
            }
        }
}
