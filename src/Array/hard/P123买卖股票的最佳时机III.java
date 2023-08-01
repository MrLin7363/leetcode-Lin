package Array.hard;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe: 只能交易两次
  1.递归优化 DFS  5+5
  2.DP+三维数组  5+5
  3.DP+二维数组  12+5
  4.DP+空间优化 在二维数组基础上，这里不做代码
 */

import java.util.HashMap;
import java.util.Map;

public class P123买卖股票的最佳时机III {

    /*
     DP + 二维数组
     */
    public int maxProfit3(int[] prices) {
        int n=prices.length;
        if (prices == null || n < 2)
            return 0;
        // 天数，0-4代替：初始，买1，卖1，买2，卖2
        int[][] dp = new int[n][5];
        // 初始化第1天
        dp[0][0]=0; // 初始状态
        dp[0][1] = -prices[0]; // 第一次买入
        dp[0][2]= 0 ; // 第一次卖出
        dp[0][3]=-prices[0]; // 第二次买入
        dp[0][4] = 0; // 第二次卖出
        for (int i = 1; i < n; i++) {
            // 第1次买入= max ( 第1次买入=不动，初始状态-当天价格=根本没买过 )
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            dp[i][2]=Math.max( dp[i-1][2],dp[i-1][1]+prices[i] );
            dp[i][3]=Math.max( dp[i-1][3],dp[i-1][2]-prices[i] );
            dp[i][4]=Math.max( dp[i-1][4],dp[i-1][3]+prices[i] );
        }
        // 返回最大值,上面五个数的最大值
        int a = Math.max(dp[n-1][1],dp[n-1][2]);
        int b = Math.max(dp[n-1][3],dp[n-1][4]);
        return Math.max(a,b);// max(a,b)
    }

    /*
    DP + 三维数组  + 下面的初始状态可以和之前的一样，用卖+买替代，如上面的方法DP+二维
     */
    public int maxProfit2(int[] prices) {
        int n=prices.length;
        if (prices == null || n < 2)
            return 0;
        // 天数，交易次数, 1买入 0卖出，
        int[][][] dp = new int[n][3][2];
        // 初始化第1天
        dp[0][0][0] = 0; // 初始状态
        dp[0][0][1] = -prices[0]; // 第一次买入，交易还未变成1次
        dp[0][1][0]= 0 ; // 第一次卖出时，交易为1次
        dp[0][1][1]=-prices[0]; // 第二次买入时
        dp[0][2][0] = 0; // 第二次卖出
        for (int i = 1; i < n; i++) {
            //dp[i][0][0]相当于初始状态，它只能从初始状态转换来,初始状态不动
            dp[i][0][0] = dp[i-1][0][0];
            // 第一次买入=max ( 第i-1天第一次买入不动 ，  初始状态 - 当天价格)
            dp[i][0][1]=Math.max( dp[i-1][0][1], dp[i-1][0][0]-prices[i] );
            // 第一次卖出=max( 第i-1天第一次卖出不动， 第i-1天第一次买入+当天价格)
            dp[i][1][0]=Math.max( dp[i-1][1][0], dp[i-1][0][1]+prices[i] );
            //  第二次买入=max ( 第i-1天第二次买入=不动 ， 第i-1天第一次卖出 - 当天价格)
            dp[i][1][1]=Math.max( dp[i-1][1][1], dp[i-1][1][0]-prices[i]);
            // 第二次卖出=max( 第i-1天第一次卖出=不动  , 第i-1天第二买入+ 当天价格 )
            dp[i][2][0]=Math.max( dp[i-1][2][0], dp[i-1][1][1]+prices[i]);
        }
        // 返回最大值,上面五个数的最大值,到了最后一天都不买就是初始状态，这都可能是最大收益
        int a = Math.max(dp[n-1][0][0],dp[n-1][0][1]);//初始状态一直不买和第一次买比
        int b = Math.max(dp[n-1][1][0],dp[n-1][1][1]);//第一次卖和第二次买比
        return Math.max(Math.max(a,b),dp[n-1][2][0]);// max(a,b)和第二次卖出比
    }

    /*
    递归
     */
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if(prices==null || n==0) {
            return 0;
        }
        Map<Key,Integer> map=new HashMap<>();
        return dfs(map,prices,0,0,0);
    }
    // k交易次数
    private int dfs(Map<Key,Integer> map,int[] prices,int index,int status,int k){
        if (index==prices.length || k==2)
            return 0;
        Key key=new Key(index,status,k);
        if (map.containsKey(key))
            return map.get(key);
        int a=0,b=0,c=0;// 不动，卖，买
        a=dfs(map,prices,index+1,status,k);
        if (status==1){
            b=dfs(map,prices,index+1,0,k+1)+prices[index];
        }else{
            c=dfs(map,prices,index+1,1,k)-prices[index];
        }
        map.put(key,Math.max(Math.max(a,b),c));
        return map.get(key);
    }
    private class Key{
         int index; // 天数
         int status; // 买 卖
         int tradeTimes;// 交易次数
        Key(int index,int status,int tradeTimes){
            this.index=index;
            this.status=status;
            this.tradeTimes=tradeTimes;
        }
        // 注意方法名不能写错,大小写都是，这个是作为一个类的特有方法，和toString()一样
        public int hashCode(){
            return this.index+this.status+this.tradeTimes;
        }
        public boolean equals(Object o){
            Key other=(Key)o;
            return this.index==other.index && this.status==other.status && this.tradeTimes==other.tradeTimes;
        }
    }
}
