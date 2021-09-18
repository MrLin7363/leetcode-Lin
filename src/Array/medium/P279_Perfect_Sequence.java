package Array.medium;

/**
 * @author: Junlin Chen
 * @Date: 2021/09/17 17:33
 * @Describe: 完全平方数
 */
public class P279_Perfect_Sequence {

    /*
    DP 33 + 27
     */
    public int numSquares(int n) {
        // 默认初始值都是0
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 最差情况就是每次+1
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

}
