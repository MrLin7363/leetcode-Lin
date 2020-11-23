package DynamicProgramming.easy.Medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe: 零钱兑换比2稍微复杂
  最少硬币数
  94+89
 */

import java.util.Arrays;

public class P322Coin_Change {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = amount + 1;
        Arrays.fill(dp, max); // 一般Integer.MaxValue
        dp[0] = 0;
        // 这里的 coin 循环和 amount 循环互换都行，按照公式coin再外层效率更高
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i - coin >= 0)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); // 注意+1
            }
        }
        System.out.println(dp[amount]);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        coinChange(new int[]{2},4);
    }
}
