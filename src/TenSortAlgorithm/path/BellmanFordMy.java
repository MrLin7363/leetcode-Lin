package TenSortAlgorithm.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 存图方式：类   O(n∗m)
 * 理解 https://blog.csdn.net/m0_58151858/article/details/124204854
 * <p>
 * 防止串联原理  https://blog.csdn.net/qq_52905520/article/details/126453516
 * <p>
 * 经过的边数最多为N的最短路  ,  松弛操作
 * 有点像BFS 每次找出源点到其他顶点经过一条边最短路
 */
public class BellmanFordMy {
    class Edge {
        int start, end, weight;

        Edge(int _a, int _b, int _c) {
            start = _a;
            end = _b;
            weight = _c;
        }
    }

    // 使用类进行存边
    List<Edge> es = new ArrayList<>();

    private int INF = Integer.MAX_VALUE;

    public int[] bf(int[][] times, int n, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k] = 0;

        for (int[] ts : times) {
            es.add(new Edge(ts[0] - 1, ts[1] - 1, ts[2]));
        }

        //经过 n - 1次松驰
        //对所有边进行一次松弛操作，就求出了源点到所有点，经过的边数最多为1的最短路
        //对所有边进行两次松弛操作，就求出了源点到所有点，经过的边数最多为2的最短路
        //....
        //对所有边进行n- 1次松弛操作，就求出了源点到所有点，经过的边数最多为n - 1的最短路
        for (int i = 0; i < n - 1; i++) {
            // 每次都使用上一次迭代的结果，执行松弛操作
            int[] prev = dist.clone();
            for (Edge e : es) {
                if (prev[e.start] != INF && prev[e.start] + e.weight < dist[e.end]) {
                    dist[e.end] = prev[e.start] + e.weight;
                }
            }
        }
        return dist;
    }
}
