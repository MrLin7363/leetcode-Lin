package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/1
  *@Describe: 字母解码
  动态规划
 */

public class Decode_Ways_91 {
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n=s.length();
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]= s.charAt(0)!='0'? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first=Integer.parseInt(s.substring(i-1,i));//截取一位
            int second=Integer.parseInt(s.substring(i-2,i));//截取两位
            if (first>=1 && first<=9){
                dp[i]+=dp[i-1]; //加上位dp的情况，如01，那么上一位dp=0,以0开头的dp都是0
            }
            if (second>=10 && second<=26){
                dp[i]+=dp[i-2];//加上上位dp的情况
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        numDecodings("01");
    }
}
