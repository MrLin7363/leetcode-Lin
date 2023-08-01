package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/19
  *@Describe:
 */

public class P79单词搜索 {
    /*
    二维数组，搜索字符串 : 回溯 31 + 93
     */
    public boolean exist(char[][] board, String word) {
        int height=board.length, width=board[0].length;
        boolean[][] visited=new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean flag=backtrack(board,visited,i,j,word,0);
                if (flag){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean backtrack(char[][] board,boolean[][] visited,int i,int j,String word, int idx){
        if (board[i][j]!=word.charAt(idx)){
            return false;
        } else if (idx==word.length()-1){
            return true;
        }
        visited[i][j]=true;
        int[][] directions= { {-1,0},{0,1},{1,0},{0,-1} }; // 上 右 下 左  不一定按顺序也行
        for (int[] direction:directions){
            int newi=i+direction[0], newj=j+direction[1]; // 移动位置
            if (newi>=0 && newi<board.length && newj>=0 && newj<board[0].length){ // 判断位置是否在二维数组中
                if (!visited[newi][newj]){ //为被访问过，防止从来的位置又回朔还可以
                    boolean flag=backtrack(board,visited,newi,newj,word,idx+1);
                    if (flag==true){
                        return true;
                    }
                }
            }
        }
        visited[i][j]=false;
        return false;
    }

}
