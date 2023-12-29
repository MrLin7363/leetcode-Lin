package A1000PLAN.数学.easy;

/**
 * desc:
 *
 * @author lin
 * @since 2023/12/4
 **/
public class P326_3的幂 {
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    // 是否为 最大3的幂 3^19=11622614673 的约数
    public boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        // 1.162261467E9 E9的意思是*9个0，  10^9
        System.out.println(Math.pow(3, 19));
    }
}
