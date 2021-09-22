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
    public static int numSquares(int n) {
        // 默认初始值都是0
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 最差情况就是每次+1
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                // dp[0]=0,dp[1]=1,dp[2]=2,dp[3]=3,dp[4]=1 -> 4 = 4
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        numSquares(4);
    }

}
