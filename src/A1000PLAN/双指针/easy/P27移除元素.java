package A1000PLAN.双指针.easy;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/21
 **/
public class P27移除元素 {
    /*
    首尾双指针优化版：遇到val直接把右指针的值set到左指针，省去交换，因为数组后面的值是什么无所谓
     */
    public int removeElement2(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        // 1个元素也要考虑，[]左闭右闭
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    // 双指针：遇到不是val直接set到左指针
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    /*
    不推荐
    首尾双指针：自己写的-遇到val交换，右指针时需要找到非val值比较不好理解
     */
    public int removeElement3(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {

            if (nums[left] == val) {
                while (right >= 0 && nums[right] == val) {
                    right--;
                }
                // 交换
                if (left < right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    right--;
                }
            }
            left++;
        }
        return right + 1;
    }

    public static void main(String[] args) {
        new P27移除元素().removeElement2(new int[] {1}, 1);
        new P27移除元素().removeElement3(new int[] {3, 3, 2, 3}, 3);
        new P27移除元素().removeElement3(new int[] {3, 2, 2, 3}, 3);
        new P27移除元素().removeElement3(new int[] {1, 2}, 2);
        new P27移除元素().removeElement3(new int[] {0, 1, 2, 2, 3, 0, 4, 2}, 2);
    }
}
