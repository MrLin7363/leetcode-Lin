package A1000PLAN.排序.二分.旋转排序数组;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/23
 **/
public class P33搜索旋转排序数组 {
    /*
    二分-找答案，闭区间-常规-变种
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            // mid左侧是有序的
            if (nums[left] <= nums[mid]) {
                // 往有序数组里面搜索 注意是<=
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // 往右侧无序数组搜索
                    left = mid + 1;
                }
            } else { // mid右侧是有序数组
                // 往右侧有序数组搜索
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    // 往左侧无序数组搜索
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new P33搜索旋转排序数组().search(new int[] {5, 1, 3}, 3);
    }
}
