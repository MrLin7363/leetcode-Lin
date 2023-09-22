package A1000PLAN.动态规划.二维表格类;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * desc:只有 水平向左移动 和 竖直向上移动；
 * <p>
 * 只有 水平向左移动 和 竖直向下移动；
 * <p>
 * 只有 水平向右移动 和 竖直向上移动；
 * <p>
 * 只有 水平向右移动 和 竖直向下移动。
 *
 * @author Lin
 * @since 2023/9/6
 **/
public class P542矩阵01 {
    /*
    解释：简化为 只有 水平向左移动 和 竖直向上移动；只有 水平向右移动 和 竖直向下移动。
    dp定义: dp[i][j] 方格(i,j)距离0的最短距离
    转移方程：min(左，上)+1; min(右，下)+1
    初始化：dp最短距离初始化为最大,非0
    返回：dp数组

    错误理解：方格上下左右有0， dp[i][j]=1   方格上下左右无0， dp[i][j]= Math.min(dp[i-1][j-1],....上下左右DP)+1
    如果判断上下左右，那么有时候dp[i+1][j+1] 表格dp还没填下面的会有问题，所以通过两次遍历，能完整填完表格
     */
    public int[][] updateMatrix(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;

        int[][] dp = new int[row][col];
        // 初始化
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] != 0) {
                    // /2防止+1溢出
                    dp[i][j] = Integer.MAX_VALUE / 2;
                }
            }
        }

        // 只有 水平向左移动 和 竖直向上移动，注意动态规划的计算顺序
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 1) {
                    if (i - 1 >= 0) {
                        dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j]);
                    }
                    if (j - 1 >= 0) {
                        dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i][j]);
                    }
                }
            }
        }
        // 右下的dp数组推导
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (mat[i][j] == 1) {
                    if (i + 1 < row) {
                        dp[i][j] = Math.min(dp[i + 1][j] + 1, dp[i][j]);
                    }
                    if (j + 1 < col) {
                        dp[i][j] = Math.min(dp[i][j + 1] + 1, dp[i][j]);
                    }
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        new P542矩阵01().updateMatrix(new int[][] {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}});
        new P542矩阵01().updateMatrix2(new int[][] {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}, {1, 1, 1}});
    }

    /*
   广度优先遍历-直接从0节点扩展长度
    */
    public int[][] updateMatrix2(int[][] mat) {
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
