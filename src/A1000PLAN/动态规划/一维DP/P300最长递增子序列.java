package A1000PLAN.动态规划.一维DP;

/**
 * @author: Junlin Chen
 * @Date: 2021/10/04 16:31
 * @Describe:
 */
public class P300最长递增子序列 {
    /*
    状态定义： dp[i] nums[0]到nums[i]的最长子序列的个数
    状态方程： 枚举j=i-1...0 如果nums[i]>nums[j]     dp[i]=Math.max(dp[i],dp[j]+1)
    初始化： 每个元素自身都是1的子序列
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            // 倒序也行
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 这里求最大可以放到上面
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        new P300最长递增子序列().findLengthOfLCIS(new int[] {1,3,5,4,7});
        new P300最长递增子序列().findLengthOfLCIS(new int[] {1,3,5,4,2,3,4,5});
        new P300最长递增子序列().lengthOfLIS(new int[] {0});
    }

    // P674 最长连续递增序列
    public int findLengthOfLCIS(int[] nums) {
        int ans = 1;
        int cur = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                cur++;
                ans = Math.max(ans, cur);
            } else {
                cur = 1;
            }
        }
        return ans;
    }
}
