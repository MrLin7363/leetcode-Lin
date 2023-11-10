package A1000PLAN.深广遍历.图;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/26
 **/
public class P200岛屿数量 {
    /*
    找1然后1全部填为0
     */
    private int[] direction = new int[] {1, 0, -1, 0, 1};

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    // 1填0,不需要记录访问过
    private void dfs(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return;
        }
        if (grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';
        for (int k = 0; k < 4; k++) {
            int i = row + direction[k];
            int j = col + direction[k + 1];
            dfs(grid, i, j);
        }
    }
}
