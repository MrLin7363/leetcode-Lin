package A1000PLAN.排序.二分;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/23
 **/
public class P153寻找旋转排序数组中的最小值 {
    /*
    二分-常规-变种  找最小
    推荐-可以兼容有重复元素
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // <= < 都可以
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right]) { // 兼容有重复元素的情况
                right--;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    // 不推荐 非标准模板，left<right, 所以下面不存在 nums[mid] == nums[right]
    public int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;                /* 左闭右闭区间，如果用右开区间则不方便判断右值 */
        while (left < right) {                      /* 循环不变式，如果left == right，则循环结束 */
            int mid = left + (right - left) / 2;    /* 地板除，mid更靠近left */
            if (nums[mid] > nums[right]) {          /* 中值 > 右值，最小值在右半边，收缩左边界 */
                left = mid + 1;                     /* 因为中值 > 右值，中值肯定不是最小值，左边界可以跨过mid */
            } else if (nums[mid] < nums[right]) {   /* 明确中值 < 右值，最小值在左半边，收缩右边界 */
                right = mid;                        /* 因为中值 < 右值，中值也可能是最小值，右边界只能取到mid处 */
            }
        }
        return nums[left];    /* 循环结束，left == right，最小值输出nums[left]或nums[right]均可 */
    }

    public static void main(String[] args) {
        new P153寻找旋转排序数组中的最小值().findMin(new int[] {4, 5, 6, 7, 0, 1, 2});
        new P153寻找旋转排序数组中的最小值().findMin(new int[] {3, 4, 5, 1, 2});
    }
}
