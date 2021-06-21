package String.medium;

/**
 * @author: Junlin Chen
 * @Date: 2021/06/16 22:25
 * @Describe: DP
 *
 */
public class P97_Interleaving_String {
    /*
    二维数组版本
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] =  (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    dp[i][j] =  dp[i][j]||(dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[n][m];
    }
    /*
    滚动数组
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        for (char c3: s3.toCharArray()){

        }
        return false;
    }
}
