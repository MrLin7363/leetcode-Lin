package String.hard;
    
/**
  *@Author JunLin
  *@Date 2020/11/1
  *@Describe: 解码2
  场景*：
  前一位1： dp[n]=9*dp[n-1] + 9*dp[n-2]
  前一位2： dp[n]=9*dp[n-1] + 6*dp[n-2]
  前一位*： dp[n]=9*dp[n-1] + 15*dp[n-2]
  其他：
  前一位1： dp[n]=dp[n-1] + dp[n-2]
  前一位2： dp[n]=dp[n-1] + dp[n-2]
  前一位*   当前位<=6   dp[n]=dp[n-1] + 2* dp[n-2]
          否则  dp[n]=dp[n-1] + dp[n-2]
  最后结果求模，题目要求避免溢出
 */

public class Decode_Ways_II_639 {
    int M = 1000000007;
    /*
    T n
    S n
     */
    public int numDecodings(String s) {
        long[] dp=new long[s.length()+1];
        dp[0]=1;
        dp[1] = s.charAt(0)=='*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i)=='*'){
                dp[i+1] = 9*dp[i]; // 这里用 i+1 表示，因为dp[n] 指有多少个数字
                if (s.charAt(i-1)=='1') // 前一位为 1
                    dp[i+1]=(dp[i+1] + 9*dp[i-1]) % M;
                else if (s.charAt(i - 1) == '2')
                    dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '*')
                    dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
            }else{
                dp[i+1]=s.charAt(i)!='0'?dp[i]:0;
                if (s.charAt(i-1)=='1')
                    dp[i+1]=(dp[i+1]+dp[i-1])%M;
                else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                    dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '*')
                    dp[i + 1] = (dp[i + 1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i - 1]) % M;
            }
        }
        return (int)dp[s.length()];
    }

    /*
    T n S 1  恒定空间的dp,不需要DP数组，
     */
    public int numDecodings2(String s) {
        long first = 1, second = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            long temp = second;
            if (s.charAt(i) == '*') {
                second = 9 * second;
                if (s.charAt(i - 1) == '1')
                    second = (second + 9 * first) % M;
                else if (s.charAt(i - 1) == '2')
                    second = (second + 6 * first) % M;
                else if (s.charAt(i - 1) == '*')
                    second = (second + 15 * first) % M;
            } else {
                second = s.charAt(i) != '0' ? second : 0;
                if (s.charAt(i - 1) == '1')
                    second = (second + first) % M;
                else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                    second = (second + first) % M;
                else if (s.charAt(i - 1) == '*')
                    second = (second + (s.charAt(i) <= '6' ? 2 : 1) * first) % M;
            }
            first = temp;
        }
        return (int) second;
    }


}
