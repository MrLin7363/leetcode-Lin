package A1000PLAN.动态规划.一维DP;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/24
 **/
public class P397整数替换 {
    /*
    给定一个正整数 n ，你可以做如下操作：
    如果 n 是偶数，则用 n / 2替换 n 。
    如果 n 是奇数，则可以用 n + 1或n - 1替换 n
    返回 n 变为 1 所需的 最小替换次数 。

    dp3=2; dp4=2; dp5=3; dp6=3 dp7=4 dp8=3 dp9=4 dp10=4 dp11=5 dp12=4 dp13=5 dp14=5 dp15=5
    特殊情况如果是 15+1/2=8   15-1/2=7   并不是-1的情况是一定会减少次数的
     */

    /*
    内存超时-逻辑正确的   超内存，时间空间复杂度都是O(n),相比logn差的有点大了
     */
    public int integerReplacementDP(int n) {
        if (n <= 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[1] = dp[0] = 0;
        dp[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2] + 1;
            } else {
                // 需要判断特殊情况
                if (dp[(i - 1) / 2] < dp[(i + 1) / 2]) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = dp[(i + 1) / 2] + 2;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        new P397整数替换().integerReplacementDP(65535);
    }
}