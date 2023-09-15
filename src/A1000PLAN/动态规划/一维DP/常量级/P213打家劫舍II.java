package A1000PLAN.动态规划.一维DP.常量级;

/**
 * desc: 环形房子，间隔不能打劫
 *
 * @author Lin
 * @since 2023/9/8
 **/
public class P213打家劫舍II {
    /*
    解释：金额/房子数
    dp定义：dp[i]  第0~i间房子能偷的最高金额
    转移方程：如果不偷窃最后一间房屋，则偷窃房屋的下标范围是 [0,n−2]；如果不偷窃第一间房屋，则偷窃房屋的下标范围是 [1,n−1]
    初始化：dp[0]=nums[0]
        dp[1] = Math.max(nums[0], nums[1]);
    */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(countDp(nums, 0, n - 2), countDp(nums, 1, n - 1));
    }

    // 常量求范围内的最大偷窃金额，其实就是P198题
    private int countDp(int[] nums, int start, int end) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
