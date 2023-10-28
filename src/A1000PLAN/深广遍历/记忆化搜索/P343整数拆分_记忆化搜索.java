package A1000PLAN.深广遍历.记忆化搜索;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/23
 **/
public class P343整数拆分_记忆化搜索 {
    /*
    F(i) 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积  F(2)=1
    https://leetcode.cn/problems/integer-break/solutions/16677/bao-li-sou-suo-ji-yi-hua-sou-suo-dong-tai-gui-hua-/
     */
    private int[] memory;

    public int integerBreak(int n) {
        memory = new int[n + 1];
        return dfs(n);
    }

    private int dfs(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (memory[n] != 0) {
            return memory[n];
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, Math.max(i * (n - i), i * dfs(n - i)));
        }
        memory[n] = res;
        return res;
    }
}
