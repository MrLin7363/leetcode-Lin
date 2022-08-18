 

package hw.深广遍历;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * desc: 每个方向遍历到最后，球撞到墙才停止，所以目的地基本只是在墙边才行
 *
 * @author junlin
 * @since 2022/2/7
 **/
public class P490迷宫 {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        System.out.println(bfs(maze, start, destination));
        return dfs(maze, start, destination, visited);
//        return bfs(maze, start, destination);
    }

    public boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }
        if (visited[start[0]][start[1]]) {
            return false;
        }
        visited[start[0]][start[1]] = true;
        int left = start[1] - 1;
        int right = start[1] + 1;
        int up = start[0] - 1;
        int down = start[0] + 1;
        // 下面四个while if 可以用for循环代替
        // left
        while (left >= 0 && maze[start[0]][left] == 0) {
            left--;
        }
        if (dfs(maze, new int[]{start[0], left + 1}, destination, visited)) {
            return true;
        }
        // right
        while (right < maze[0].length && maze[start[0]][right] == 0) {
            right++;
        }
        if (dfs(maze, new int[]{start[0], right - 1}, destination, visited)) {
            return true;
        }
        // up
        while (up >= 0 && maze[up][start[1]] == 0) {
            up--;
        }
        if (dfs(maze, new int[]{up + 1, start[1]}, destination, visited)) {
            return true;
        }
        // down
        while (down < maze.length && maze[down][start[1]] == 0) {
            down++;
        }
        if (dfs(maze, new int[]{down - 1, start[1]}, destination, visited)) {
            return true;
        }
        return false;
    }

    /**
     * 相当于每次向四个方向滚动球直到撞到墙
     */
    public boolean bfs(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] begin = queue.remove();
            if (begin[0] == destination[0] && begin[1] == destination[1]) {
                return true;
            }
            int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : direction) {
                int row = begin[0] + dir[0];
                int col = begin[1] + dir[1];
                while (row >= 0 && col >= 0 && row < maze.length && col < maze[0].length && maze[row][col] == 0) {
                    row += dir[0];
                    col += dir[1];
                }
                if (visited[row - dir[0]][col - dir[1]] == true) {
                    continue;
                }
                visited[row - dir[0]][col - dir[1]] = true;
                queue.add(new int[]{row - dir[0], col - dir[1]});
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P490迷宫 be = new P490迷宫();
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
        System.out.println(be.hasPath(maze, start, destination));
    }
}
