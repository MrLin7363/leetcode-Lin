package A1000PLAN.动态规划.一维DP.常量级;

/**
 * desc:间隔不能打劫
 *
 * @author Lin
 * @since 2023/9/7
 **/
public class P198打家劫舍 {
    /*
    解释：金额/房子数
    dp定义：dp[i]  第0~i间房子能偷的最高金额
    转移方程： dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
    初始化：dp[0]=nums[0]
            dp[1] = Math.max(nums[0], nums[1]);
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

    // 优化为两个常量值
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return nums[0];
        }
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(new P198打家劫舍().rob(new int[] {1, 2, 3, 1}));
        System.out.println(new P198打家劫舍().rob(new int[] {2, 1, 1, 2}));
        System.out.println(new P198打家劫舍().rob(new int[] {1, 1}));
        System.out.println(new P198打家劫舍().rob(new int[] {2, 7, 9, 3, 1}));
    }
}
