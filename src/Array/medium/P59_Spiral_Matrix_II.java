package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/10
  *@Describe:
 */

public class P59_Spiral_Matrix_II {
    /*
    边界 100+47
     */
    public int[][] generateMatrix(int n) {
        int top=0,right=n-1,bottom=n-1,left=0;
        int num=1;
        int total=n*n;
        int[][] matrix=new int[n][n];
        while (num<=total){
            // left to right.
            for (int col = left; col <= right; col++) {
                matrix[top][col]=num++;
            }
            top++;
            for (int row = top; row <= bottom; row++) {
                matrix[row][right]=num++;
            }
            right--;
            for (int col = right; col >=left ; col--) {
                matrix[bottom][col]=num++;
            }
            bottom--;
            for (int row = bottom; row >=top ; row--) {
                matrix[row][left]=num++;
            }
            left++;
        }
        return matrix;
    }
}
