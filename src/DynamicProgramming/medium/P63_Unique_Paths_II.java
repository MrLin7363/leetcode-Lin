package DynamicProgramming.medium;
    
/**
  *@Author JunLin
  *@Date 2021/1/9
  *@Describe:
 */

public class P63_Unique_Paths_II {
    /*
    DP 一维数组  100 + 91
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length, n=obstacleGrid[0].length;
        int[] dp=new int[n];
        dp[0]= obstacleGrid[0][0] ==1 ? 0:1; // obstacleGrid[0][0] ==1 是有障碍物
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前元素是障碍物
                if (obstacleGrid[i][j]==1){
                    dp[j]=0;
                    continue;
                }
                // 如果当前元素的左边元素不是障碍物   第二个条件可以去掉
//                if (j>=1 && obstacleGrid[i][j-1]==0){
                if (j >= 1 ){
                    dp[j]=dp[j]+dp[j-1]; // 分别是上一行上边元素和左边前一个元素
                }
            }
        }
        return dp[n-1];
    }
    // 二维数组
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int steps[][]=new int[obstacleGrid.length][obstacleGrid[0].length];
        //初始化第一列，只要碰到一个1，那么后边都无法走到
        for(int i=0;i<obstacleGrid.length;i++){
            if(obstacleGrid[i][0]==1){break;}
            steps[i][0]=1;
        }
        //初始化第一行，只要碰到一个1，那么后边都无法走到
        for(int j=0;j<obstacleGrid[0].length;j++){
            if(obstacleGrid[0][j]==1){break;}
            steps[0][j]=1;

        }
        //在没有障碍物的情况下，到达某一点只能从左边或者上边
        for(int i=1;i<obstacleGrid.length;i++){
            for(int j=1;j<obstacleGrid[0].length;j++){
                steps[i][j]=obstacleGrid[i][j]==1?0:steps[i-1][j]+steps[i][j-1];
            }
        }
        return steps[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
}
