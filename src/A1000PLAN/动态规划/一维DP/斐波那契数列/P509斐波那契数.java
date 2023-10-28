package A1000PLAN.动态规划.一维DP.斐波那契数列;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/19
 **/
public class P509斐波那契数 {
    /*
    F(0) = 0，F(1) = 1
    F(n) = F(n - 1) + F(n - 2)，其中 n > 1
    计算F(n)
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public int fib2(int n) {
        int first = 0;
        int second = 1;
        for (int i = 2; i <= n; i++) {
            int temp = second;
            second = first + second;
            first = temp;
        }
        return n == 0 ? 0 : second;
    }
}
