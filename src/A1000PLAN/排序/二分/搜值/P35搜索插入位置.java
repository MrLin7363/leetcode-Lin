package A1000PLAN.排序.二分.搜值;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/23
 **/
public class P35搜索插入位置 {
    // 二分查找-搜索值
    // 返回的就是left+right失序的位置，所以直接返回就行,但是是left
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
