package DynamicProgramming.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe: 零钱兑换比2稍微复杂
  最少硬币数

 */

import java.util.Arrays;

public class P322Coin_Change {
    /*
    动态规划最强版：自底向上 94+89
     */
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
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /*
    动态规划：自上而下 84+79
     */
    public static int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = amount + 1;
        Arrays.fill(dp, max); // 一般Integer.MaxValue
        dp[0] = 0;
        // 这里的 coin 循环和 amount 循环互换都行，按照公式coin再外层效率更高
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); // 注意+1
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /*
    记忆化递归  14+68
     */
    public static int coinChange3(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }
    // count 备忘录
    private static int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        // 记忆的数组
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 需要硬币数
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
            System.out.println("res="+res);
        }
        //  如果最后都没有找到合适的硬币数则返回-1
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        System.out.println("coutn["+(rem-1)+"]="+count[rem-1]);
        return count[rem - 1];
    }

    public static void main(String[] args) {
        coinChange3(new int[]{1,2,3},6);
    }
}
