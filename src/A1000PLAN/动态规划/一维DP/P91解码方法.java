package A1000PLAN.动态规划.一维DP;

/**
 *desc:
 *@author lin
 *@since 2023/11/25
 **/
public class P91解码方法 {
    /*
    DP定义： dp[i] 到i结点前的解码方法数
    转移方程：
            dp[i]==dp[i-1] s[i]!=0 ,意思是之前组合搭配单独的当前字符，
            dp[i]=dp[i-2]  s[i-1]!=0 && 10*s[i-1]+s[i]<=26
    初始化： dp[0]=1 空字符串解码为空字符串1种方式
    遍历顺序：
    返回条件：
    可以优化为三个变量
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) - '0' != 0) {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) - '0' != 0 && 10 * (s.charAt(i - 2) - '0') + (s.charAt(i - 1) - '0') <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
