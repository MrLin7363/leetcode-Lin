package TenSortAlgorithm.greedy;

import java.util.Arrays;

/**
 * 源节点加入最短路径集合，先算出这个节点的周围路径长度。  继续加入 不属于这个集合的且到这个集合 路径最短的节点，再算边
 * 注意：graph是根据下标两点之间的值，如果到达不了则是0，不一定是lc题目那种
 */
public class Dijkstra {
    // 节点个数
    private int n;

    public int[] dijkstra(int graph[][], int src, int nodeCount) {
        this.n = nodeCount;
        // 源节点到其他节点的最短距离
        int dist[] = new int[n];

        // 最短路径节点集合
        Boolean visit[] = new Boolean[n];

        // 初始化
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            visit[i] = false;
        }
        dist[src] = 0; // 源节点到自身距离为0

        // 1.找节点 2.计算该节点扩展的边    -1是最后一轮循环确定倒数第二个节点时，也确定了到最后一个节点的边距离，所以可以省一轮遍历
        for (int i = 0; i < n - 1; i++) {
            // 找到下一个节点加入 最短路径节点集合
            int u = findNextNode(dist, visit);
            if (u == -1) {
                return dist;
            }
            visit[u] = true;

            // 根据这个节点计算 最短路径数组 排除自己到自己/达到不了
            for (int v = 0; v < n; v++) {
                if (!visit[v] && dist[u] != Integer.MAX_VALUE && graph[u][v] + dist[u] < dist[v]) {
                    dist[v] = graph[u][v] + dist[u];
                }
            }
        }
        return dist;
    }

    // 首次默认是源节点，找到距离最短的节点
    private int findNextNode(int[] dist, Boolean[] visit) {
        int u = -1;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            // <=注意，都在贪心到死胡同的时候不会找另外的节点
            if (!visit[j] && dist[j] <= min) {
                min = dist[j];
                u = j;
            }
        }
        return u;
    }

    public static void main(String[] args) {
        // 源，目标，距离
        int times[][] = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        // times数组转为 graph 距离数组
        int[][] graph = new int[4][4];
        // 设置为0
        for (int i = 0; i < 4; ++i) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            graph[x][y] = t[2];
        }

        Dijkstra t = new Dijkstra();
        t.dijkstra(graph, 1, 4);
    }
}
