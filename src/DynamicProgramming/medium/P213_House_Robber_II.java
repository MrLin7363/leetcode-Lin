package DynamicProgramming.medium;

/**
 * @author: chenjunlin
 * @since: 2021/08/09
 * @descripe: 相邻不能偷，数组是个环，最后一个和第一个是接着的
 */
public class P213_House_Robber_II {

    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return 0;
        }
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (n-2-1==i){
                dp[i] = nums[i];
                continue;
            }
            dp[i] = Math.max(dp[i - 1 < 0 ? n - 1 : i - 1], dp[i - 2 < 0 ? n - i-2 : i - 1] + nums[i]);
        }
        int res=0;
        for (int i = 0; i < dp.length; i++) {
            res=Math.max(dp[i],res);
        }
        return res;
    }

    public static void main(String[] args) {
        rob(new int[]{2,3,2});
    }
}
