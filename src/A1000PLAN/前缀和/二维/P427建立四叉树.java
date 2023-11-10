package A1000PLAN.前缀和.二维;

import Construct.foruCha.Node;

/**
 *desc:
 *@author
 *@since 2023/11/7
 **/
public class P427建立四叉树 {
    /*
    2.二维前缀和(更优解)， 类似304 在O 1 时间里找到是否全为0或1
     */
    private int[][] presum;

    public Node construct(int[][] grid) {
        presum = new int[grid.length + 1][grid[0].length + 1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                presum[i + 1][j + 1] = presum[i][j + 1] + presum[i + 1][j] - presum[i][j] + grid[i][j];
            }
        }
        return dfs(grid, 0, 0, grid.length, grid[0].length);
    }

    private Node dfs(int[][] grid, int r1, int l1, int r2, int l2) {
        // 注意这里的r2,l2是length,所以不用+1
        int total = presum[r2][l2] - presum[r1][l2] - presum[r2][l1] + presum[r1][l1];
        if (total == 0) {
            return new Node(false, true);
        } else if (total == (r2 - r1) * (l2 - l1)) {
            return new Node(true, true);
        }
        return new Node(true, false, dfs(grid, r1, l1, (r1 + r2) / 2, (l1 + l2) / 2),
            dfs(grid, r1, (l1 + l2) / 2, (r1 + r2) / 2, l2), dfs(grid, (r1 + r2) / 2, l1, r2, (l1 + l2) / 2),
            dfs(grid, (r1 + r2) / 2, (l1 + l2) / 2, r2, l2));
    }

    /*
    1.递归
     */
    public Node construct2(int[][] grid) {
        return dfs2(grid, 0, 0, grid.length, grid[0].length);
    }

    private Node dfs2(int[][] grid, int r1, int l1, int r2, int l2) {
        boolean same = true;
        for (int i = r1; i < r2; i++) {
            for (int j = l1; j < l2; j++) {
                if (grid[i][j] != grid[r1][l1]) {
                    same = false;
                    break;
                }
            }
            if (!same) {
                break;
            }
        }
        if (same) {
            return new Node(grid[r1][l1] == 1, true);
        }
        // 有叶子节点当前节点的值true false都行
        return new Node(true, false, dfs2(grid, r1, l1, (r1 + r2) / 2, (l1 + l2) / 2),
            dfs2(grid, r1, (l1 + l2) / 2, (r1 + r2) / 2, l2), dfs2(grid, (r1 + r2) / 2, l1, r2, (l1 + l2) / 2),
            dfs2(grid, (r1 + r2) / 2, (l1 + l2) / 2, r2, l2));
    }
}
