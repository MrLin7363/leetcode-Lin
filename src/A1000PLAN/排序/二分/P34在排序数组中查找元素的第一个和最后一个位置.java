package A1000PLAN.排序.二分;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/23
 **/
public class P34在排序数组中查找元素的第一个和最后一个位置 {
    /*
    1.推荐，两次二分查找  最左边界+最右边界
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        int left = binarySearchByLeft(nums, target);
        int right = binarySearchByRight(nums, target);
        if (left >= nums.length || nums[left] != target) {
            return new int[] {-1, -1};
        } else {
            return new int[] {left, right};
        }
    }

    // 找左边界
    private int binarySearchByLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) // 此时不要返回，继续向左搜索
            {
                right = mid;  // 向左向右搜索的不同点，搜索边界方向
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left;
    }

    // 找右边界
    private int binarySearchByRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid])// 此时不要返回，继续向右搜索
            {
                left = mid + 1;  // 向左向右搜索的不同点，搜索边界方向
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        //又因为收紧左侧边界时必须 left = mid + 1
        //所以最后无论返回 left 还是 right，必须减一
        return left - 1;
    }

    /*
    2.找左边界>相等继续右移动
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        if (nums.length == 0) {
            return res;
        }
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 未找到
        if (left >= nums.length || left < 0 || nums[left] != target) {
            return res;
        }

        // 找右边界
        res[0] = left;
        int cur = left;
        while (cur < nums.length - 1 && nums[cur + 1] == nums[cur]) {
            cur++;
        }
        res[1] = cur;
        return res;
    }

    public static void main(String[] args) {
        new P34在排序数组中查找元素的第一个和最后一个位置().searchRange(new int[] {0, 0, 1, 2, 2}, 2);
    }
}
