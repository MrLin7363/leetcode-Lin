

package hw.二分;

/**
 * desc: 记住条件有> < mid 的都是要 +1 因为mid已经不符合条件
 *
 * @author junlin
 * @since 2021/11/23
 **/
public class erfen模板 {
    /*
     标准二分查找
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /*
    二分查找左边界  -  查找第一个大于等于target的元素 注意必须等于target否则找不到，也可以返回索引就行
    记住查找左边界 nums[mid] < target 因为如果比target小，只能移动+1，而不是原地
    1.数组有序，但包含重复元素
    2.数组部分有序，且不包含重复元素
    3.这个是从小到大排好序的数组，如果是从大到小，则 if else条件相反
     */
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
        return nums[left] == target ? left : -1;
    }

    /*
    二分查找左边界
    3.数组部分有序，且包含重复元素
     */
    public static int searchLeft2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left] == target ? left : -1;
    }

    /*
    二分查找右边界有序
    记住查找右边界 nums[mid] > target 因为如果比target大，只能移动-1，而不是原地
    1.有序
    2.部分有序 且不含重复元素
     */
    public static int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 注意后面要加1  对于奇数还是偶数，这个中间的位置都是偏右的
            int mid = left + ((right - left) >> 1) + 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }

    public static int testLeft() {
        int[] nums = new int[]{1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 4, 5, 5, 6, 6};
        int target = 3;
        return searchLeft(nums, target);
    }

    // 测试方法
    public static int testRight() {
        int[] nums = new int[]{1, 1, 1, 1, 1, 1, 2, 2, 4};
        int target = 1;
        return searchRight(nums, target);
    }

    public static void main(String[] args) {
        System.out.println(testLeft());
        System.out.println(testRight());
        System.out.println(testSearchFirstBig());
    }

    /*
    查找第一个大于target的元素
    利用右边界模板：查找最后一个大于等于target的元素   最后默认索引是最后一个 比target小的元素，或者等于target的元素
    索引加+1就是第一个 大于target的元素
     */
    public static int searchFirstBig(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 注意后面要加1  对于奇数还是偶数，这个中间的位置都是偏右的
            int mid = left + ((right - left) >> 1) + 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return right;
    }

    // 测试方法
    public static int testSearchFirstBig() {
        int[] nums = new int[]{1, 1, 21, 22,222};
        int target = 23;
        return searchFirstBig(nums, target);
    }
}