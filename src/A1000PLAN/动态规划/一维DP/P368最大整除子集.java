package A1000PLAN.动态规划.一维DP;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/25
 **/
public class P368最大整除子集 {
    /*
    子集是不一定连续的
    从小到大排序
    状态定义：dp[i]：以nums[i]为子集中最大元素的整除子集大小
    状态方程：枚举j=0...i-1的所有整数nums[j]，如果nums[j]能整除nums[i],则dp[i]=Math.max(dp[i],dp[j]+1)
    初始化：都为1，自身为一个子集
    求结果：找到最大整除子集的个数，以及该子集的最大值，倒序遍历，如果能整除最大值，则加入结果集
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return Arrays.asList(nums[0]);
        }
        Arrays.sort(nums);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxSize = 0;
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // nums[j]能整除nums[i]
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                if (dp[i] > maxSize) {
                    maxSize = dp[i];
                    maxVal = nums[i];
                }
            }
        }

        List<Integer> res = new LinkedList<>();
        for (int i = n - 1; i >= 0 && maxSize > 0; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.add(nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new P368最大整除子集().largestDivisibleSubset(new int[] {1,2,3});
    }
}
