package A1000PLAN.动态规划.背包问题;

import java.util.Arrays;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/17
 **/
public class 零一背包 {
    /**
     * 让装入背包的物品重量不超过背包容量 且价值最大
     * 由dp[i - 1][j]推出，即背包容量为j，里面不放物品i的最大价值，此时dp[i][j]就是dp[i - 1][j]
     * 由dp[i - 1][j - weight[i]]推出，dp[i - 1][j - weight[i]] 为背包容量为j - weight[i]的时候不放物品i的最大价值，
     * 那么dp[i - 1][j - weight[i]] + value[i] （物品i的价值），就是背包放物品i得到的最大价值
     * <p>
     * dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
     *
     * @param capacity 背包总容量
     * @param n 物品个数
     * @param w 每个物品的重量
     * @param v 每个物品的价值
     */
    /*
    二维数组正序/倒序  dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少
     */
    public void packageZoreOne(int capacity, int n, int[] w, int[] v) {
        // f[i][j]表示前 i 件物品可拿， 总重量不超过 j 的最大价值，横轴是背包容量，Y轴是物品
        int[][] f = new int[n][capacity + 1];

        // i是由i-1推导的，所以i=0一定要初始化，只取第一个物品
        for (int i = 1; i <= capacity; i++) {
            if (w[0] <= i) {
                f[0][i] = v[0];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (w[i] <= j) {
                    // 当前价值=不放当前物品价值  /  上一个物品+当前物品 的价值,上一个物品的价值=f[i - 1][j - w[i]]
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - w[i]] + v[i]);
                } else {
                    // 放不下当前物品
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        // 倒序
        // for (int i = 1; i < n; i++) {
        //     for (int j = capacity; j > 0; j--) {
        //         // 如果当前物品重量小于等于背包中的当前重量 ,w[0]是第一个物品的重量
        //         if (w[i] <= j) {
        //             f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - w[i]] + v[i]);
        //         } else {
        //             //如果当前物品重量大于背包中的当前重量
        //             f[i][j] = f[i - 1][j];
        //         }
        //     }
        // }

        for (int[] rows : f) {
            for (int col : rows) {
                System.out.format("%5d", col);
            }
            System.out.println();
        }
    }

    /*
    一维数组 (滚动数组) - 只能倒序
    递推公式：dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
    如果把dp[i - 1]那一层拷贝到dp[i]上，表达式完全可以是：dp[i][j] = max(dp[i][j], dp[i][j - weight[i]] + value[i]);
    于其把dp[i - 1]这一层拷贝到dp[i]上，不如只用一个一维数组了

    dp[j]表示：容量为j的背包，所背的物品价值可以最大为dp[j]
    dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
     */
    public void packageZoreOne2(int capacity, int n, int[] w, int[] v) {
        int[] f = new int[capacity + 1];

        for (int i = 0; i < n; i++) {
            // 如果正序遍历会有问题，那么物品0会被重复放入多次
            for (int j = capacity; j >= w[i]; j--) {
                // 上一个物品+当前物品 的价值 ; 不放当前物品的情况不需要变化
                f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
            }
        }
        // 理解版
        // for (int i = 0; i < n; i++) {
        //     // 如果正序遍历，那么物品0会被重复放入多次
        //     for (int j = capacity; j > 0; j--) {
        //         if (j >= w[i]) {
        //             f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
        //         } else {
        //             f[j] = f[j]; // 因为一维数组所以不需要修改
        //         }
        //     }
        // }
        Arrays.stream(f).forEach(d-> System.out.print(d+" "));
    }

    public static void main(String[] args) {
        new 零一背包().packageZoreOne(9, 4, new int[] {2, 3, 4, 5}, new int[] {3, 4, 5, 7});
    }
}
