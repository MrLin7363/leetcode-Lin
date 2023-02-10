package A2023.路径算法;

import TenSortAlgorithm.greedy.Dijkstra;

import java.util.Arrays;

/**
 * Dijkstra
 */
public class P743网络延迟时间 {
    public int networkDelayTime(int[][] times, int n, int k) {
        // times数组转为 graph 距离数组
        int[][] graph = new int[n][n];
        // 设置为0
        for (int i = 0; i < n; ++i) {
            Arrays.fill(graph[i], 0);
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            graph[x][y] = t[2];
        }

        int[] dist = new Dijkstra().dijkstra(graph, k - 1, n);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dist.length; i++) {
            // 有节点到达不了
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, dist[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        new P743网络延迟时间().networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
    }
}
