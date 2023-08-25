package A1000PLAN.动态规划;

/**
 * desc:完全背包+组合问题
 *
 * @author Lin
 * @since 2023/8/22
 **/
public class P518零钱兑换II {
    /*
    一维数组：  dp[i]=dp[i]+dp[i-coins[i]     dp[i]硬币凑成金额i的组合数
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j >= coins[i]) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        new P518零钱兑换II().change(5, new int[] {1, 2, 5});
        new P518零钱兑换II().change3(5, new int[] {1, 2, 5});
        new P518零钱兑换II().change2(5, new int[] {1, 2, 5});
    }

    /*
    二维数组 dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
     */
    public int change2(int amount, int[] coins) {
        // 使用前i号个硬币，凑出零钱j的组合数
        int[][] dp = new int[coins.length][amount + 1];
        // 初始化amount=0;哪个硬币都不用都是一种方式
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }
        // 初始化 第一枚硬币只要能整除都算一种组合方式
        for (int i = 1; i < amount + 1; i++) {
            if (i % coins[0] == 0) {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                // 当前金额上一个硬币组合数 + 当前硬币金额为j-coins[i]的组合数
                if (j >= coins[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                } else {
                    // 当前硬币无法组合，取上一个硬币的组合数
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length - 1][amount];
    }

    /*
    二维数组，n+1枚硬币
     */
    public int change3(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j] 表示使用前i个硬币，凑出零钱j的硬币组合数
        // 初始化为n+1表示0枚硬币的时候组合数为0，因为公式要从上一枚硬币来，所以默认第一行为0
        int[][] dp = new int[n + 1][amount + 1];
        // base case dp[0][j] = 0; dp[i][0] = 1;
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // amount为0时，哪个硬币都不用也是一种方式
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                // dp[i-1][j-coins[i-1]]将当前硬币装入背包, dp[i-1][j]代表不装入
                // coins[i-1] i=1的时候指第一枚硬币
                if (j - coins[i - 1] >= 0) { // 防止下标越界
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    // 当前硬币无法组合，取上一个硬币的组合，如果是第一枚硬币，那么上一枚硬币的组合为0
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount];
    }
}
