package A1000PLAN.贪心;

/**
 * desc: 能不能到达
 *
 * @author Lin
 * @since 2023/9/8
 **/
public class P55跳跃游戏 {
    /*
    DP定义：dp[i]到i节点前，能到达的最远节点
    转移方程: dp[i]=Math.max(dp[i-1],i+nums[i]) 不跳到
    初始化: dp[0]=nums[0]
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            // 前一个节点能到达当前节点
            if (dp[i - 1] >= i) {
                dp[i] = Math.max(dp[i - 1], i + nums[i]);
            } else {
                return false;
            }
        }
        return true;
    }
    
    // 常量最优化解
    public boolean canJump3(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new P55跳跃游戏().canJump(new int[] {2, 3, 1, 1, 4});
    }
}
