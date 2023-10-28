package A1000PLAN.动态规划;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/6
 **/
public class P357统计各位数字都不同的数字个数 {
    /*
    https://leetcode.cn/problems/count-numbers-with-unique-digits/solutions/1403485/tong-ji-ge-wei-shu-zi-du-bu-tong-de-shu-iqbfn/
    评论的题解
    解释：n=2时: n=1时的答案 + 长度为2的数字个数（9*9个）= 10 + 9 * 9 = 10 + 81 = 91
         n=3时：n=2时答案 + 长度为3的数字个数   (10 + 9 * 9) + 9 * 9 * 8  (8是因为前面用了2个数，只剩8个可用)
    dp定义： dp[i]：i位数情况下不同的数字个数
    转移方程： dp[i]=dp[i-1]+(dp[i-1]-dp[i-2])*(10-(i-1))
    初始化：dp[0]=1,dp[1]=10
    遍历顺序：for n
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n < 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - 2]) * (10 - (i - 1));
        }
        return dp[n];
    }
}