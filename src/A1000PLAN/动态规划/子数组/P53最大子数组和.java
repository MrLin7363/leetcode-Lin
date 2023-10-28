package A1000PLAN.动态规划.子数组;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/12
 **/
public class P53最大子数组和 {
    /*
    直接 一维->常量级
    DP定义：dp[i] 以第 i 个元素结尾的和最大子数组的和
    转移方程：dp[i]=max(dp[i],dp[i-1]+nums[i])
    初始化：
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int first = nums[0];
        int ans = first;
        for (int i = 1; i < n; i++) {
            first = Math.max(nums[i], first + nums[i]);
            ans = Math.max(ans, first);
        }
        return ans;
    }

    public static void main(String[] args) {
        new P53最大子数组和().maxSubArray(new int[] {-1});
        new P53最大子数组和().maxSubArray(new int[] {5, 4, -1, 7, 8});
    }
}
