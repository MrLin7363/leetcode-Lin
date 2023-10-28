package A1000PLAN.深广遍历.广度遍历;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/26
 **/
public class P994腐烂的橘子 {
    /* 推极度荐-自己写的
    广度遍历
    1.先找出有多少个烂橘子，加入队列
    2.每次遍历完队列的size，扩散烂橘子，记录次数
    值 0 代表空单元格；值 1 代表新鲜橘子；值 2 代表腐烂的橘子。
    没有腐烂的返回0； 无法腐烂全部的返回-1
     */
    private int[] direction = new int[] {1, 0, -1, 0, 1};

    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        // 记录新鲜橘子，如果扫描完后还剩，那么就是不成功返回-1
        int newOranges = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] {i, j});
                }
                if (grid[i][j] == 1) {
                    newOranges++;
                }
            }
        }
        // 腐烂不成功的返回-1
        if (queue.isEmpty() && newOranges != 0) {
            return -1;
        }
        // 没有腐烂的返回0
        if (queue.isEmpty() && newOranges == 0) {
            return 0;
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int row = poll[0] + direction[j];
                    int col = poll[1] + direction[j + 1];
                    if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 1) {
                        continue;
                    }
                    newOranges--;
                    grid[row][col] = 2;
                    queue.add(new int[] {row, col});
                }
            }
            if (!queue.isEmpty()) {
                count++;
            }
        }
        // 扩散完了如果还剩下未腐烂的返回-1: 通过计数来考虑
        return newOranges == 0 ? count : -1;
    }

    public static void main(String[] args) {
        int[][] grid3 = new int[][] {{1}};
        int[][] grid = new int[][] {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int[][] grid2 = new int[][] {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        new P994腐烂的橘子().orangesRotting(grid3);
        new P994腐烂的橘子().orangesRotting(grid2);
        new P994腐烂的橘子().orangesRotting(grid);
    }
}
