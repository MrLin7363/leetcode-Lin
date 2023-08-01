package Array.medium;

/**
 * @author: chenjunlin
 * @since: 2021/08/24
 * @descripe:
 */
public class P200岛屿数量 {

    /*
    DFS
    探测到一个岛，向四周扩散，将岛屿的 1 > 0 被水填满
    99 + 94
     */
    int nums = 0;

    public int numIslands(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    nums++;
                    dfsExpandIslands(grid, i, j);
                }
            }
        }
        return nums;
    }

    private void dfsExpandIslands(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfsExpandIslands(grid, i + 1, j);
        dfsExpandIslands(grid, i - 1, j);
        dfsExpandIslands(grid, i, j + 1);
        dfsExpandIslands(grid, i, j - 1);
    }

}
