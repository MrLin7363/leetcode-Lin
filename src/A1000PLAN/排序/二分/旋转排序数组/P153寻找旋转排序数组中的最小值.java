package A1000PLAN.排序.二分.旋转排序数组;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/23
 **/
public class P153寻找旋转排序数组中的最小值 {
    /*
    二分-  找最小
    推荐-可以兼容有重复元素
    红：最小值左侧
    蓝：最小值及其右侧， n-1其实已经确定
    [) 找左边界模板
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        new P153寻找旋转排序数组中的最小值().findMin(new int[] {4, 5, 6, 7, 0, 1, 2});
        new P153寻找旋转排序数组中的最小值().findMin(new int[] {3, 4, 5, 1, 2});
    }
}
