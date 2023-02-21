package TenSortAlgorithm.path;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 堆优化 - Dijkstra  O(mlogn+n)
 * 邻接表（数组）
 * 这也是一种在图论中十分常见的存图方式，与数组存储单链表的实现一致（头插法）。
 *
 * 这种存图方式又叫链式前向星存图。
 *
 * 适用于边数较少的稀疏图使用，当边数量接近点的数量， m=n
 * 下标0开始
 */
public class DijistraHeapMy {
    // 头节点指向的边
    private int[] srcToEdge;

    // 边指向节点
    private int[] edgeToDist;

    // 边的下一条边，同一个出发节点src   简称邻接边
    private int[] nextEdge;

    // 边的权重
    private int[] w;

    private int[] dist;

    private boolean[] visited;

    private int idx;

    private int INF = Integer.MAX_VALUE;

    private void add(int srcV, int distV, int weight) {
        this.edgeToDist[idx] = distV;
        this.nextEdge[idx] = this.srcToEdge[srcV];
        this.srcToEdge[srcV] = idx;
        this.w[idx] = weight;
        idx++;
    }

    private void init(int n) {
        visited = new boolean[n];
        dist = new int[n];
        w = new int[6000];
        nextEdge = new int[6000]; // 题目要求的边最多6000
        srcToEdge = new int[n]; // 头节点数量
        edgeToDist = new int[6000];
        Arrays.fill(srcToEdge, -1);
        Arrays.fill(nextEdge, -1);
        Arrays.fill(edgeToDist, -1);
        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);
    }

    // k:源节点的下标，从0开始
    public int[] dijkstra(int[][] times, int n, int k) {
        // 初始化数组
        init(n);
        for (int[] ts : times) {
            // 从下标0开始
            add(ts[0] - 1, ts[1] - 1, ts[2]);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{k, 0});
        dist[k] = 0;

        while (!queue.isEmpty()) {
            // 确定点
            int v = queue.poll()[0];
            if (visited[v]) {
                continue;
            }
            visited[v] = true;

            // 确定最短集合边
            for (int i = srcToEdge[v]; i != -1; i = nextEdge[i]) {
                int distNode = edgeToDist[i];

                System.out.println("结点" + v + " -> 结点" + distNode);
                System.out.println("编号为" + idx + "的边, 权重为: " + w[idx]);

                // 由于是通过边数组确定的，所以这里不用判断 !=INF 因为都是能通的边
                if (w[i] + dist[v] < dist[distNode]) {
                    dist[distNode] = w[i] + dist[v];
                    queue.add(new int[]{distNode, dist[distNode]});
                }
            }
        }
        return dist;
    }
}
