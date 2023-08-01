package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe:
  1.递归优化版  5+5
  2.DP空间优化版 92+5
 */

import java.util.HashMap;
import java.util.Map;

public class P714买卖股票的最佳时机含手续费 {

    public int maxProfitDP(int[] prices, int fee) {
        int n=prices.length;
        if (prices == null || n < 2)
            return 0;
        int dp0 = 0; // 卖出
        int dp1 = -prices[0]; // 买入
        for (int i = 1; i < n; i++) {
            int temp=dp0;
            // 第i天卖出=max( 第i-1天卖出=不动  , 第i-1天买入+ 当天价格-费用 )
            dp0 = Math.max(dp0, dp1 + prices[i]-fee);
            // 第i天买入=max ( 第i-1天买入=不动 ， 第i-1天卖出 - 当天价格)
            dp1 = Math.max(dp1, temp - prices[i]);
        }
        return Math.max(dp0,dp1);
    }
    /*
    1.递归优化版  5+5
     */
    public static int maxProfit(int[] prices, int fee) {
        Map<Pair,Integer> hashMap=new HashMap<Pair,Integer>();
        int res= dfs(hashMap,prices,0,0,fee);
        System.out.println(res);
        return res;
    }
    // status 0 ：买入  1：卖出
    private static int dfs(Map<Pair,Integer> hashMap,int[] prices , int index ,int status, int fee){
        if (index==prices.length)
            return 0;
        // 将每一天和每一天的交易状态作为一个对象
        Pair key=new Pair(index,status);
        // 剪枝
        if (hashMap.containsKey(key))
            return hashMap.get(key);
        int a=0,b=0,c=0;// 不动，卖，买
        // 不动
        a=dfs(hashMap,prices,index+1,status,fee);
        if (status==1){
            //递归处理卖的情况，卖的时候会有一个手续费
            // 卖出=前一天买入+当天价格
            b=dfs(hashMap,prices,index+1,0,fee)+prices[index]-fee;
        }else{
            // 买入=前一天卖出-当天价格-手续费
            c=dfs(hashMap,prices,index+1,1,fee)-prices[index];
        }
        // 最大收益
        hashMap.put(key,Math.max(Math.max(a,b),c));
        // 处理当天三种情况最大值
        return hashMap.get(key);
    }
    private static class Pair{
        private int index;
        private int status;
        Pair(int index,int status){
            this.index=index;
            this.status=status;
        }
        // 注意方法名不能写错,大小写都是，这个是作为一个类的特有方法，和toString()一样
        public int hashCode(){
            return this.index+this.status;
        }
        public boolean equals(Object o){
            Pair other=(Pair)o;
            return this.index==other.index && this.status==other.status;
        }
    }

    public static void main(String[] args) {
        maxProfit(new int[]{1,4,2,5,2},2);
    }
}
