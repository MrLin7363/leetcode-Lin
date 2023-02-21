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
 * 某个点的入队数大于了n，证明他在不停得松弛
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

    // 这里times 从下标0开始
    public boolean spfa(int[][] times, int n) {
        // 初始化队列
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 把所有点全部放入到队列中，因为我们不是要找从1点出发的负环，而是要找整个图中的负环
            // 每个点都相当于虚拟源点，只要从这个点出发的最短路径上，某个点入队超过N，就是有负环
            queue.add(i);
        }

        for (int[] ts : times) {
            es.add(new Edge(ts[0] - 1, ts[1] - 1, ts[2]));
        }

        // 都设置为0，只有负数的情况下才会是最短路径,没有负数就没有负环
        int[] dist = new int[n];

        // 源点到下标点的最短路径的边数量
        int[] cnt = new int[n];

        boolean[] visited = new boolean[n];

        while (!queue.isEmpty()) {
            int poll = queue.poll();
            visited[poll] = false;

            // 此处不是邻接边，是所有边了
            for (Edge edge : es) {
                // 由于所有的最短路径 dist都是0，所以只有负数的才会是最短路径
                if (dist[poll] + edge.w < dist[edge.end]) {
                    dist[edge.end] = dist[poll] + edge.w;

                    // 到达的点入队的数标为前面这个点入队的次数+1 , 假设循环是好几个点负边连起来的就明白了，  1->2->3>1  权值都为负数
                    cnt[edge.end] = cnt[poll] + 1;
                    if (cnt[edge.end] >= n) {
                        return false;
                    }

                    if (!visited[edge.end]) {
                        queue.add(edge.end);
                        visited[edge.end] = true;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SpfaMyNegativeRing().spfa(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 5}, {3, 4, 1},
            {2, 1, -1}}, 4));
    }
}
