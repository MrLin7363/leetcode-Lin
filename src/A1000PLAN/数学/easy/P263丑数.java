package A1000PLAN.数学.easy;

/**
 *desc:
 *@author lin
 *@since 2023/12/1
 **/
public class P263丑数 {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = new int[] {5, 3, 2};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}
