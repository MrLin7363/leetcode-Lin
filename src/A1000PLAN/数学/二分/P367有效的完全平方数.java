package A1000PLAN.数学.二分;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/8
 **/
public class P367有效的完全平方数 {
    // 二分-推荐
    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            if (square == num) {
                return true;
            } else if (square > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new P367有效的完全平方数().isPerfectSquare(16);
        new P367有效的完全平方数().isPerfectSquare(14);
    }

    // 内置函数
    public boolean isPerfectSquare2(int num) {
        int x = (int) Math.sqrt(num);
        return x * x == num;
    }
}
