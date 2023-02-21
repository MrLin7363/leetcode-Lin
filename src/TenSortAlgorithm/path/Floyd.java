package TenSortAlgorithm.path;

/**
 * 动态规划 - 邻接矩阵    O N^3
 *
 * 跑一遍 Floyd，可以得到「从任意起点出发，到达任意起点的最短距离   时间复杂度On 3 > Dijkstra On 2
 *
 * P743网络延迟时间Floyd
 *
 * 通过循环每个中转点求路径
 */
public class Floyd {
    private static int INF = Integer.MAX_VALUE;

    /**
     * 距离矩阵   自身为0，到达不了为INF
     */
    public static int[][] distance;
    /**
     * 路径矩阵
     */
    public static int[][] path; // 一些求最短距离的点不需要记录路径矩阵也行

    public static void floyd(int[][] graph) {
        //初始化距离矩阵 distance
        distance = graph;
        //初始化路径
        path = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                path[i][j] = j;
            }
        }
        //开始 Floyd 算法
        //每个点为中转
        for (int i = 0; i < graph.length; i++) {
            //所有入度
            for (int j = 0; j < graph.length; j++) {
                //所有出度
                for (int k = 0; k < graph[j].length; k++) {
                    //以每个点为「中转」，刷新所有出度和入度之间的距离
                    //例如 AB + BC < AC 就刷新距离
                    if (graph[j][i] != INF && graph[i][k] != INF) {
                        // 如果两点到达不了也只能是 走中转节点
                        if (graph[j][i] + graph[i][k] < graph[j][k] || graph[j][k] == INF) {
                            //刷新距离
                            graph[j][k] = graph[j][i] + graph[i][k];
                            //刷新路径
                            path[j][k] = i;
                        }
                    }
                }
            }
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
//        int[][] graph = new int[][]{
//            {0, 2, INF, 6}
//            , {2, 0, 3, 2}
//            , {INF, 3, 0, 2}
//            , {6, 2, 2, 0}};
        // 以下是转换后的 graph
        int[][] graph = new int[][]{
            {0, 1, INF, INF}
            , {INF, 0, 1, INF}
            , {INF, INF, 0, 1}
            , {INF, INF, INF, 0}};

        floyd(graph);
        System.out.println("====distance====");
        // distance根据graph 修改为最短路径数组
        for (int[] ints : distance) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println("====path====");
        for (int[] ints : path) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

}
