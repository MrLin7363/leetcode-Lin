package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/8
  *@Describe:
 */

import java.util.Arrays;

public class P62_Unique_Paths {
    /*
    DP 二维数组 100 + 48
     */
    public int uniquePaths(int m, int n) {
        int[][] dp=new int[m][n];
        //所有的 f(0, j)f(0,j) 以及 f(i, 0)f(i,0) 都设置为边界条件，它们的值均为 11
        for (int i = 0; i < m; i++) {
            dp[i][0]=1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i]=1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    // DP  两个一维数组  100 + 98
    public int uniquePaths2(int m, int n) {
        int[] pre=new int[n]; // 前一行 注意个数是 n 列数
        int[] cur=new int[n]; // 当前行
        Arrays.fill(pre,1);
        Arrays.fill(cur,1);
        for (int i = 1; i < m;i++) {
            for (int j = 1; j < n; j++) {
                cur[j] = cur[j-1] + pre[j]; // 当前行左边一个，和前一行正上面一个
            }
            pre=cur.clone(); // 两个数组要不一样，否则第二个for循环有重复
        }
        return pre[n-1];
    }
    // DP 一个一维数组 100 + 100
    // cur[j] = cur[j] + cur[j-1]，等号右边分别是该位置上边的值和左边的值
    public int uniquePaths3(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j]+=dp[j-1];
            }
        }
        return dp[n-1];
    }

}
