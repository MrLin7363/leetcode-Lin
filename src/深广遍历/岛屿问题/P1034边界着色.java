

package A1000PLAN.深广遍历.岛屿问题;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/11
 **/
public class P1034边界着色 {
    // 上右下左
    static int[] row = new int[] {-1, 0, 1, 0};

    static int[] clo = new int[] {0, 1, 0, -1};

    static boolean[][] visited;

    /*
    如果两个方块在任意 4 个方向上相邻，则称它们 相邻 。
    如果两个方块具有相同的颜色且相邻，它们则属于同一个 连通分量 。
    连通分量的边界 是指连通分量中满足下述条件之一的所有网格块：
    1.在上、下、左、右任意一个方向上与不属于同一连通分量的网格块相邻
    2.在网格的边界上（第一行/列或最后一行/列）
    请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色。
     */
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        visited = new boolean[grid.length][grid[0].length];
        int origin = grid[row][col];
        dfs(grid, row, col, color, origin);
        return grid;
    }

    private void dfs(int[][] grid, int i, int j, int color, int origin) {
        // 非连通变量 / 非合理方格 / 已经访问过
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] != origin || visited[i][j]) {
            return;
        }

        // 满足2个条件着色
        if (check(grid, i, j, color, origin)) {
            grid[i][j] = color;
        }
        visited[i][j] = true;
        // 四方向dfs
        for (int k = 0; k < 4; k++) {
            int sr = i + row[k];
            int sc = j + clo[k];
            dfs(grid, sr, sc, color, origin);
        }
    }

    private boolean check(int[][] grid, int i, int j, int color, int origin) {
        // 2.边界情况
        if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1) {
            return true;
        }
        // 1. 上下左右有非连通分量
        for (int k = 0; k < 4; k++) {
            int sr = i + row[k];
            int sc = j + clo[k];
            // 需要判断没访问过，因为访问过可能已经着色!=origin
            if (!visited[sr][sc] && grid[sr][sc] != origin) {
                return true;
            }
        }
        return false;
    }



    /*
    官方做法-----------------------------------------------------------------------------------------
     */
    public int[][] colorBorder2(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> borders = new ArrayList<>();
        int originalColor = grid[row][col];
        visited[row][col] = true;
        dfs(grid, row, col, visited, borders, originalColor);
        for (int i = 0; i < borders.size(); i++) {
            int x = borders.get(i)[0], y = borders.get(i)[1];
            grid[x][y] = color;
        }
        return grid;
    }

    private void dfs(int[][] grid, int x, int y, boolean[][] visited, List<int[]> borders, int originalColor) {
        int m = grid.length, n = grid[0].length;
        boolean isBorder = false;
        int[][] direc = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < 4; i++) {
            int nx = direc[i][0] + x, ny = direc[i][1] + y;
            // 判断上下左右 2.是否是边界外(说明当前节点是边界节点)  1.是否包含非连通变量   反过来可能好理解
            // if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] != origin ) {
            if (!(nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == originalColor)) {
                isBorder = true;
            } else if (!visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(grid, nx, ny, visited, borders, originalColor);
            }
        }
        // 找到周围
        if (isBorder) {
            borders.add(new int[]{x, y});
        }
    }
}
