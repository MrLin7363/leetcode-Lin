package TenSortAlgorithm.path;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 对 BellmanFord 的优化  求最短路径   O M*N
 * 能够解决负环问题： 负环，又叫负权回路，负权环，指的是一个图中存在一个环，里面包含的边的边权总和<0
 * <p>
 * 与Dikstra类似，D是类似DFS找最短距离的点深入，SPFA是类似BFS每次从相邻节点扩展，而且访问过可以重复访问，可以解决负环问题
 * <p>
 * https://blog.csdn.net/m0_64045085/article/details/123547253
 */
public class SpfaMy {
    // 边到节点
    private int[] edgeToNode;

    // 邻接边
    private int[] nextEdge;

    // 结点到边
    private int[] srcToEdge;

    // 边权重
    private int[] w;

    // 第几条边,从0开始
    private int idx;

    private boolean[] visited;

    private int INF = Integer.MAX_VALUE;

    // 这里 k times 从下标1开始
    public int[] spfa(int[][] times, int n, int k) {
        // 初始化最短距离
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[k] = 0;

        // 初始化是否访问过 由于下表从1开始所以n+1
        visited = new boolean[n + 1];
        Arrays.fill(visited, false);
        visited[k] = true;

        // 初始化表
        edgeToNode = new int[6000]; // 题目最多6000条边
        nextEdge = new int[6000];
        srcToEdge = new int[n + 1]; // 由于下表从1开始所以n+1
        w = new int[6000];

        // 初始化邻接表
        Arrays.fill(srcToEdge, -1);
        Arrays.fill(nextEdge, -1);
        for (int[] ts : times) {
            edgeToNode[idx] = ts[1];
            nextEdge[idx] = srcToEdge[ts[0]];
            srcToEdge[ts[0]] = idx;
            w[idx] = ts[2];
            idx++;
        }

        // 初始化队列
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(k);

        while (!queue.isEmpty()) {
            System.out.print("队列此时为" + queue);
            Integer poll = queue.poll();
            visited[poll] = false; // 这是能够探测负权的操作，如果原结点的距离更短，则最短路径继续能探测原结点
            System.out.println("，更新" + poll + "结点");

            // 循环点的邻接边  i是边  j是结点
            for (int i = srcToEdge[poll]; i != -1; i = nextEdge[i]) {
                int j = edgeToNode[i];
                if (dist[poll] + w[i] < dist[j]) {
                    dist[j] = dist[poll] + w[i];
                    System.out.println("计算 " + poll + " 至 " + j + " ，到 " + j + " 最短距离为 " + dist[j]);

                    if (!visited[j]) {
                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }
        }
        return dist;
    }
}
