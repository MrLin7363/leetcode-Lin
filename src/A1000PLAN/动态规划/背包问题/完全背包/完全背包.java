package A1000PLAN.动态规划.背包问题.完全背包;

import java.util.Arrays;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/18
 **/
public class 完全背包 {
    /*
    二维
     */
    public void packageTotal2(int capacity, int n, int[] w, int[] v) {
        // 确定数组含义
        int[][] dp = new int[n][capacity + 1];

        // 确定推导公式 dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
        // 确定如何初始化 只放一个物品的各个容量的
        for (int i = 1; i < capacity + 1; i++) {
            // 物品比剩余容量小
            int left = i;
            while (w[0] <= i && w[0] <= left) {
                dp[0][i] += v[0];
                left -= w[0];
            }
        }
        // 确定遍历顺序,可以重复放，所以正序
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                if (w[i] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int[] rows : dp) {
            for (int col : rows) {
                System.out.format("%5d", col);
            }
            System.out.println();
        }
    }

    /*
    一维度
     */
    public void packageTotal(int capacity, int n, int[] w, int[] v) {
        int[] f = new int[capacity + 1];

        for (int i = 0; i < n; i++) {
            for (int j = w[i]; j <= capacity; j++) {
                //  不放当前物品 / 上一个物品+当前物品 的价值
                f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
            }
        }
        Arrays.stream(f).forEach(d-> System.out.print(d+" "));
    }

    public static void main(String[] args) {
        new 完全背包().packageTotal2(9, 4, new int[] {2, 3, 4, 5}, new int[] {3, 4, 5, 7});
    }
}
