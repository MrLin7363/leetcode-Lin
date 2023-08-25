package A1000PLAN.深广遍历.记忆化搜索;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/24
 **/
public class P375猜数字大小II_记忆化搜索 {
    /*
    //动态规划 极小极大化 //思路：这是一道Minimax算法 又名极小化极大算法问题。局部最大值，全局最小值。举例说明：
    我们可决策的部分为「选择猜哪个数 x」，而不可决策的是「选择某个数x 之后（假设没有猜中），真实值会落在哪边」。
    因此为求得「最坏情况下最好」的结果，我们应当取所有的 x 中的最小值。
 解释递归 https://leetcode.cn/problems/guess-number-higher-or-lower-ii/solutions/1097127/gong-shui-san-xie-yi-ti-shuang-jie-ji-yi-92e5/
 解释max/min https://leetcode.cn/problems/guess-number-higher-or-lower-ii/solutions/83395/dong-tai-gui-hua-c-you-tu-jie-by-zhang-xiao-tong-2/
 以1，2，3举例  min( max(0,1+dp[2][3]) , max(0,2+dp[1][1],2+dp[3][3]) , max(0,3+dp[1][2]) )
 max(0,1+dp[2][3])是选择1必定赢得游戏的情况
     */
    private int[][] cache;

    public int getMoneyAmount(int n) {
        cache = new int[n + 1][n + 1];
        return dfs(1, n);
    }

    private int dfs(int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (cache[left][right] != 0) {
            return cache[left][right];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            int max = Math.max(dfs(left, i - 1), dfs(i + 1, right)) + i;
            ans = Math.min(ans, max);
        }
        cache[left][right] = ans;
        return ans;
    }

    public static void main(String[] args) {
        new P375猜数字大小II_记忆化搜索().getMoneyAmount(10);
    }
}
