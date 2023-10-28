package A1000PLAN.动态规划.二维表格类;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/27
 **/
public class P119杨辉三角II {
    // DP和118一样
    public List<Integer> getRow(int rowIndex) {
        int[][] dp = new int[rowIndex + 1][];
        for (int i = 0; i < rowIndex + 1; i++) {
            dp[i] = new int[i + 1];
            dp[i][0] = 1;
            dp[i][i] = 1;
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            res.add(dp[rowIndex][i]);
        }
        return res;
    }

    // 模拟:空间优化用前一个list
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> prev = new ArrayList<>();

        for (int i = 0; i < rowIndex + 1; i++) {
            List<Integer> level = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    level.add(1);
                } else {
                    level.add(prev.get(j - 1) + prev.get(j));
                }
            }
            prev = level;
        }
        return prev;
    }

    public static void main(String[] args) {
        new P119杨辉三角II().getRow2(5);
    }
}
