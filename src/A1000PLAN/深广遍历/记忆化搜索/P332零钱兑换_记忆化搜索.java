/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2023-2023. All rights reserved.
 */

package A1000PLAN.深广遍历.记忆化搜索;

import java.util.Arrays;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/17
 **/
public class P332零钱兑换_记忆化搜索 {
    /*
    记忆化搜索  S(i)=金额i最少的硬币数,递归的过程中很多次会碰到相同的S(i)
    https://leetcode.cn/problems/coin-change/solutions/132979/322-ling-qian-dui-huan-by-leetcode-solution/
     */
    public int coinChange(int[] coins, int amount) {
        // 最大值
        int i = minCoins(coins, amount, new int[amount]);
        return i;
    }

    private int minCoins(int[] coins, int target, int[] memory) {
        if (target < 0) {
            return -1;
        }
        if (target == 0) {
            return 0;
        }
        // target不-1会超数组限制
        if (memory[target - 1] != 0) {
            return memory[target - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = minCoins(coins, target - coin, memory);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        // -1表示无法凑成
        memory[target - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memory[target - 1];
    }

    /*
    暴力剪枝：超时
    https://leetcode.cn/problems/coin-change/solutions/137760/322-by-ikaruga/
     */
    private int ans;

    public int coinChange2(int[] coins, int amount) {
        Arrays.sort(coins);
        // 最大值
        ans = Integer.MAX_VALUE;
        dfs(coins, amount, coins.length - 1, 0);
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    private void dfs(int[] coins, int target, int index, int count) {
        // 找到组合
        if (target == 0) {
            ans = Math.min(ans, count);
            return;
        }
        if (index < 0) {
            return;
        }
        // 剪枝：从大到小硬币找，找到一个组合未必是最少硬币的，所以其实还需要算全部
        // 假如算了 7=7 : 2 那么到 1的时候需要好多个1大于2，会剪枝
        for (int i = target / coins[index]; i >= 0 && i + count < ans; i--) {
            dfs(coins, target - i * coins[index], index - 1, count + i);
        }
    }

    public static void main(String[] args) {
        // 考虑到有 [1,7,10] 这种用例，按照贪心思路 10 + 1 + 1 + 1 + 1 会比 7 + 7 更早找到,所以不完全是贪心，是搜索+剪枝
        new P332零钱兑换_记忆化搜索().coinChange(new int[] {2}, 3);
        new P332零钱兑换_记忆化搜索().coinChange(new int[] {1, 7, 10}, 14);
        new P332零钱兑换_记忆化搜索().coinChange(new int[] {186, 419, 83, 408}, 6249);
        new P332零钱兑换_记忆化搜索().coinChange(new int[] {1, 5, 2, 10}, 96);
    }
}
