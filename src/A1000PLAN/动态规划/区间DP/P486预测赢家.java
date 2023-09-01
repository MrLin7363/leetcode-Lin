package A1000PLAN.动态规划.区间DP;

/**
 * desc: 下一进阶 石子游戏
 * 极小化极大
 * @author Lin
 * @since 2023/8/26
 **/
public class P486预测赢家 {
    /*
    下标 i到j的分数之差
    dp[i][j]=Math.max(i-dp[i+1][j],j-dp[i][j-1])
     */
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        for (int len = 1; len <= n; len++) {
            for (int l = 1; l + (len - 1) <= n; l++) {
                int r = l + (len - 1);
                int chooseLeft = nums[l - 1] - dp[l + 1][r];
                int chooseRight = nums[r - 1] - dp[l][r - 1];
                dp[l][r] = Math.max(chooseLeft, chooseRight);
            }
        }
        return dp[1][n] >= 0; // 这里平局也是赢
    }

    public static void main(String[] args) {
        new P486预测赢家().predictTheWinner(new int[] {0});
    }
}
