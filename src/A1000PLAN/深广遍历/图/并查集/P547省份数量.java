package A1000PLAN.深广遍历.图.并查集;

import java.util.LinkedList;
import java.util.Queue;

/**
 *desc:
 *@author lin
 *@since 2023/11/9
 **/
public class P547省份数量 {
    // 1. DFS
    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int count = 0;
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(isConnected, visited, cities, i);
                count++;
            }
        }
        return count;
    }

    // 递归i城市到其他城市
    private void dfs(int[][] isConnected, boolean[] visited, int cities, int i) {
        for (int j = 0; j < cities; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, cities, j);
            }
        }
    }

    // 2.BFS
    public int findCircleNum3(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                queue.add(i);
                // 对i城市进行广度遍历，直到同一个连通变量的所有城市都被找到
                while (!queue.isEmpty()) {
                    Integer j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < cities; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.add(k);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        P547省份数量 p547省份数量 = new P547省份数量();
        int[][] req = new int[5][5];
        req[0][1] = 1;
        req[1][2] = 1;
        req[3][0] = 1;
        p547省份数量.findCircleNum(req);
    }

    // 3. 并查集
    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            // 优化不用走相同的i j 的访问
            // for (int j = i+1; j < n; j++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.count;
    }

    private class UnionFind {
        int count;

        int[] parent;

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            while (parent[i] != i) {
                i = parent[i];
            }
            return i;
        }

        public void union(int q, int p) {
            int qRoot = find(q);
            int pRoot = find(p);
            if (qRoot == pRoot) {
                return;
            }
            parent[qRoot] = pRoot;
            count--;
        }
    }
}
