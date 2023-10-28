package A1000PLAN.动态规划.二维表格类;

/**
 * desc: https://leetcode.cn/problems/edit-distance/solutions/6455/zi-di-xiang-shang-he-zi-ding-xiang-xia-by-powcai-3/?envType=study-plan-v2&envId=top-100-liked
 *
 * @author Lin
 * @since 2023/10/27
 **/
public class P72编辑距离_hard {
    /*
    DP定义：dp[i][j] 字符串word1[0,i]转移到words[0,j]的最小步数
    转移方程：当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]
            当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
            其中，dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。
    初始化：第一行：是 word1 为空变成 word2 最少步数，就是插入操作
           第一列：是 word2 为空，需要的最少步数，就是删除操作
    遍历顺序：都是-1,所有都正序
    返回结果：dp[n][m]
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        // 初始化第一行
        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }

        // 初始化第一列
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        P72编辑距离_hard p72编辑距离_hard = new P72编辑距离_hard();
        p72编辑距离_hard.minDistance("horse", "ros");
    }
}
