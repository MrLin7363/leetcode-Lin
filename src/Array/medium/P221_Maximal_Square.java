package Array.medium;

/**
 * @author: Junlin Chen
 * @Date: 2021/07/04 19:07
 * @Describe: DP 求最大正方形的面积
 */
public class P221_Maximal_Square {
    /*
    DP  90+85
     */
    public int maximalSquare(char[][] matrix) {
        int maxSize=0;
        if (matrix==null||matrix.length==0||matrix[0].length==0){
            return maxSize;
        }
        int rows=matrix.length,cols=matrix[0].length;
        int[][] dp =new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    if (i==0||j==0){
                        dp[i][j]=1;
                    }else{
                        dp[i][j]=Math.min( dp[i-1][j-1] ,Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    }
                    maxSize=Math.max(maxSize,dp[i][j]);
                }
            }
        }
        return maxSize*maxSize;
    }

}
