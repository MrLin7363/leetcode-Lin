package A1000PLAN.深广遍历.记忆化搜索;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/7
 **/
public class P131分割回文串_记忆化搜索 {
    /*
    记忆化搜索代替DP + 回溯
    */
    private List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        dfs(s, 0, dp, new LinkedList<>());
        return ans;
    }

    private void dfs(String s, int i, int[][] dp, List<String> path) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (isPalin(s, dp, i, j) == 1) {
                path.add(s.substring(i, j + 1));
                dfs(s, j + 1, dp, path);
                path.remove(path.size() - 1);
            }
        }
    }

    // 0未搜索，1是子串，-1不是子串
    private int isPalin(String s, int[][] dp, int i, int j) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (s.charAt(i) != s.charAt(j)) {
            dp[i][j] = -1;
        } else {
            if (j - i < 3) {
                dp[i][j] = 1;
            } else {
                return isPalin(s, dp, i + 1, j - 1);
            }
        }
        return dp[i][j];
    }
}
