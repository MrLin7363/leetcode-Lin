package TenSortAlgorithm.greedy;

import java.util.Arrays;

public class DikstraLc {
    /**
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x][y] = t[2];
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k-1] = 0;
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; ++i) {
//            int x = -1;
//            for (int y = 0; y < n; ++y) {
//                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
//                    x = y;
//                }
//            }
            int x = findNextNode(dist, used, n);
            if (x == -1) {
                return -1;
            }
            used[x] = true;
            // x=2
            for (int y = 0; y < n; ++y) {
                // 不通的边不考虑
                if (g[x][y] != Integer.MAX_VALUE) {
                    dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
                }
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    // 首次默认是源节点，找到距离最短的节点
    private int findNextNode(int[] dist, boolean[] visit, int n) {
        int u = -1;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if (!visit[j] && dist[j] < min) {
                min = dist[j];
                u = j;
            }
        }
        return u;
    }

    public static void main(String[] args) {
        new DikstraLc().networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
    }
}
