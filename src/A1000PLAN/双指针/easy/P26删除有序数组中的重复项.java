package A1000PLAN.双指针.easy;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/22
 **/
public class P26删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int left = 0;
        for (int right = 1; right < n; right++) {
            if (nums[right] != nums[right - 1]) {
                left++;
                nums[left] = nums[right];
            }
        }
        return left + 1;
    }

    public static void main(String[] args) {
        new P26删除有序数组中的重复项().removeDuplicates(new int[] {1, 1, 2});
        new P26删除有序数组中的重复项().removeDuplicates(new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
    }
}
