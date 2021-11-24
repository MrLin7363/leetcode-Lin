/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package hw.dfsbfs;

/**
 * desc:
 *
 * @author c30021507
 * @since 2021/11/18
 **/
public class P695岛屿的最大面积 {

    /*
    DFS 访问到一个岛屿，扩散记录面积，将岛屿设置为海，下次不会再访问到这个岛屿
     */
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]==1){
                    ans=Math.max(ans,dfs(grid,i,j));
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] grid,int i,int j){
        // 超过边界或者不是岛屿
        if (i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]!=1){
            return 0;
        }
        // 已访问过节点设置为0
        grid[i][j]=0;
        int ans=1;
        ans+=dfs(grid,i+1,j);
        ans+=dfs(grid,i,j+1);
        ans+=dfs(grid,i-1,j);
        ans+=dfs(grid,i,j-1);
        return ans;
    }


}
