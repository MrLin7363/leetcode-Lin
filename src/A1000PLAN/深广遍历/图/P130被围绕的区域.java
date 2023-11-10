package A1000PLAN.深广遍历.图;

/**
 *desc:
 *@author lin
 *@since 2023/11/8
 **/
public class P130被围绕的区域 {
    /*
    外层的不会被包围，标记与外层O相连的所有O为A
    剩下的所有的O 换成X，A换回为O
     */
    public void solve(char[][] board) {
        // 外层填充A
        // for (int i = 0; i < board.length; i++) {
        //     for (int j = 0; j < board[0].length; j++) {
        //         if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
        //             dfs(board, i, j);
        //         }
        //     }
        // }
        for (int i = 0; i < board.length; i++) {
            // 第一列
            dfs(board, i, 0);
            // 最后一列
            dfs(board, i, board[0].length - 1);
        }
        for (int i = 1; i < board[0].length - 1; i++) {
            // 第一行,不包括 第一个和最后一个元素
            dfs(board, 0, i);
            // 最后一行
            dfs(board, board.length - 1, i);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private int[] direction = new int[] {1, 0, -1, 0, 1};

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'A';
        for (int k = 0; k < 4; k++) {
            int row = i + direction[k];
            int col = j + direction[k + 1];
            dfs(board, row, col);
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'O', 'X'}};
        new P130被围绕的区域().solve(board);
    }
}
