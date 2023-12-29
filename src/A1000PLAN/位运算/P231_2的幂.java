package A1000PLAN.位运算;

/**
 * desc:
 *
 * @author lin
 * @since 2023/12/4
 **/
public class P231_2的幂 {
    /*
    2^n 二进制 1 10 100 1000 10000 只有一个1   BK算法
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
