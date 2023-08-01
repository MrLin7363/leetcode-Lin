package Array.medium;

/**
 * @author: Junlin Chen
 * @Date: 2020/11/10 19:28
 * @Describe:
 */
public class P34在排序数组中查找元素的第一个和最后一个位置 {
    /*
    二分：往左二分，往右二分，两次确定左右端点
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        int left = binarySearch(nums, target, true);
        if (left == nums.length || nums[left] != target) // target比所有数都要大，或者不等于
            return new int[]{-1, -1};
        int right = binarySearch(nums, target, false) - 1;
        return new int[]{left, right};
    }

    private static int binarySearch(int[] nums, int target, boolean isLeft) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 左进如果是相等情况下，搜索左端点时要再左进
            if (nums[mid] > target || (isLeft && nums[mid] == target)) {
                right = mid - 1;
            } else {
                // 如果相等且不是搜索左端点时，右进
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(searchRange(new int[]{0, 1, 1, 1, 3, 4}, 1)[1]);
    }


    // ---------------------------------------------------------------
    //  二分法

    public int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int firstPosition = findFirstPosition(nums, target);
        int lastPosition = findLastPosition(nums, target);
        return new int[]{firstPosition, lastPosition};
    }


    private int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // ① 不可以直接返回，应该继续向左边找，即 [left, mid - 1] 区间里找
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else {
                // 此时 nums[mid] > target，应该继续向左边找，即 [left, mid - 1] 区间里找
                right = mid - 1;
            }
        }

        // 此时 left 和 right 的位置关系是 [right, left]，注意上面的 ①，此时 left 才是第 1 次元素出现的位置
        // 因此还需要特别做一次判断
        if (left != nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }


    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 只有这里不一样：不可以直接返回，应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else if (nums[mid] < target) {
                // 应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else {
                // 此时 nums[mid] > target，应该继续向左边找，即 [left, mid - 1] 区间里找
                right = mid - 1;
            }
        }

        if (right != -1 && nums[right] == target) {
            return right;
        }
        return -1;
    }
}