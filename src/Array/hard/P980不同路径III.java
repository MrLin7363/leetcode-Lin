package Array.hard;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/9
  *@Describe:
 */

public class P980不同路径III {
    /*
    回溯  21 + 29
    所有无障碍都要走一遍
     */
    int finishStep; // 总要完成的步数
    int result; // 方案数
    int moveCount; // 当前移动的步数
    int[][] myGrid;

    public int uniquePathsIII(int[][] grid) {
        int row=-1, col=-1; // 起始位置
        finishStep=1; //起始点要走
        // 计算总要走的步数
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 记录起始位置
                if (grid[i][j]==1){
                    row=i;
                    col=j;
                }else if (grid[i][j]==0){ // 无障碍
                    finishStep++;
                }
            }
        }
        myGrid=grid; // 不改变原来的二维数组
        moveCount=0;
        result=0;
        myGrid[row][col]=0; // 让起始位置作为一条路径
        dfs(row,col);
        myGrid[row][col]=1; // 不恢复也行
        return result;
    }
    public void dfs(int row,int col) {
        if (moveCount == finishStep && myGrid[row][col] == 2) {
            result++;
            return;
        }
        if (myGrid[row][col] != 0) {
            return;
        }
        // 下面是无障碍可走的
        moveCount++;
        myGrid[row][col] = -1; // 走过
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 上 右 下 左  不一定按顺序也行
        for (int[] direction : directions) {
            int newRow=row+direction[0],newCol=col+direction[1];
            // 判断位置是否在二维数组可用位置
            if (newRow>=0 && newRow<myGrid.length &&newCol>=0 && newCol<myGrid[0].length){
                dfs(newRow,newCol);
            }
        }
        // 回溯
        myGrid[row][col]=0;
        moveCount--;
    }
}
