 

package hw.二分;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/11/23
 **/
public class P33搜索旋转排序数组 {

    public static int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] <= target ? -1 : left;
    }

    public static void main(String[] args) {
        System.out.println(P33搜索旋转排序数组.searchLeft(new int[]{1,2,6,7,7,8},3));
    }
}
