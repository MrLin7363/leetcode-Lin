package Math.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/20 20:00
 * @Describe:
 */
public class Reverse_Integer_7 {

    public int reverse(int x) {
        //utilize the carry
        int rev = 0;
        while (x != 0) {
            //last digit
            int pop = x % 10;
            //except last digit
            x /= 10;
            //overflow
            if (rev > Integer.MAX_VALUE / 10 || rev == Integer.MAX_VALUE / 10 && pop > 7) return 0;
            if (rev < Integer.MIN_VALUE / 10 || rev == Integer.MIN_VALUE / 10 && pop < -8) return 0;
            //add value from the last
            rev = rev * 10 + pop;
        }
        return rev;

    }
}
