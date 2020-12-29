package BitManipulation.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe:
 */

public class P326_Power_of_Three {
    // log3 MaxInt 最大整数限制  99 + 47
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    // 用这个log运算，推广到 N 的幂  99 + 24
    public boolean isPowerOfThree3(int n) {
        return Math.log10(n)/Math.log10(3) %1==0;
        // return Math.log(n)/Math.log(3) %1==0;  log 是以e为底
    }
    // 迭代
    public boolean isPowerOfThree4(int n) {
        if (n < 1 ) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
