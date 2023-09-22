package A1000PLAN.动态规划.一维DP.斐波那契数列;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/19
 **/
public class P746使用最小花费爬楼梯 {
    /*
    DP定义：dp[i] 表示达到下标 i 的最小花费
    转移方程：dp[i]=min(dp[i−1]+cost[i−1],dp[i−2]+cost[i−2])
    初始化：dp[0]=dp[1]=0
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new P746使用最小花费爬楼梯().minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        System.out.println(new P746使用最小花费爬楼梯().minCostClimbingStairs(new int[] {10, 15, 20}));
    }
}
