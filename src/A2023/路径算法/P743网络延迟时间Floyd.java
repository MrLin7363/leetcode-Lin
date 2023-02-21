package A2023.路径算法;

public class P743网络延迟时间Floyd {
    public int networkDelayTime(int[][] times, int n, int k) {
        int INF = Integer.MAX_VALUE;
        int[][] graph = new int[n][n];
        // 初始化graph  自己到自己距离为 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = graph[j][i] = i == j ? 0 : INF; // 自身到自身也可以设置为INF，到达不了的意思
            }
        }
        for (int[] t : times) {
            int u = t[0] - 1, v = t[1] - 1, d = t[2];
            graph[u][v] = d;
        }

        // floyd
        // 循环中转点
        for (int i = 0; i < n; i++) {
            // 所有入度 ji
            for (int j = 0; j < n; j++) {
                // 所有出度 iz
                for (int z = 0; z < n; z++) {
                    if (graph[j][i] != INF && graph[i][z] != INF) {
                        //  ji+iz <jz
                        if (graph[j][i] + graph[i][z] < graph[j][z] || graph[j][z] == INF) {
                            graph[j][z] = graph[j][i] + graph[i][z];
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 有一个节点到达不了
            if (graph[k - 1][i] == INF) {
                return -1;
            }
            ans = Math.max(ans, graph[k - 1][i]);
        }
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        //        new P743网络延迟时间Floyd().networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
        new P743网络延迟时间Floyd().networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 1}}, 3, 2);
        //        new P743网络延迟时间Floyd().networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2);
    }
}
