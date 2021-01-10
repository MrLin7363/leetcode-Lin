package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/10
  *@Describe:
 */

import java.util.Arrays;

public class P64_Minimum_Path_Sum {
    /*
    DP 二维数组  85 + 29
     */
    public int minPathSum(int[][] grid) {
        int row=grid.length,col=grid[0].length;
        int[][] dp=new int[row][col];
        // 初始化第一个元素
        dp[0][0]=grid[0][0];
        // 初始化第一行和第一列
        for (int i = 1; i < row; i++) {
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }
        for (int i = 1; i < col; i++) {
            dp[0][i]=dp[0][i-1]+grid[0][i];
        }
        // 计算剩余
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[row-1][col-1];
    }
    /*
    DP 一维数组 10 + 5
     */
    public int minPathSum2(int[][] grid) {
        int row=grid.length,col=grid[0].length;
        int[] dp=new int[col];
        Arrays.fill(dp,Integer.MAX_VALUE);
        // 初始化第一个元素
        dp[0]=0;
        for (int i = 0; i < row; i++) {
            dp[0]=dp[0]+grid[i][0];
            for (int j = 1; j < col; j++) {
                dp[j]=Math.min(dp[j],dp[j-1]) + grid[i][j];
            }
        }
        return dp[col-1];
    }
}