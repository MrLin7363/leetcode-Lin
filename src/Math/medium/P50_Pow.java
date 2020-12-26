package Math.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/22
  *@Describe:
 */

public class P50_Pow {

    /*
    快速幂 + 迭代   29 + 38
    利用二进制的规律
    x * x^4 * x^8 * x^64 = x^77 只需相乘前面几个位数的值，二进制判断就是对应下标位置为1
     */
    static double  quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案，每次N/2后
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    public static double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    /*
    快速幂 + 递归   97 + 15
    直接把上一次的结果进行平方，奇数额外乘一个 x
     */
    public  double quickMul2(double x, long N) {
        if (N==0){
            return 1;
        }
        double y =quickMul2(x,N/2);
        return N%2==0 ? y * y : y*y*x; // 奇数额外乘多一个
    }

    public  double myPow2(double x, int n) {
        return n>=0?quickMul2(x,n):1/quickMul2(x,-n);
    }

    public static void main(String[] args) {
        myPow(2,77);
    }
    /*
    暴力解法-超时
     */
    public double myPow3(double x, int n) {
        if (x < 0) {
            x = 1 / x;
            n = -n;
        }
        double ans = 1;
        for (int i = 0; i < n; i++) {
            ans = ans * x;
        }
        return ans;
    }
}
