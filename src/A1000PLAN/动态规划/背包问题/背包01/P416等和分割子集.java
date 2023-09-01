package A1000PLAN.动态规划.背包问题.背包01;

/**
 * desc: 0-1背包  二维内循环正序，一维倒序
 *
 * @author Lin
 * @since 2023/8/28
 **/
public class P416等和分割子集 {
    /*
    0-1背包转换表述：给定一个只包含正整数的非空数组，判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素和的一半
    状态定义： dp[i][j]: 0-i下标范围内选取若干个数（包括0个数）能否凑成j, j最大是sum/2
    状态方程：
        nums[i]>j 不选当前数  dp[i][j]=dp[i-1][j]
        nums[i]<=j  选或不选  dp[i][j]=dp[i-1][j]|dp[i-1][j-nums[i]]
    初始化：
        j==0时，不选取，dp[i][0]=true
        i==0时，dp[0][nums[i]]=true
    前置流程: 获取所有元素和sum, 最大元素max
        1.max>sum/2 不能组成两个子集 return false
        2.sum是奇数，不能组成两个子集  return false
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int max = -1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }

        boolean[][] dp = new boolean[n][target + 1];
        // 只选一个 只有一种方案能组成j
        dp[0][nums[0]] = true;
        // 金额0都不选就是true
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 选或不选
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[n - 1][target];
    }

    /*
    一维DP  dp[j]: 能否凑成j
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        int max = -1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }

        boolean[] dp = new boolean[target + 1];
        dp[nums[0]] = true;
        dp[0] = true;

        for (int i = 1; i < n; i++) {
            for (int j = target; j >= 1; j--) {
                if (nums[i] > j) {
                    dp[j] = dp[j];
                } else {
                    // 需要倒序，如果不倒序，dp[j-nums[i]]就是已经被更新过得状态了，不是上一行的状态
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        new P416等和分割子集().canPartition(new int[] {1, 5, 11, 5});
        new P416等和分割子集().canPartition2(new int[] {2, 2, 3, 5});
    }
}
