package DynamicProgramming.medium;

/**
 * @author: chenjunlin
 * @since: 2021/08/09
 * @descripe: 相邻不能偷，数组是个环，最后一个和第一个是接着的
 */
public class P213_House_Robber_II {

    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        if (n==1){
            return nums[0];
        }
        if (n==2){
            return 0;
        }
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < n-1; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[n-1];
    }

}
