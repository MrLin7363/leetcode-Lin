package A1000PLAN.数学;

/**
 *desc:
 *@author lin
 *@since 2023/11/22
 **/
public class P50Pow {
    /*
    快速幂
    x * x^4 * x^8 * x^64 = x^77 将上次的结果进行平方，如果n为奇数，还要再乘一个x
     */
    public double myPow(double x, int n) {
        return n >= 0 ? dfs(x, n) : 1.0 / dfs(x, -n);
    }

    private double dfs(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double y = dfs(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }
}
