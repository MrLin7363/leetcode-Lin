package A1000PLAN.排序.二分.搜值;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/25
 **/
public class P374猜数字大小 {
    /*
    二分查找-搜索值常规版 [1,n]闭区间版本
     */
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int guess = guess(mid);
            if (guess == 0) {
                return mid;
            } else if (guess == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int guess(int num) {
        return 0;
    }
}
