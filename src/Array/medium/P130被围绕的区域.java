package Array.medium;

/**
 * @author: chenjunlin
 * @since: 2021/08/23
 * @descripe: 被围绕的区域
 */
public class P130被围绕的区域 {

    /*
    外层的不会被包围，标记与外层O相连的所有O为A
    剩下的所有的O 换成X，A换回为O
     */
    int wide;
    int height;
    public void solve(char[][] board) {
        height=board.length; // n
        wide=board[0].length; // m
        // 外层遍历标志
        for (int i = 0; i < height; i++) {
            // 第一列
            dfs(board,i,0);
            // 最后一列
            dfs(board,i,wide-1);
        }
        for (int i = 1; i < wide-1; i++) {
            // 第一行,不包括 第一个和最后一个元素
            dfs(board,0,i);
            // 最后一列
            dfs(board,height-1,i);
        }
        // 最终全遍历一次
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < wide; j++) {
                if (board[i][j]=='A'){
                    board[i][j]='O';
                }else if (board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }
    }

    private void dfs(char[][] board,int x,int y) {
        if (x < 0 || x >= height || y < 0 || y >= wide || board[x][y] != 'O') {
            return;
        }
        // 相连的O = A
        board[x][y] = 'A';
        dfs(board,x-1,y); // 上
        dfs(board,x,y+1); // 右
        dfs(board,x+1,y); // 下
        dfs(board,x,y-1);// 左
    }

}
