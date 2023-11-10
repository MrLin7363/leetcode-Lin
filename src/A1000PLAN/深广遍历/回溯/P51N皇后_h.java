package A1000PLAN.深广遍历.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *desc:  关键点，斜线的坐标相加相差相等
 *@author   
 *@since 2023/11/2
 **/
public class P51N皇后_h {
    public static void main(String[] args) {
        List<List<String>> lists = new P51N皇后_h().solveNQueens(4);
        System.out.println(lists);
    }

    private List<List<String>> res = new ArrayList<>();

    private Set<Integer> column = new HashSet<>(); // 每一列是否存了

    private Set<Integer> leftDiagonal = new HashSet<>(); //斜线为从右上到左下，行下表+列下标相等

    private Set<Integer> rightDiagonal = new HashSet<>(); //斜线为从左上到右下，行下标-列下标相等

    int[] queues; // 每行皇后的位置

    public List<List<String>> solveNQueens(int n) {
        queues = new int[n];
        dfs(0, n);
        return res;
    }

    private void dfs(int row, int n) {
        if (row == n) {
            res.add(generateBoard(n));
            return;
        }
        // 每次递归是下一行，这里for每一列放置元素
        for (int i = 0; i < n; i++) {
            if (column.contains(i)) {
                continue;
            }
            int diagonal1 = row + i;
            if (leftDiagonal.contains(diagonal1)) {
                continue;
            }
            int diagonal2 = row - i;
            if (rightDiagonal.contains(diagonal2)) {
                continue;
            }
            queues[row] = i;
            column.add(i);
            leftDiagonal.add(diagonal1);
            rightDiagonal.add(diagonal2);
            dfs(row + 1, n);
            // queues[row] = -1; // 加不加都行，因为每一行都会覆盖掉
            column.remove(i);
            leftDiagonal.remove(diagonal1);
            rightDiagonal.remove(diagonal2);
        }
    }

    private List<String> generateBoard(int n) {
        List<String> res = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queues[k]] = 'Q';
            res.add(new String(row));
        }
        return res;
    }
}
