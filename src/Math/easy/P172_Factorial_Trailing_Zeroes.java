package Math.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/28
  *@Describe: 阶乘后的尾数0的个数
 */

public class P172_Factorial_Trailing_Zeroes {
    /*
    高效计数因子5   100 + 99
    规律就是每隔 5 个数，出现一个 5，每隔 25 个数，出现 2 个 5，每隔 125 个数，出现 3 个 5
     */
    public int trailingZeroes(int n) {
        int count=0;
        while (n>0){
            count+=n/5;
            n/=5;
        }
        return count;
    }
    /*
     高效计数因子5   100 + 61  幂运算
     规律就是每隔 5 个数
      */
    public int trailingZeroes2(int n) {
        int count=0;
        // We need to use long because currentMultiple can potentially become arger than an int.
        long power=5;
        while (n>=power){
            count+=n/power;
            power*=5;
        }
        return count;
    }


    /*
    计数因子5    14 + 96
    每隔5个判断累乘的数有多少个5的因子
     */
    public int trailingZeroes4(int n) {
        int count = 0;
        for (int i = 5; i <= n; i+=5) {
            int N = i;
            while (N %5 == 0 ) {
                    count++;
                    N /= 5;
            }
        }
        return count;
    }
    /*
     计数因子5    14 + 61   幂判断
     每隔5个判断累乘的数有多少个5的因子
    */
    public int trailingZeroes3(int n) {
        int count = 0;
        for (int i = 5; i <= n; i+=5) {
            int powerOf5 = 5;
            while (i%powerOf5 == 0 ) {
                count++;
                powerOf5*=5;
            }
        }
        return count;
    }

    /*
    超时:计数因子5
    逐个判断每个累乘的数有多少个 5 的因子
     */
    public int trailingZeroes5(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int N = i;
            while (N > 0) {
                if (N % 5 == 0) {
                    count++;
                    N /= 5;
                } else {
                    break;
                }
            }
        }
        return count;
    }
}
