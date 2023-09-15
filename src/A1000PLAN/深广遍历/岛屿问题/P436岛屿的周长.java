

package A1000PLAN.深广遍历.岛屿问题;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/11
 **/
public class P436岛屿的周长 {
    /*迭代*/
    public int islandPerimeter(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    count += countWay(grid, i, j);
                }
            }
        }
        return count;
    }

    /*
    下面的四条语句可以使用for方向循环， 边界的情况+1，非边界且相邻节点是水域也+1
      // 下个格子的x轴与y轴加的值，右下左上
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
      for (int k = 0; k < 4; k++) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        // tx < 0，已是左边界；tx >= n，已是右边界
                        // ty < 0，已是上边界；ty >= m，已是下边界
                        // grid[tx][ty] == 0，相邻格子是水域
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
     */
    private int countWay(int[][] grid, int i, int j) {
        int ans = 0;
        // 左
        if (j == 0 || (j > 0 && grid[i][j - 1] == 0)) {
            ans++;
        }
        // 上
        if (i == 0 || (i > 0 && grid[i - 1][j] == 0)) {
            ans++;
        }
        // 右
        if (j == grid[0].length - 1 || (j < grid[0].length - 1 && grid[i][j + 1] == 0)) {
            ans++;
        }
        // 下
        if (i == grid.length - 1 || (i < grid.length - 1 && grid[i + 1][j] == 0)) {
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        new P436岛屿的周长().islandPerimeter(new int[][] {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}});
    }

    /*
    官方 一样的迭代
     */
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public int islandPerimeter2(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }
}
