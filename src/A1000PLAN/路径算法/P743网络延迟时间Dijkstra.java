package A1000PLAN.路径算法;

import TenSortAlgorithm.path.Dijkstra;

import java.util.Arrays;

/**
 * Dijkstra - 朴素/邻接矩阵
 */
public class P743网络延迟时间Dijkstra {
    public int networkDelayTime(int[][] times, int n, int k) {
        int INF = Integer.MAX_VALUE;
        // times数组转为 graph 邻接矩阵
        int[][] graph = new int[n][n];
        // 设置为0
        for (int i = 0; i < n; ++i) {
            Arrays.fill(graph[i], INF);
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            graph[x][y] = t[2];
        }

        int[] dist = new Dijkstra().dijkstra(graph, n, k - 1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dist.length; i++) {
            max = Math.max(max, dist[i]);
        }
        return max == INF ? -1 : max;
    }

    public static void main(String[] args) {
        new P743网络延迟时间Dijkstra().networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 1);
    }
}
