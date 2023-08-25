/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2023-2023. All rights reserved.
 */

package A1000PLAN.动态规划.区间DP;

/**
 * desc: 区间DP
 *
 * @author Lin
 * @since 2023/8/24
 **/
public class P375猜数字大小II {
    /*
    //动态规划 极小极大化 //思路：这是一道Minimax算法 又名极小化极大算法问题。局部最大值，全局最小值。举例说明：
    我们可决策的部分为「选择猜哪个数 x」，而不可决策的是「选择某个数x 之后（假设没有猜中），真实值会落在哪边」。
    因此为求得「最坏情况下最好」的结果，我们应当取所有的 x 中的最小值。
 解释递归/DP https://leetcode.cn/problems/guess-number-higher-or-lower-ii/solutions/1097127/gong-shui-san-xie-yi-ti-shuang-jie-ji-yi-92e5/
 解释max/min https://leetcode.cn/problems/guess-number-higher-or-lower-ii/solutions/83395/dong-tai-gui-hua-c-you-tu-jie-by-zhang-xiao-tong-2/
 以1，2，3举例  min( max(0,1+dp[2][3]) , max(0,2+dp[1][1],2+dp[3][3]) , max(0,3+dp[1][2]) )
 max(0,1+dp[2][3])是选择1必定赢得游戏的情况
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 2][n + 2]; //区间[l, r]的最优目标值
        // 递推公式: 选择x点,l<=x<=j  有 cur=max(f[l][x-1],f[x+1][r])+x  最终f[l][r]=所有的x点的最小值
        for (int len = 2; len <= n; len++) { // 区间长度 从小区间到大区间遍历
            for (int l = 1; l + (len - 1) <= n; l++) { // 区间左端点,条件：右端点不越界   (l从1开始)
                int j = l + (len - 1); // 右端点
                int ans = Integer.MAX_VALUE;
                // 选择区间内每个端点 k==j的时候会溢出，所以n+2避免
                for (int k = l; k <= j; k++) {
                    int max = Math.max(dp[l][k - 1], dp[k + 1][j]) + k;
                    ans = Math.min(ans, max);
                }
                dp[l][j] = ans;
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        new P375猜数字大小II().getMoneyAmount(10);
    }
}
