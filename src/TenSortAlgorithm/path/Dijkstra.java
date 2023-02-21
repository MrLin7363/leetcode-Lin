package TenSortAlgorithm.path;

import java.util.Arrays;

/**
 * 贪心  O N^2
 * 1.找距离最短的节点 2.计算该节点扩展的边
 *
 * 源节点加入最短路径集合，先算出这个节点的周围路径长度。  继续加入 不属于这个集合的且到这个集合 路径最短的节点，再算边
 * 注意：graph是根据下标两点之间的值，如果到达不了则设置为Integer.MAXVALUE，不一定是lc题目那种
 *
 * P743网络延迟时间   https://leetcode.cn/problems/network-delay-time/solution/gong-shui-san-xie-yi-ti-wu-jie-wu-chong-oghpz/
 * 存图方式
 * 在开始讲解最短路之前，我们先来学习三种「存图」方式。
 *
 * 邻接矩阵
 * 这是一种使用二维矩阵来进行存图的方式。
 *
 * 适用于边数较多的稠密图使用，当边数量接近点的数量的平方
 */
public class Dijkstra {

    public int[] dijkstra(int[][] graph, int n, int k) {
        int INF = Integer.MAX_VALUE;
        // 源节点到其他节点的最短距离
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        // 源节点到自身距离为0
        dist[k] = 0;

        // 最短路径节点集合
        boolean[] visited = new boolean[n];

        // 1.找距离最短的节点 2.计算该节点扩展的边
        // n -1  也行，是最后一轮循环确定倒数第二个节点时，也确定了到最后一个节点的边距离，可以省一轮遍历
        for (int i = 0; i < n; ++i) {
            int x = findNextNode(dist, visited, n);
            // 源节点无法到达任务一个点
            if (x == -1) {
                return dist;
            }
            visited[x] = true;
            for (int y = 0; y < n; ++y) {
                // 根据这个节点计算 最短路径数组 不通的边不考虑
                if (graph[x][y] != Integer.MAX_VALUE && dist[x] + graph[x][y] < dist[y]) {
                    dist[y] = dist[x] + graph[x][y];
                }
            }
        }

        return dist;
    }

    // 首次默认是源节点，找到距离最短的节点 这里是遍历所有的点到该点距离
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

    // 下面也可以这样找最短节点  代替 findNextNode()，但是不通的节点也会加入计算，多余了点步骤
    //            int x = -1;
    //            for (int y = 0; y < n; ++y) {
    //                if (!visited[y] && (x == -1 || dist[y] < dist[x])) {
    //                    x = y;
    //                }
    //            }
}
