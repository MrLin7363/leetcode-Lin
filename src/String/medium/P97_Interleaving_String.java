package String.medium;

/**
 * @author: Junlin Chen
 * @Date: 2021/06/16 22:25
 * @Describe: DP  DFS
 *
 */
public class P97_Interleaving_String {
    /*
    二维数组版本  O m*n  S m*n
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }
        boolean[][] dp = new boolean[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) { // 其实是把第一行初始化
                    dp[i][j] = dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(p));
                } else if (j == 0) { // 第一列的初始化
                    dp[i][j] = dp[j][i - 1] && (s1.charAt(i - 1) == s3.charAt(p));
                } else {
                    dp[i][j] = (dp[j][i - 1] && (s1.charAt(i - 1) == s3.charAt(p))) ||
                            (dp[j][i - 1] && (s1.charAt(i - 1) == s3.charAt(p)));
                }
            }
        }
        return dp[n][m];
    }
    /*
    滚动数组 - 一维数组
    每一行每一行确定
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }
        boolean[] dp = new boolean[s2.length() + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) { // 其实是把第一行初始化
                    dp[j] = dp[j - 1] && (s2.charAt(j - 1) == s3.charAt(p));
                } else if (j == 0) { // 由上一行同一列的元素确定
                    dp[j] = dp[j] && (s1.charAt(i - 1) == s3.charAt(p));
                } else {
                    dp[j] = (dp[j - 1] && (s1.charAt(i - 1) == s3.charAt(p))) ||
                            (dp[j] && (s1.charAt(i - 1) == s3.charAt(p)));
                }
            }
        }
        return dp[s2.length()];
    }

    /*
     递归 DFS   2^n*m
     */
    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return is_interleave3(s1, 0, s2, 0, "", s3);
    }

    private boolean is_interleave3(String s1, int i,String s2,int j,String res, String s3){
        if (i==s1.length()&&j==s2.length()&&res.equals(s3)){
            return true;
        }
        boolean ans =false;
        if (i<s1.length()){
            ans |= is_interleave3(s1,i+1,s2,j,res+s1.charAt(i),s3);
        }
        if(j<s2.length()){
            ans |= is_interleave3(s1,i,s2,j+1,res+s2.charAt(j),s3);
        }
        return ans;
    }

    /*
    DFS 备忘录 递归
     */
    public boolean isInterleave4(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int memo[][] = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return is_interleave4(s1, 0, s2, 0, s3,0,memo);
    }
    private boolean is_interleave4(String s1, int i,String s2,int j,String s3,int k ,int[][] memo) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if (memo[i][j] >= 0) {
            return memo[i][j] == 1 ? true : false;
        }
        boolean ans = false;
        if (s3.charAt(k) == s1.charAt(i) && is_interleave4(s1, i + 1, s2, j, s3, k + 1, memo)
                || s3.charAt(k) == s2.charAt(j) && is_interleave4(s1, i, s2, j + 1, s3, k + 1, memo)) {
            ans = true;
        }
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }

}
