package A1000PLAN.动态规划.二维表格类;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/27
 **/
public class P118杨辉三角 {
    /*
    二维DP定义： dp[i][j] 第i行第j列的元素值
    转义方程：dp[i][j]=dp[i-1]
    初始化: i==0,是dp=1; j=length时,dp=1
     */
    public List<List<Integer>> generate(int numRows) {
        int[][] dp = new int[numRows][];
        for (int i = 0; i < numRows; i++) {
            dp[i] = new int[i + 1];
            dp[i][0] = 1;
            dp[i][i] = 1;
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> level = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                level.add(dp[i][j]);
            }
            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        new P118杨辉三角().generate(5);
    }

    /*
    模拟: list
     */
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }
}
