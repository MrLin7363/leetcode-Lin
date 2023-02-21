package TenSortAlgorithm.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 存图方式：类
 * 理解 https://blog.csdn.net/m0_58151858/article/details/124204854
 *
 * 防止串联原理  https://blog.csdn.net/qq_52905520/article/details/126453516
 *
 * 经过的边数最多为N的最短路  ,  松弛操作
 * 有点像BFS 每次找出源点到其他顶点经过一条边最短路
 *
 * 1.用dis数组记录点到有向图的任意一点距离，初始化起点距离为0，其余点均为INF，起点入队。
 *
 * 2.判断该点是否存在。（未存在就入队，标记）
 *
 * 3.队首出队，并将该点标记为没有访问过，方便下次入队。
 *
 * 4.遍历以对首为起点的有向边（t,i）,如果dis[i]>dis[t]+w(t,i),则更新dis[i]。
 *
 * 5.如果i不在队列中，则入队标记，一直到循环为空。
 *
 */
public class BellmanFord2 {
    class Edge {
        int start, end, weight;

        Edge(int _a, int _b, int _c) {
            start = _a;
            end = _b;
            weight = _c;
        }
    }

    int N = 110, M = 6010;

    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    int[] dist = new int[N];

    int INF = 0x3f3f3f3f; // MAX/2

    int n, k;

    // 使用类进行存边
    List<Edge> es = new ArrayList<>();

    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n = _n;
        k = _k;
        // 存图
        for (int[] t : ts) {
            int u = t[0], v = t[1], c = t[2];
            es.add(new Edge(u, v, c));
        }
        // 最短路
        bf();
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        return ans > INF / 2 ? -1 : ans;
    }

    void bf() {
        // 起始先将所有的点标记为「距离为正无穷」
        Arrays.fill(dist, INF);
        // 只有起点最短距离为 0
        dist[k] = 0;
        //经过 n - 1次松驰
        //对所有边进行一次松弛操作，就求出了源点到所有点，经过的边数最多为1的最短路
        //对所有边进行两次松弛操作，就求出了源点到所有点，经过的边数最多为2的最短路
        //....
        //对所有边进行n- 1次松弛操作，就求出了源点到所有点，经过的边数最多为n - 1的最短路
        for (int p = 0; p < n-1; p++) {
            int[] prev = dist.clone();
            // 每次都使用上一次迭代的结果，执行松弛操作
            for (Edge e : es) {
                int a = e.start, b = e.end, c = e.weight;
                // 使用MAX/2可以预防加上 = 负数， 这样prev[a]=MAx/2时+c最小的还是dist[b]
                dist[b] = Math.min(dist[b], prev[a] + c);
            }
        }
    }

    public static void main(String[] args) {
        new BellmanFord2().networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
    }
}
