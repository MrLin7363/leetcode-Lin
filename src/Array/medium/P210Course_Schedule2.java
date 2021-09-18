package Array.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: Junlin Chen
 * @Date: 2021/09/16 16:56
 * @Describe: 拓扑排序
 */
public class P210Course_Schedule2 {
    // 存储有向图，第一个list纪录节点，第二个list纪录该节点所指向的节点
    static List<List<Integer>> edges;
    // 存储每个节点的入度
    static int[] indeg;
    // 存储答案
    static int[] result;
    // 答案下标
    static int index = 0;

    /*
    BFS
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        indeg = new int[numCourses];
        result = new int[numCourses];
        // 初始化有向图
        for (int[] info : prerequisites) {
            // prerequisites 完成 info 1 前要完成 info 0
            edges.get(info[1]).add(info[0]);
            indeg[info[0]]++;
        }
        // 将入度为0的放入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            // 队列首部取出节点
            int u = queue.poll();
            result[index++] = u;
            // 相邻节点
            for (int v : edges.get(u)) {
                indeg[v]--;
                // 如果相邻节点度数为0，则入队列
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        if (index != numCourses) {
            return new int[0];
        }
        return result;
    }

    public static void main(String[] args) {
        findOrder(2, new int[][]{{0, 1}, {1, 0}});
        findOrderDFS(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
//        findOrderDFS(2,new int[][]{{1,0}});
    }

    /*
    ---------------------------------------------------------
    DFS
     */
    // 存储有向图，第一个list纪录节点，第二个list纪录该节点所指向的节点
    static List<List<Integer>> graph;
    // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
    static int[] visited;
    // 存用数组来模拟栈，下标 n-1 为栈底，0 为栈顶
    static int[] stack;
    // 判断有向图中是否有环
    static boolean valid = true;
    // 栈下标
    static int zIndex;

    public static int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        stack = new int[numCourses];
        zIndex = numCourses - 1;
        // 初始化有向图
        for (int[] info : prerequisites) {
            // prerequisites 完成 info 1 前要完成 info 0
            graph.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        if (!valid) {
            return new int[0];
        }
        // 如果没有环，那么就有拓扑排序
        return stack;
    }

    private static void dfs(int u) {
        // 将节点标记为「搜索中」
        visited[u] = 1;
        for (int v : graph.get(u)) {
            // 如果「搜索中」说明找到了环
            if (visited[v] == 1) {
                valid = false;
                return;
            }
            // 如果「未搜索」那么搜索相邻节点
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            }
        }
        // 将节点标记为「已完成」
        visited[u] = 2;
        stack[zIndex--] = u;
    }


}
