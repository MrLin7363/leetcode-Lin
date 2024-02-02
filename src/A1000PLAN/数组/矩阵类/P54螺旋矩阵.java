package A1000PLAN.数组.矩阵类;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author Lin
 * @since 2024/2/2
 **/
public class P54螺旋矩阵 {
    /*
    访问二维矩阵+遇到路径换方向
    右下上左，遇到障碍才转变方向
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int row = matrix.length;
        int col = matrix[0].length;
        int total = row * col;
        int[] cur = new int[] {0, 0};
        boolean[][] visited = new boolean[row][col];
        int turns = 0;
        for (int i = 0; i < total; i++) {
            visited[cur[0]][cur[1]] = true;
            res.add(matrix[cur[0]][cur[1]]);
            int nextRow = cur[0] + directions[turns][0];
            int nextCol = cur[1] + directions[turns][1];
            if (nextRow > row - 1 || nextCol > col - 1 || nextRow < 0 || nextCol < 0 || visited[nextRow][nextCol]) {
                turns = (turns + 1) % 4;
            }
            cur[0] += directions[turns][0];
            cur[1] += directions[turns][1];
        }
        return res;
    }

    /*
    由外向内遍历
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            // 添加上行
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            // 添加右列
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            //添加下行和左列，如果相同在上面已经添加
            if (top < bottom && left < right) {
                // 添加下行
                for (int i = right - 1; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                for (int i = bottom - 1; i > top; i--) {
                    res.add(matrix[i][left]);
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return res;
    }

    public static void main(String[] args) {
        new P54螺旋矩阵().spiralOrder2(new int[][] {{7}, {9}, {6}});
        new P54螺旋矩阵().spiralOrder2(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        new P54螺旋矩阵().spiralOrder(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}
