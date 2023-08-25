package A1000PLAN.动态规划;

import java.util.Arrays;

/**
 * desc: 完全背包+最大最小问题
 *
 * @author Lin
 * @since 2023/8/17
 **/
public class P332零钱兑换 {
    /*
    完全背包-最少硬币数，每个硬币可以无限取   dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
    一维数组-先遍历硬币
     */
    public int coinChange(int[] coins, int amount) {
        // 1.确定dp数组定义，dp[i]是凑成金额i所需要的最少硬币数
        int[] dp = new int[amount + 1];
        // 2.确定初始化，金额0需要硬币0，dp[0]=0无需初始化,其他为MAX/或者硬币数
        for (int i = 1; i < amount + 1; i++) {
            if (i % coins[0] == 0) {
                dp[i] = i / coins[0];
            } else {
                dp[i] = Integer.MAX_VALUE;
            }
        }

        // 3.确定推导公式  dp[i]= Math.min(dp[i-1],dp[i-coins[k]]);
        // 4.确定遍历顺序，先遍历coins
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                // 需要判断 !=MAX 否则 MAX+1=MIN 会出问题
                if (coins[i] <= j && dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /*
    一维数组-先遍历金额
     */
    public int coinChange4(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /*
   （1）dp[i][j]含义： 在总额为j的前提下，从coins[0]-coins[i]中选择硬币，可用最少硬币组成的j的硬币个数。

    （2）递推公式： dp[i][j] = min(dp[i - 1][j], dp[i][j - coins[i]]+1)，完全背包递推公式取最小值，不取当前硬币/取当前硬币=能构成金额j-coin[i]的最少硬币数+1(最少硬币数需要!=MAX才合理)

    （3）dp数组初始化： 因为要求的是最小值，因此dp[i][0] = 0, 表示为总价值为0时什么都不选，其余都初始化为INT_MAX。当只有找当j - coins[i]为0的数，
    才会因为min()更新，如果减去的不为0，或者减去到之前没有被更新过的数，则还会保持INT_MAX

    （4）遍历顺序： 因为只要找到某种最小值的方案取的是MIN，因此先背包后物品，先物品后背包都可以

    https://blog.csdn.net/qq_44879358/article/details/119719001
     */
    public int coinChange2(int[] coins, int amount) {
        // 1.确定dp数组定义，dp[i][j]凑成金额j,需要的最少硬币数i
        int[][] dp = new int[coins.length][amount + 1];
        // 2.确定初始化，金额0需要硬币0，第一列都为0; 第一行则是0号硬币能否凑成金额i,不能的话因为是求最小值，所以初始化为MAX
        for (int i = 1; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[0][i] = i / coins[0];
            } else {
                dp[0][i] = Integer.MAX_VALUE;
            }
        }
        // 3.确定推导公式  Math.min(dp[i - 1][j], dp[i][j - coins[i]] + 1);
        // 4.遍历顺序
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                // 前提要判断之前的金额是否能由硬币凑成
                if (j >= coins[i] && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                    // 取当前硬币 = 能构成金额j-coin[i]的最少硬币数+1
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i]] + 1);
                } else {
                    // 不取当前硬币
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length - 1][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length - 1][amount];
    }

    public static void main(String[] args) {
        new P332零钱兑换().coinChange2(new int[] {1, 2, 5}, 11);
        new P332零钱兑换().coinChange3(new int[] {1, 2, 5}, 11);
        new P332零钱兑换().coinChange(new int[] {1, 2, 5}, 11);
        new P332零钱兑换().coinChange4(new int[] {1, 2, 5}, 11);
    }

    /*
    采用coins+1的硬币数，第一行默认都是MAX，不好理解/省去了初始化- 不用看
     */
    public int coinChange3(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i - 1] && dp[i][j - coins[i - 1]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[coins.length][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length][amount];
    }
}
