 

package hw.深广遍历;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * desc: 每个方向遍历到最后，球撞到墙才停止，撞到墙为1步
 * 每撞一次墙算一层，但是撞墙次数少的路径不一定总步数少，因为一次撞墙可以走多步。
 * 0可以走，1不可以走
 *
 * @author junlin
 * @since 2022/2/7
 **/
public class P505迷宫2 {

    /**
     * 计数器 count 记录当前的步数。为了防止重复搜索，我们需要使用二维数组 distance 记录从起始位置到 (i, j) 的最小步数 distance[i, j]
     * 若在某一次搜索到位置 (i, j) 时，distance[i, j] 的值小于等于 count，那么我们可以进行回溯，不必继续搜索下去
     * <p>
     * 链接：https://leetcode-cn.com/problems/the-maze-ii/solution/mi-gong-ii-by-leetcode/
     */
    private int[][] distance;

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        distance = new int[maze.length][maze[0].length];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        dfs(maze, start);
        return bfs(maze,start,destination);
//        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 :
//            distance[destination[0]][destination[1]];
    }

    public void dfs(int[][] maze, int[] start) {
        int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : direction) {
            int row = start[0] + dir[0];
            int col = start[1] + dir[1];
            int count = 0;
            while (row >= 0 && col >= 0 && row < maze.length && col < maze[0].length && maze[row][col] == 0) {
                row += dir[0];
                col += dir[1];
                count++;
            }
            if (distance[start[0]][start[1]] + count < distance[row - dir[0]][col - dir[1]]) {
                distance[row - dir[0]][col - dir[1]] = distance[start[0]][start[1]] + count;
                dfs(maze, new int[]{row - dir[0], col - dir[1]});
            }
        }
    }

    public int bfs(int[][] maze, int[] start, int[] destination) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] begin = queue.remove();
            int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : direction) {
                int row = begin[0] + dir[0];
                int col = begin[1] + dir[1];
                int count = 0;
                while (row >= 0 && col >= 0 && row < maze.length && col < maze[0].length && maze[row][col] == 0) {
                    row += dir[0];
                    col += dir[1];
                    count++;
                }
                if (distance[begin[0]][begin[1]] + count < distance[row - dir[0]][col - dir[1]]) {
                    distance[row - dir[0]][col - dir[1]] = distance[begin[0]][begin[1]] + count;
                    queue.add(new int[]{row - dir[0], col - dir[1]});
                }
            }
        }
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 :
            distance[destination[0]][destination[1]];
    }

    public static void main(String[] args) {
        P505迷宫2 be = new P505迷宫2();
        int[][] maze = new int[1][4];
        maze[0][0] = 0;
        maze[0][1] = 0;
        maze[0][2] = 0;
        maze[0][3] = 0;
        int[] start = new int[2];
        start[0] = 0;
        start[1] = 3;
        int[] destination = new int[2];
        destination[0] = 0;
        destination[1] = 0;
        System.out.println(be.shortestDistance(maze, start, destination));
    }

}
