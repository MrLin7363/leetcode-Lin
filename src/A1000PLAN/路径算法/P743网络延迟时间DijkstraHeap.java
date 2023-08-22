package A1000PLAN.路径算法;

import TenSortAlgorithm.path.DijistraHeapMy;

/**
 * Dijkstra - 堆优化/邻接表
 */
public class P743网络延迟时间DijkstraHeap {
    public int networkDelayTime(int[][] times, int n, int k) {
        int INF = Integer.MAX_VALUE;
        // 从0下标开始
        int[] dist = new DijistraHeapMy().dijkstra(times, n, k - 1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dist.length; i++) {
            max = Math.max(max, dist[i]);
        }
        return max == INF ? -1 : max;
    }

    public static void main(String[] args) {
        new P743网络延迟时间DijkstraHeap().networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 2}, {3, 4, 1}}, 4, 2);
    }
}
