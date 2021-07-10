package Array.medium;

/**
 * @author: Junlin Chen
 * @Date: 2021/07/10 15:05
 * @Describe:
 */
public class P287_Find_Duplicate_Number {

    /*
    快慢指针  51+7
     */
    public static int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        // find the same place
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        // 同步走
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /*
    抽屉原理 60+60
     */
    public static int findDuplicate2(int[] nums) {
        int n = nums.length;
        int left = 1, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            // 根据抽屉原理，小于等于 4 的个数如果严格大于 4 个，此时重复元素一定出现在 [1..4] 区间里
            if (count > mid) {
                // 重复元素位于区间 [left..mid]
                right = mid;
            } else {
                // if 分析正确了以后，else 搜索的区间就是 if 的反面区间 [mid + 1..right]
                left = mid + 1;
            }
        }
        return left;
    }

}
