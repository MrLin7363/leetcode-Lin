package A2023.路径算法;

import TenSortAlgorithm.path.SpfaMy;

public class P743网络延迟时间Spfa {
    public int networkDelayTime(int[][] ts, int n, int k) {

        // 最短路
        int[] dist = new SpfaMy().spfa(ts, n, k);
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        new P743网络延迟时间Spfa().networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 5}, {3, 4, 1}}, 4
            , 1);
    }
}
