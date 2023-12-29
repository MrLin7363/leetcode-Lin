package A1000PLAN.位运算;

/**
 * desc:
 *
 * @author lin
 * @since 2023/12/4
 **/
public class P342_4的幂 {
    /*
    二进制中 1的位置
    所有偶数二进制位都是 0，所有奇数二进制位都是 1
     */
    public boolean isPowerOfFour(int n) {
        // 32位
        return n > 0 && (n & (n - 1)) == 0 && (n & 0b10101010101010101010101010101010) == 0;
    }

    // 迭代
    public boolean isPowerOfFour2(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }
}
