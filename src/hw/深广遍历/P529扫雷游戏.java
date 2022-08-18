 

package hw.深广遍历;

  
public class P529扫雷游戏 {
    /**
     * 1.如果首次点击的是地雷，则修改为 X 结束
     * 2.如果点击的不是地雷，周围也没有地雷，就递归周围的8个方块
     * 3.如果点击的不是地雷，但是周围有地雷，则修改当前方格为周围地雷的个数
     * 链接：https://leetcode-cn.com/problems/minesweeper/solution/sao-lei-you-xi-by-leetcode-solution/
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            dfs(board, click);
        }
        return board;
    }

    private void dfs(char[][] board, int[] click) {
        int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
        int count = 0;
        for (int[] dir : direction) {
            int row = click[0] + dir[0];
            int col = click[1] + dir[1];
            if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
                continue;
            }
            if (board[row][col] == 'M') {
                count++;
            }
        }
        // 规则1
        if (count > 0) {
            board[click[0]][click[1]] = (char) (count + '0');
        } else {

            board[click[0]][click[1]] = 'B';
            for (int[] dir : direction) {
                int row = click[0] + dir[0];
                int col = click[1] + dir[1];
                // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了,同样炸弹M也不用判断
                if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != 'E') {
                    continue;
                }
                dfs(board, new int[]{row, col});
            }
        }
    }

    /*
    “A”为65；“a”为97；“0”为 48
     */
    public static void main(String[] args) {
        int count = 0;
        char cha = (char) (count + '0');
        System.out.println(cha);
    }

}
