package MyTest;/*
    
/**
  *@Author JunLin
  *@Date 2021/2/2
  *@Describe:
 */

public class Test {
    public int climbStairs(int n) {
        if (n<=1) return 1;
        int[] dp=new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for (int i = 3; i <= n; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    public int climbStairs2(int n) {
        if (n<=1) return 1;
        int first=1,second=2;
        int sum=0;
        for (int i = 3; i <= n; i++) {
            sum=first+second;
            first=second;
            second=sum;
        }
        return sum;
    }
}
