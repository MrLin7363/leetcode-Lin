package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/10
  *@Describe:
 */

public class P48旋转图像 {
    /*
    水平翻转，再延主对角线反转  100+33
     */
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        // 水平翻
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n; j++) {
                int temp=matrix[n-i-1][j];
                matrix[n-i-1][j]=matrix[i][j];
                matrix[i][j]=temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
    }
    /*
    原地翻转，循环4个点 100 + 5
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) { // n+1处理奇数情况
                int temp = matrix[i][j];
                // 上=左
                matrix[i][j] = matrix[n - j - 1][i];
                // 左=下
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                // 下=右
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                // 右=上
                matrix[j][n - i - 1] = temp;
            }
        }
    }
    /*
    辅组矩阵： 100 + 5
     */
    public void rotate3(int[][] matrix) {
        int n=matrix.length;
        int[][] matrix_new=new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix_new[j][n-1-i]=matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j]=matrix_new[i][j];
            }
        }
    }
}
