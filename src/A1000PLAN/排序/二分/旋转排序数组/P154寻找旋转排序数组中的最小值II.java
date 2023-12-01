package A1000PLAN.排序.二分.旋转排序数组;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/23
 **/
public class P154寻找旋转排序数组中的最小值II {
    /*
    二分-常规-变种  有重复 找最小
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right]) {
                right--;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        new P154寻找旋转排序数组中的最小值II().findMin(new int[] {1, 1});
    }
}
