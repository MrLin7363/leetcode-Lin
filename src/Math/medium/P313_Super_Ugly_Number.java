package Math.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe: 参考P264丑数2
 */

public class P313_Super_Ugly_Number {
    // 87 + 78
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp=new int[n];
        dp[0]=1;
        int ugly,i2=0,i3=0,i5=0;
        // 记录每一个质数列表的下标
        int[] index=new int[primes.length];
        for (int i = 1; i < n; i++) {
            // 找最小
            int min=Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                if (min>primes[j] * dp[index[j]] ){
                    min=primes[j]*dp[index[j]];
                }
            }
            dp[i]=min;
            // 移动下标
            for (int j = 0; j < primes.length; j++) {
                if (min==primes[j]*dp[index[j]]){
                    index[j]++;
                }
            }
        }
        return dp[n-1];
    }
}