package A1000PLAN.树.广度;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * desc:
 *
 * @author lin
 * @since 2023/12/7
 **/
public class P310最小高度树 {
    /*
    推荐
    拓扑排序(类似)：每次消除最外一层结点. 构成最小高度的结点在最中间
    https://leetcode.cn/problems/minimum-height-trees/solutions/242910/zui-rong-yi-li-jie-de-bfsfen-xi-jian-dan-zhu-shi-x/
    1.计算度（多少条边连这个结点） 出入度都算
    2. 度=1的结点 分批次入队列
    3. 最后一批就是结果集
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        List<List<Integer>> edgeList = new ArrayList<>(); // 邻接表
        for (int i = 0; i < n; i++) {
            edgeList.add(new LinkedList<>());
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            edgeList.get(edge[0]).add(edge[1]);
            edgeList.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans.clear();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                ans.add(poll);
                for (Integer cur : edgeList.get(poll)) {
                    degree[cur]--;
                    if (degree[cur] == 1) {
                        queue.offer(cur);
                    }
                }
            }
        }
        return ans;
    }

    /*
    最长路径的中间结点-广搜
    https://leetcode.cn/problems/minimum-height-trees/solutions/1395249/zui-xiao-gao-du-shu-by-leetcode-solution-6v6f/
    1.以任意节点 p 出现，利用广度优先搜索或者深度优先搜索找到以 p 为起点的最长路径的终点 x
    以节点 x 出发，找到以 x 为起点的最长路径的终点 y
    x 到 y 之间的路径即为图中的最长路径，找到路径的中间节点即为根节点

    2. 记录第二次过程经过的结点

    3.路径为偶数，则有两个最小高度树

     */
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        List<List<Integer>> edgeList = new ArrayList<>(); // 邻接表
        for (int i = 0; i < n; i++) {
            edgeList.add(new LinkedList<>());
        }
        for (int[] edge : edges) {
            edgeList.get(edge[0]).add(edge[1]);
            edgeList.get(edge[1]).add(edge[0]);
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        /* 找到与节点 0 最远的节点 x */
        int x = findLongestNode(0, edgeList, parent); // 第一条路径其实可以不用记录parent,因为第二条会覆盖一些值
        /* 找到与节点 x 最远的节点 y */
        int y = findLongestNode(x, edgeList, parent);

        /* 求出节点 x 到节点 y 的路径 */
        // 最后一个结点需要设置为-1，便于y找到x
        parent[x] = -1;
        List<Integer> path = new ArrayList<>();
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        if ((path.size() & 1) == 0) {
            ans.add(path.get((path.size() - 1) / 2));
        }
        ans.add(path.get(path.size() / 2));
        return ans;
    }

    private Integer findLongestNode(Integer node, List<List<Integer>> edgeList, int[] parent) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        int ans = -1;
        boolean[] visit = new boolean[edgeList.size()];
        visit[node] = true;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            ans = poll;
            for (Integer cur : edgeList.get(poll)) {
                if (!visit[cur]) {
                    visit[cur] = true;
                    parent[cur] = poll;
                    queue.offer(cur);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        new P310最小高度树().findMinHeightTrees2(4, new int[][] {{1, 0}, {1, 2}, {1, 3}});
        new P310最小高度树().findMinHeightTrees2(6, new int[][] {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}});
        new P310最小高度树().findMinHeightTrees(4, new int[][] {{1, 0}, {1, 2}, {1, 3}});
    }
}
