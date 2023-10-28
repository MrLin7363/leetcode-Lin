package A1000PLAN.深广遍历.回溯.二维搜索;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/26
 **/
public class P79单词搜索 {
    private int[] direction = new int[] {-1, 0, 1, 0, -1};

    private boolean[][] visited;

    // 推荐下面那个
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backtrack(board, word, String.valueOf(word.charAt(0)), i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, String word, String cur, int row, int col) {
        if (cur.equals(word)) {
            return true;
        }
        visited[row][col] = true;
        for (int k = 0; k < 4; k++) {
            int i = row + direction[k];
            int j = col + direction[k + 1];
            if (i >= 0 && i < board.length && j >= 0 && j < board[0].length && !visited[i][j]) {
                if (board[i][j] != word.charAt(cur.length())) {
                    continue;
                }
                if (backtrack(board, word, cur + board[i][j], i, j)) {
                    return true;
                }
            }
        }
        visited[row][col] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] board2 = new char[][] {{'a', 'a',}};
        new P79单词搜索().exist(board2, "aaa");
        new P79单词搜索().exist(board, "ABCCED");
    }


    /*
    差不多的做法-推荐这个，但是方向的遍历可以推荐上面那个
    二维数组，搜索字符串 : 回溯 31 + 93
    */
    public boolean exist2(char[][] board, String word) {
        int height = board.length, width = board[0].length;
        boolean[][] visited = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean flag = backtrack(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean backtrack(char[][] board, boolean[][] visited, int i, int j, String word, int idx) {
        if (board[i][j] != word.charAt(idx)) {
            return false;
        } else if (idx == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 上 右 下 左  不一定按顺序也行
        for (int[] direction : directions) {
            int newi = i + direction[0], newj = j + direction[1]; // 移动位置
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) { // 判断位置是否在二维数组中
                if (!visited[newi][newj]) { //为被访问过，防止从来的位置又回朔还可以
                    boolean flag = backtrack(board, visited, newi, newj, word, idx + 1);
                    if (flag == true) {
                        return true;
                    }
                }
            }
        }
        visited[i][j] = false;
        return false;
    }
}
