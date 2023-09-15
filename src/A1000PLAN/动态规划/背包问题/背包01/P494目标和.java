package A1000PLAN.动态规划.背包问题.背包01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/31
 **/
public class P494目标和 {
    public static void main(String[] args) {
        new P494目标和().findTargetSumWays1(new int[] {1, 1, 1, 1, 1}, 3);
    }

    /*
    1.二维原始数组-推荐好理解
    状态定义： dp[i][j]  0~i构成金额j的方案数,因为  -1000<target<1000 所以  -sum ~ sum -> 0 ~ 2sum
    状态方程：f[i][j]=f[i−1][j−nums[i−1]]+f[i−1][j+nums[i−1]]
    初始化：dp[0][sum] = 1; 全加起来的情况
     */
    public int findTargetSumWays1(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(nums[i]);
        }
        if (sum < Math.abs(target)) {
            return 0;
        }
        int[][] dp = new int[n + 1][2 * sum + 1];
        dp[0][sum] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = -sum; j <= sum; j++) {
                // 两个条件都满足就是 f[i][j]=f[i−1][j−nums[i−1]]+f[i−1][j+nums[i−1]]
                // 金额-当前数值不小于最小sum,就是全-号的情况,其实就是判断下标合理的情况
                // 当前方案数加 +nums[i]的方案数
                if (j - nums[i - 1] + sum >= 0) {
                    dp[i][j + sum] += dp[i - 1][j - nums[i - 1] + sum];
                }
                // 金额+当前数值不超过最大sum
                // 当前方案数加 -nums[i]的方案数
                if (j + nums[i - 1] + sum <= 2 * sum) {
                    dp[i][j + sum] += dp[i - 1][j + nums[i - 1] + sum];
                }
            }
        }
        return dp[n][target + sum];
    }

    /*
动态规划-两个一维数组-可以不看- 60+5
*/
    public static int findTargetSumWays5(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            // 一行行遍历，所以要遍历完一行再确定,注意每次都要初始化，否则原先的数组存在的元素会产生干扰
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }

    /*
    动态规划-二维-可以不看- 60+5
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return target > 1000 ? 0 : dp[nums.length - 1][target + 1000];
    }

    /**
     * 最优-不推荐，难理解
     * <p>
     * 转换思路  因为有-的话转换不了0-1背包，如果求+的个数和==target就转换成了01背包
     * <p>
     * 数组和（全部+）为sum  1  2  3  4    +1 -2 +3 +4     sum=10  neg=2  +的数和sum-neg=8  8-2=6为方案值
     * 假设在一个方案中  -的数的总和为neg  那么+的数的总和就应该为sum-neg
     * 那么一个方案结果为(sum-neg)-neg
     * 令这个方案值为target   target=sum-2*neg   ->  neg=(sum-target)/2
     * 首先  sum肯定要大于target  否则没办法实现(全+)
     * 所以 sum-target>0  且sum-target为偶数
     * <p>
     * 然后以neg为目标数  每次做减法
     * dp[i][j]表示 第i个数 值为j的方案
     * dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i]];
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

    /*
    3.递归记忆化 memo[][] 二维数组，官方  60+5  memo[i][j]表示在nums[0..i-1]中选出m个数（m <= i）使其和为 j 的方案数
     */
    public int findTargetSumWays3(int[] nums, int S) {
        int[][] memo = new int[nums.length][2001]; // 解决数组下标相减后为负数情况，默认加1000
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return calculate(nums, 0, 0, S, memo);
    }

    private static int calculate(int[] nums, int i, int sum, int target, int[][] memo) {
        if (i == nums.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        } else {
            if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                return memo[i][sum + 1000];
            }
            int add = calculate(nums, i + 1, sum + nums[i], target, memo);
            int subtract = calculate(nums, i + 1, sum - nums[i], target, memo);
            memo[i][sum + 1000] = add + subtract;
            return memo[i][sum + 1000];
        }
    }

    // 4.记忆化搜索-递归  46+10  hashMap  在第i层，还需要+-target==0  这里即使因不用map也够满足时间要求
    private static Map<String, Integer> memo = new HashMap<>();

    public int findTargetSumWays4(int[] nums, int S) {
        return nums.length == 0 ? 0 : backtrack4(nums, 0, S);
    }

    private static int backtrack4(int[] nums, int i, int rest) {
        if (i == nums.length) {
            if (rest == 0) {
                return 1;
            }
            return 0;
        }
        // 把它俩转成字符串才能作为哈希表的键
        String key = i + "," + rest;
        // 避免重复计算
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int result = backtrack4(nums, i + 1, rest - nums[i]) + backtrack4(nums, i + 1, rest + nums[i]);
        memo.put(key, result);
        return result;
    }
}
