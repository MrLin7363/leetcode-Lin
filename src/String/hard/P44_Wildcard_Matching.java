package String.hard;

/**
 * @author: Junlin Chen
 * @Date: 2021/07/04 20:00
 * @Describe: 匹配字符串
 */
public class P44_Wildcard_Matching {

    /*
    DP-二维数组-这题可优化  46+91
     */
    public boolean isMatch(String s, String p) {
        int m=s.length(),n=p.length();
        boolean[][] dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        // dp[0][0]为空字符串
        // 初始化第一行，其他的默认为false
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i-1)=='*'){
                dp[0][i]=true;
            }else{
                break;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1)=='*'){ // dp 矩阵 和 字符串的下标差1 ，如dp[0][1] 是p的第一个字符匹配s的空字符串
                    dp[i][j]= dp[i-1][j] || dp[i][j-1];
                }else if (p.charAt(j-1)=='?' || s.charAt(i-1)==p.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }

}
