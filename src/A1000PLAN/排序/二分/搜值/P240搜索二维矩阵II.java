

package A1000PLAN.排序.二分.搜值;

/**
 * desc:
 *
 * @author Lin
 * @since 2024/2/2
 **/
public class P240搜索二维矩阵II {
    /*
    从左下逐行开始向上遍历,找到元素所在的行，再来一次顺序遍历 O m+n
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col <= matrix[0].length - 1) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
    }

    /*
    逐行二分查找
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        while (row >= 0) {
            // 剪枝:如果第一个元素不比target小，就不用继续找这行了
            if (matrix[row][0] <= target && binarySearch(matrix[row], target)) {
                return true;
            }
            row--;
        }
        return false;
    }

    private boolean binarySearch(int[] cur, int target) {
        int left = 0;
        int right = cur.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (cur[mid] < target) {
                left = mid + 1;
            } else if (cur[mid] > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new P240搜索二维矩阵II().binarySearch(new int[] {2, 3, 4, 5}, 3);
    }
}
