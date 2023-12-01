package A1000PLAN.数学.easy;

/**
 *desc:
 *@author lin
 *@since 2023/11/22
 **/
public class P69x的平方根 {
    /*
    二分好记录
     */
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
