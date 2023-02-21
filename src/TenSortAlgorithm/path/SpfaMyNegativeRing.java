package TenSortAlgorithm.path;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 对 BellmanFord 的优化  判断负环   O M*N
 * 能够解决负环问题： 负环，又叫负权回路，负权环，指的是一个图中存在一个环，里面包含的边的边权总和<0
 * https://blog.51cto.com/u_3044148/4028313
 * <p>
 * 抽屉原理，如果一条正在搜索的[最短路径]上的点的个数大于总共点的个数，则说明路径上一定有至少重复的两个点，走了回头路
 */
public class SpfaMyNegativeRing {
    class Edge {
        private int start;

        private int end;

        private int w;

        public Edge(int a, int b, int c) {
            this.start = a;
            this.end = b;
            this.w = c;
        }
    }

    private List<Edge> es = new LinkedList<>();

    private int INF = Integer.MAX_VALUE;

    // 这里 k times 从下标0开始
    public boolean spfa(int[][] times, int n, int k) {
        for (int[] ts : times) {
            es.add(new Edge(ts[0] - 1, ts[1] - 1, ts[2]));
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k] = 0;

        // 初始化队列
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(k);

        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        while (!queue.isEmpty()) {
            int poll = queue.poll();
            visited[poll] = false;

            // 此处不是邻接边，是所有边了
            for (Edge edge : es) {
                if (dist[poll] + edge.w < dist[edge.end]) {
                    dist[edge.end] = dist[poll] + edge.w;
                    if (!visited[edge.end]) {
                        queue.add(edge.end);
                        visited[edge.end] = true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SpfaMyNegativeRing().spfa(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 5}, {3, 4, 1},
                {2,1,-1}}, 4, 1));
    }
}
