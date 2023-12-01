package A1000PLAN.排序.二分.旋转排序数组;

/**
 *desc:
 *@author lin
 *@since 2023/11/25
 **/
public class P81搜索旋转排序数组II {
    /*
    二分
     */
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                // 左侧有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new P81搜索旋转排序数组II().search(new int[] {3, 1, 2, 3, 3, 3, 3}, 2);
    }
}
