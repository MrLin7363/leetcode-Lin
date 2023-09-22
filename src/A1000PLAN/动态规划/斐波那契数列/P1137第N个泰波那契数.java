package A1000PLAN.动态规划.一维DP.斐波那契数列;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/19
 **/
public class P1137第N个泰波那契数 {
    /*
    T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
     */
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        new P1137第N个泰波那契数().tribonacci(4);
    }
}
