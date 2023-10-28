package A1000PLAN.位运算;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/18
 **/
public class P338比特位计数 {
    /*
    x=x & (x−1)，该运算将 xxx 的二进制表示的最后一个 1 变成 0
     */
    public int[] countBits(int n) {
        int[] count = new int[n + 1];
        for (int i = 0; i <= n; i++) {

            // 计算1的个数
            int ones = 0;
            int x = i;
            while (x > 0) {
                x = x & (x - 1);
                ones++;
            }

            count[i] = ones;
        }
        return count;
    }

    /*
    DP-奇数是上一个值+1 的值， 偶数是/2的值
     */
    public int[] countBitsdp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // 偶数
            if ((i & 1) == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        new P338比特位计数().countBitsdp(4);
    }
}
