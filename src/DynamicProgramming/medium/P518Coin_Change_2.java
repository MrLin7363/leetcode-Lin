package DynamicProgramming.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/22
  *@Describe: 组合数
 */


public class P518Coin_Change_2 {
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x < amount + 1; ++x) {
                dp[x] += dp[x - coin];
                System.out.println("dp["+x+"]="+dp[x]);
            }
        }
        System.out.println(dp[amount]);
        return dp[amount];
    }

    public static void main(String[] args) {
        change(6,new int[]{2,3});
    }
}
