package A1000PLAN.深广遍历.图.八方向遍历;

/**
 *desc:
 *@author lin
 *@since 2023/11/15
 **/
public class P289生命游戏 {
    /*
    1->2 活细胞死亡
    1->1 活细胞不变
    0->3 死细胞复活
    1,2都算活细胞
    最后2->0,3->1
     */
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int liveCount = checkLiveCount(board, i, j);
                if (board[i][j] == 1) {
                    // 活细胞死亡
                    if (liveCount < 2 || liveCount > 3) {
                        board[i][j] = 2;
                    }
                } else {
                    // 死细胞复活
                    if (liveCount == 3) {
                        board[i][j] = 3;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }

    // 八个方位遍历
    private int[] dire = new int[] {0, 1, -1};

    // 计算活细胞个数
    private int checkLiveCount(int[][] board, int i, int j) {
        int res = 0;
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                // 排除0,0本身的情况，其余是八个方向
                if (k == 0 && l == 0) {
                    continue;
                }
                int row = i + dire[k];
                int col = j + dire[l];
                if (row < 0 || row >= board.length || col < 0 || col > board[0].length) {
                    continue;
                }
                res += board[row][col] == 1 || board[row][col] == 2 ? 1 : 0;
            }
        }
        return res;
    }

}
