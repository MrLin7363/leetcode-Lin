package A1000PLAN.排序.二分.搜值;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/23
 **/
public class P74搜索二维矩阵 {
    /*
    二分搜索-常规版-下标
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0;
        int right = row * col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cur = matrix[mid / col][mid % col];
            if (cur == target) {
                return true;
            } else if (cur > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
