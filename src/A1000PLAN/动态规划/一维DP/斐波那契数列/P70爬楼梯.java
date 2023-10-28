package A1000PLAN.动态规划.一维DP.斐波那契数列;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/19
 **/
public class P70爬楼梯 {
    /*
    DP定义：dp[i]跳到第i个台阶的方案数
    转移方程：dp[i]=dp[i-1]+dp[i-2] ->依赖两个变量->常量级DP
    初始化：dp1=1,dp2=2
    */
    public int climbStairs(int n) {
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int temp = second;
            second = first + second;
            first = temp;
        }
        return n == 1 ? first : second;
    }

    /*
    DFS
     */
    public int climbStairsdfs(int n) {
        return dfs(n, new int[n + 1]);
    }

    private int dfs(int n, int[] memo) {
        if (memo[n] > 0) {
            return memo[n];
        }
        if (n == 1) {
            memo[n] = 1;
        } else if (n == 2) {
            memo[n] = 2;
        } else {
            memo[n] = dfs(n - 1, memo) + dfs(n - 2, memo);
        }
        return memo[n];
    }

    public static void main(String[] args) {
        System.out.println(new P70爬楼梯().climbStairsdfs(4));
        System.out.println(new P70爬楼梯().climbStairsdfs(3));
    }
}
