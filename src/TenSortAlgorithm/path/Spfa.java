package TenSortAlgorithm.path;

import A1000PLAN.路径算法.P743网络延迟时间Spfa;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 对 BellmanFord 的优化     O M*N
 * 能够解决负环问题： 负环，又叫负权回路，负权环，指的是一个图中存在一个环，里面包含的边的边权总和<0
 * <p>
 * 与Dikstra类似，D是类似DFS找最短距离的点深入，SPFA是类似BFS每次从相邻节点扩展，而且访问过可以重复访问，可以解决负环问题
 * <p>
 *
 * 1. 初始化 dist 数组
 * 2.
 * https://blog.csdn.net/m0_64045085/article/details/123547253
 */
public class Spfa {
    int N = 110, M = 6010;

    // 邻接表
    int[] he = new int[N], e = new int[M], ne = new int[M], w = new int[M];

    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    int[] dist = new int[N];

    // 记录哪一个点「已在队列」中
    boolean[] vis = new boolean[N];

    int INF = 0x3f3f3f3f;

    // idx 从 0 开始
    int n, k, idx;

    void add(int a, int b, int c) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx;
        w[idx] = c;
        idx++;
    }

    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n = _n;
        k = _k;
        // 初始化链表头
        Arrays.fill(he, -1);
        // 存图
        for (int[] t : ts) {
            int u = t[0], v = t[1], c = t[2];
            add(u, v, c);
        }
        // 最短路
        spfa();
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        return ans > INF / 2 ? -1 : ans;
    }

    void spfa() {
        // 起始先将所有的点标记为「未入队」和「距离为正无穷」
        Arrays.fill(vis, false);
        Arrays.fill(dist, INF);
        // 只有起点最短距离为 0
        dist[k] = 0;
        // 使用「双端队列」存储，存储的是点编号
        Deque<Integer> d = new ArrayDeque<>();
        // 将「源点/起点」进行入队，并标记「已入队」
        d.addLast(k);
        vis[k] = true;
        while (!d.isEmpty()) {
            System.out.print("队列此时为" + d);
            // 每次从「双端队列」中取出，并标记「未入队」
            int poll = d.pollFirst();
            vis[poll] = false;
            System.out.println("，更新" + poll + "结点");

            // 尝试使用该点，更新其他点的最短距离
            // 如果更新的点，本身「未入队」则加入队列中，并标记「已入队」
            for (int i = he[poll]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[poll] + w[i]) {
                    dist[j] = dist[poll] + w[i];
                    System.out.println("计算 " + poll + " 至 " + j + " ，到 " + j + " 最短距离为 " + dist[j]);

                    if (!vis[j]) {
                        d.addLast(j);
                        vis[j] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Spfa().networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 5}, {3, 4, 1}}, 4, 1);
        //                new Spfa().networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 5}, {3, 4, 1}}, 4, 1);
    }
}
