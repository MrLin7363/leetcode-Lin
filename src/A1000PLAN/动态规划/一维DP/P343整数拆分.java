package A1000PLAN.动态规划.一维DP;

/**
 * desc: 完全背包-组成背包的最大乘积   硬币则是1~n  amout=1~n
 *
 * @author Lin
 * @since 2023/8/23
 **/
public class P343整数拆分 {
    /*
   dp[i] 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积
   当 i≥2 时，假设对正整数 i 拆分出的第一个正整数是 j（1<=j<i），则有以下两种方案：
    将 i 拆分成 j 和 i−j 的和，且 i−j 不再拆分成多个正整数，此时的乘积是 j×(i−j)
    将 i 拆分成 j 和 i−j 的和，且 i−j 继续拆分成多个正整数，此时的乘积是 j×dp[i−j]
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i < n + 1; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        new P343整数拆分().integerBreak(5);
    }
}
