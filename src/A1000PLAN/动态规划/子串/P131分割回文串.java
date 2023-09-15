package A1000PLAN.动态规划.子串;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * desc: 动态规划（预处理） + 回溯
 *
 * @author Lin
 * @since 2023/9/7
 **/
public class P131分割回文串 {
    /*
    分割回文串- 动态规划（预处理）算出是否是回文子串 + 回溯 组合
    解释：如果它是回文子串，并且长度大于 2，那么将它首尾的两个字符去除之后，它仍然是个回文子串
    dp定义：用 dp[i][j]表示字符串 s 的下标 [i,j] 的字符串是否是回文子串
    转移方程：s[i]!=s[j] : 不是子串
            s[i]==s[j] : 如果j-i<3,是子串 /  dp[i][j]=dp[i+1][j-1] 取决于子区间是不是子串
    初始化：长度为1的是子串
    遍历顺序: 行倒序，列正序
    返回：如果是子串true，则记录一次list
     */
    private List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        // 动态规划预处理
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }

        // 回溯
        dfs(dp, s, 0, new LinkedList<>());
        return ans;
    }

    private void dfs(boolean[][] dp, String s, int i, List<String> path) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (dp[i][j]) {
                path.add(s.substring(i, j + 1));
                dfs(dp, s, j + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }

    /*
    记忆化搜索代替DP + 回溯
     */
    private List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition2(String s) {
        int n = s.length();
        int[][] record = new int[n][n];
        dfs(s, 0, record, new LinkedList<>());
        return ans;
    }

    private void dfs(String s, int i, int[][] record, List<String> path) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (isPalin(s, record, i, j) == 1) {
                path.add(s.substring(i, j + 1));
                dfs(s, j + 1, record, path);
                path.remove(path.size() - 1);
            }
        }
    }

    // 0未搜索，1是子串，-1不是子串
    private int isPalin(String s, int[][] record, int i, int j) {
        if (record[i][j] != 0) {
            return record[i][j];
        }
        if (s.charAt(i) != s.charAt(j)) {
            record[i][j] = -1;
        } else {
            if (j - i < 3) {
                record[i][j] = 1;
            } else {
                return isPalin(s, record, i + 1, j - 1);
            }
        }
        return record[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new P131分割回文串().partition("aab"));
        System.out.println(new P131分割回文串().partition("aba"));
    }
}
