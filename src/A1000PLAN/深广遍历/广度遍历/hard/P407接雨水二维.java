package A1000PLAN.深广遍历.广度遍历.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/25
 **/
public class P407接雨水二维 {
    /*
    木桶原理：木桶能装的水取决于最短板
    条件：
    1.该方块不为最外层的方块；
    2.该方块自身的高度比其上下左右四个相邻的方块接水后的高度都要低；

    我们假设方块的索引为 (i,j)，方块的高度为 heightMap[i][j]，方块接水后的高度为 water[i][j]
    则我们知道方块 (i,j)的接水后的高度为：
    water[i][j]=max(heightMap[i][j],min(water[i−1][j],water[i+1][j],water[i][j−1],water[i][j+1]))
    水容量为 heightMap[i][j]-water[i][j]  最外层方格水water[i][j]=heightMap[i][j]

    确定最小高度方块的相邻方块的接水高度,如果相邻小于当前高度，则更新水高度为当前高度

    广度遍历+堆
     */
    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;
        if (n <= 2) {
            return 0;
        }
        // 初始化周边方格
        // 堆 x y h 坐标+水高度
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        boolean[][] visited = new boolean[n][m];
        // 周边两列
        for (int i = 0; i < n; i++) {
            queue.add(new int[] {i, 0, heightMap[i][0]});
            queue.add(new int[] {i, m - 1, heightMap[i][m - 1]});
            visited[i][0] = true;
            visited[i][m - 1] = true;
        }
        // 周边两行(排除两行)
        for (int j = 1; j < m - 1; j++) {
            queue.add(new int[] {0, j, heightMap[0][j]});
            queue.add(new int[] {n - 1, j, heightMap[n - 1][j]});
            visited[0][j] = true;
            visited[n - 1][j] = true;
        }

        // 初始化周边的时候可以采用下面的方式,这个好理解不过复杂度多一点
        // for (int i = 0; i < m; ++i) {
        //     for (int j = 0; j < n; ++j) {
        //         if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
        //            ......
        //         }
        //     }
        // }

        int[] direction = new int[] {1, 0, -1, 0, 1};
        int sum = 0;
        // 广度遍历
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            // 看一下周围四个方向，没访问过的话能不能往里灌水
            for (int i = 0; i < 4; i++) {
                int x = poll[0] + direction[i];
                int y = poll[1] + direction[i + 1];
                // 遍历到的方格未访问过且合法
                if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y]) {
                    visited[x][y] = true;
                    // 如果外围这一圈中最小的比当前这个还高，那就说明能往里面灌水啊
                    if (heightMap[x][y] < poll[2]) {
                        queue.add(new int[] {x, y, poll[2]});
                        sum += poll[2] - heightMap[x][y];
                    } else {
                        // 灌不了水就是方格高度
                        queue.add(new int[] {x, y, heightMap[x][y]});
                    }
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        new P407接雨水二维().trapRainWater(new int[][] {{2, 3, 3, 3}, {2, 1, 1, 3}, {3, 3, 3, 3,}});
    }
}
