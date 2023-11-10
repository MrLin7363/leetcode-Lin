package A1000PLAN.前缀和.二维;

/**
 *desc:https://leetcode.cn/problems/range-sum-query-2d-immutable/solutions/627420/er-wei-qu-yu-he-jian-suo-ju-zhen-bu-ke-b-2z5n/
 *@author lin
 *@since 2023/11/8
 **/
public class P304二维区域和检索矩阵不可变 {
    /*
    二维前缀和：
     */
    class NumMatrix {
        private int[][] presum;

        public NumMatrix(int[][] matrix) {
            // 列+1方便 列=0的时候的运算
            this.presum = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    presum[i + 1][j + 1] = presum[i + 1][j] + presum[i][j + 1] - presum[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return presum[row2 + 1][col2 + 1] - presum[row1][col2 + 1] - presum[row2 + 1][col1] + presum[row1][col1];
        }
    }
    /*
    一维前缀和: 每一行计算前缀和，最后矩阵的运算  分别每一行前缀和 类似303题
     */
    /*
    class NumMatrix {
        private int[][] presum; // 每行的前缀和

        public NumMatrix(int[][] matrix) {
            // 列+1方便 列=0的时候的运算
            this.presum = new int[matrix.length][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    presum[i][j + 1] = presum[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum += presum[i][col2 + 1] - presum[i][col1];
            }
            return sum;
        }
    }
    */
}
