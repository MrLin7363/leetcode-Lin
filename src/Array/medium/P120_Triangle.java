package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/11
  *@Describe:
 */

import java.util.ArrayList;
import java.util.List;

public class P120_Triangle {

    /*
    自顶向下二维DP  70+90
      dp[i][j]=min(dp[i−1][j−1],dp[i−1][j])+c[i][j]
    注意边界问题，dp[i][0]行最左侧和最右侧只能由一个元素推算
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n=triangle.size();
        int[][] dp=new int[n][n];
        dp[0][0]=triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            // 行最左侧
            dp[i][0]=dp[i-1][0]+triangle.get(i).get(0);
            // 行中间元素
            for (int j = 1; j < i; j++) { // triangle.get(i).size() == i
                dp[i][j]=Math.min(dp[i-1][j],dp[i-1][j-1])+triangle.get(i).get(j);
            }
            // 行最右侧
            dp[i][i]=dp[i-1][i-1]+triangle.get(i).get(i);
        }
        // 比较最后一行的DP
        int min=dp[n-1][0]; // 默认最小先是最后一行第一个
        for (int i = 1; i < n; i++) {
            min=Math.min(min,dp[n-1][i]);
        }
        return min;
    }
    /*
    自顶向下 两个一维数组
     */
    public int minimumTotal4(List<List<Integer>> triangle) {
        int n=triangle.size();
        int[] cur=new int[n];
        int[] prev=new int[n];
        prev[0]=triangle.get(0).get(0);
        cur[0]=triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            // 行最左侧
            cur[0]=prev[0]+triangle.get(i).get(0);
            // 行中间元素
            for (int j = 1; j < i; j++) {
                cur[j]=Math.min( prev[j],prev[j-1] )+triangle.get(i).get(j);
            }
            // 行最右侧
            cur[i]=prev[i-1]+triangle.get(i).get(i);
            // 数组克隆，不能直接引用 =  否则第二次循环改变cur 会改变prev的值
            prev=cur.clone();
        }
        // 比较最后一行的DP
        int min=cur[0]; // 默认最小先是最后一行第一个
        for (int i = 1; i < n; i++) {
            min=Math.min(min,cur[i]);
        }
        return min;
    }
    /*
    自顶向下 一个一维数组
     */
    public int minimumTotal5(List<List<Integer>> triangle) {
        int n=triangle.size();
        int[] dp=new int[n];
        dp[0]=triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            // 注意先行右再行左
            // 行最右侧
            dp[i]=dp[i-1]+triangle.get(i).get(i);
            // 行中间元素
            // j 和 j-1 都是上一行的数据，注意这里要从右到左遍历，否则数据会被覆盖
            for (int j = i-1; j > 0; j--) {
                dp[j]=Math.min(dp[j],dp[j-1])+triangle.get(i).get(j);
            }
            // 行最左侧
            dp[0]=dp[0]+triangle.get(i).get(0);
        }
        // 比较最后一行的DP
        int min=dp[0]; // 默认最小先是最后一行第一个
        for (int i = 1; i < n; i++) {
            min=Math.min(min,dp[i]);
        }
        return min;
    }
    /*
    自底向上 二维数组 95+72
     */
    public int minimumTotal6(List<List<Integer>> triangle) {
        int n=triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和
        // 超出一行默认都为0
        int[][] dp=new int[n+1][n+1];
        // 从三角形的最后一行开始递推
        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j]=Math.min(dp[i+1][j],dp[i+1][j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
    /*
    自底向上 一个一维数组
     */
    public int minimumTotal7(List<List<Integer>> triangle) {
        int n=triangle.size();
        int[] dp=new int[n+1];
        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j]=Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
    /*
    记忆化递归 否则超时  95+61
    记住每次遍历到的点的最小
     */
    Integer[][] memo;
    public int minimumTotal(List<List<Integer>> triangle) {
        memo=new Integer[triangle.size()][triangle.size()];
        return recursion(triangle,0,0);
    }
    public int recursion(List<List<Integer>> triangle,int row,int col) {
        if (row > triangle.size() - 1 || col > triangle.get(row).size() - 1) {
            return 0;
        }
        // 剪枝
        if (memo[row][col]!=null){
            return memo[row][col];
        }
        // 当进入下一行的相邻两个元素
        return memo[row][col]=Math.min( recursion(triangle, row + 1, col),
                recursion(triangle, row + 1, col + 1)) +triangle.get(row).get(col) ;
    }

    public static void main(String[] args) {
        P120_Triangle s=new P120_Triangle();
        List<List<Integer>> triangle=new ArrayList<>();
        triangle.add(new ArrayList<>());
        triangle.get(0).add(2);
        triangle.add(new ArrayList<>());
        triangle.get(1).add(3);
        triangle.get(1).add(4);
        triangle.add(new ArrayList<>());
        triangle.get(2).add(6);
        triangle.get(2).add(5);
        triangle.get(2).add(7);
        triangle.add(new ArrayList<>());
        triangle.get(3).add(4);
        triangle.get(3).add(1);
        triangle.get(3).add(8);
        triangle.get(3).add(3);
        s.minimumTotal4(triangle);
    }
}
