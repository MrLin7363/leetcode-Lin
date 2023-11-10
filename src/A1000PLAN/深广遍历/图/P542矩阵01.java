package A1000PLAN.深广遍历.图;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/6
 **/
public class P542矩阵01 {
    public static void main(String[] args) {
        new P542矩阵01().updateMatrix(new int[][] {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}, {1, 1, 1}});
    }

    /*
    广度优先遍历-直接从0节点扩展长度
     */
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> queue = new ArrayDeque<>();
        int row = mat.length;
        int col = mat[0].length;
        int[][] res = new int[row][col];
        boolean[][] seen = new boolean[row][col];
        int[][] direction = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 0) {
                    seen[i][j] = true;
                    queue.add(new int[] {i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int k = 0; k < 4; k++) {
                int ni = poll[0] + direction[k][0];
                int nj = poll[1] + direction[k][1];
                if (ni >= 0 && ni < row && nj >= 0 && nj < col && !seen[ni][nj]) {
                    res[ni][nj] = res[poll[0]][poll[1]] + 1;
                    queue.add(new int[] {ni, nj});
                    seen[ni][nj] = true;
                }
            }
        }
        return res;
    }
}

