package A1000PLAN.动态规划.区间DP;

/**
 * desc: 极小化极大
 *
 * @author Lin
 * @since 2023/8/25
 **/
public class P877石子游戏 {
    /*
    dp[i][j]表示当剩下的石子堆为下标 i 到下标 j 时，即在下标范围 [i-1,j-1]中，当前玩家与另一个玩家的石子数量之差的最大值
    区间遍历方法
    https://leetcode.cn/problems/stone-game/solutions/396080/shi-zi-you-xi-by-leetcode-solution/
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n + 2][n + 2];// +1防止数组越界
        for (int len = 1; len <= n; len++) { // 区间长度，因为[i,j]可以相同，所以len从1开始
            for (int l = 1; l + (len - 1) <= n; l++) { // 区间左端点,条件：右端点不越界  (l从1开始)，保证r-1>0
                int r = l + (len - 1);
                int chooseLeft = piles[l - 1] - dp[l + 1][r];
                int chooseRight = piles[r - 1] - dp[l][r - 1];
                dp[l][r] = Math.max(chooseLeft, chooseRight);
            }
        }
        return dp[1][n] > 0;
    }

    /*
    二维DP方法
     */
    public boolean stoneGame2(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        // 行倒序，列正序； 因为依赖下面和左边
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] > 0;
    }

    /*
    一维滚动-区间DP不推荐一维，不好理解  这里把i+1->i
    */
    public boolean stoneGame3(int[] piles) {
        int length = piles.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = piles[i];
        }
        // 行倒序，列正序； 因为依赖下面和左边
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j - 1]);
            }
        }
        return dp[0] > 0;
    }

    public static void main(String[] args) {
        new P877石子游戏().stoneGame(new int[] {5, 3, 4, 5});
        new P877石子游戏().stoneGame2(new int[] {5, 3, 4, 5});
        new P877石子游戏().stoneGame3(new int[] {5, 3, 4, 5});
    }
}
