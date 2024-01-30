package A1000PLAN.数组.矩阵类;

/**
 * desc:
 *
 * @author Lin
 * @since 2024/1/30
 **/
public class P48旋转图像 {
    /*
    推荐： 上下水平翻转+对角线翻转(左上->右下)
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        // 对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /*
    不推荐：不好记住
    O n^2+O1
    2.原地翻转，以4*4为例，只需要遍历左上角的2*2方格，以这些为起点，去旋转，一个变量记录一开始的值
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            // n+1 考虑奇数的情况
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j]; // 记录左上
                matrix[i][j] = matrix[n - j - 1][i]; // 左上=左下
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1]; // 左下=右下
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1]; // 右下=右上  注意：这里易错：
                matrix[j][n - i - 1] = temp; // 右上=左上
            }
        }
    }

    /*
    暴力：新矩阵 On^2 + On^2
    规律：原矩阵第[i,j]元素在新矩阵坐标是[j,n-i-1]
    */
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }

    public static void main(String[] args) {
        new P48旋转图像().rotate(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        new P48旋转图像().rotate2(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        new P48旋转图像().rotate3(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}
