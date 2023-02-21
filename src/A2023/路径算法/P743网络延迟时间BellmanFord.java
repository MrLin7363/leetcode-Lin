package A2023.路径算法;

import TenSortAlgorithm.path.BellmanFordMy;

public class P743网络延迟时间BellmanFord {
    public int networkDelayTime(int[][] times, int n, int k) {
        int INF = Integer.MAX_VALUE;
        // 从0下标开始
        int[] dist = new BellmanFordMy().bf(times, n, k - 1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dist.length; i++) {
            max = Math.max(max, dist[i]);
        }
        return max == INF ? -1 : max;
    }

    public static void main(String[] args) {
        //        new P743网络延迟时间BellmanFord().networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 2}, {3, 4, 1}}, 4, 2);
        //        new P743网络延迟时间BellmanFord().networkDelayTime(new int[][]{{1, 2, 1}, {2, 1, 3}}, 2, 2);
        new P743网络延迟时间BellmanFord().networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
    }

}
