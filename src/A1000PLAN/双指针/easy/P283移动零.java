
package A1000PLAN.双指针.easy;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/21
 **/
public class P283移动零 {
    /*
    请注意 ，必须在不复制数组的情况下原地对数组进行操作。
    // 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
    2.不推荐：暴力解，依次前移动0后面的元素前1格
    */
    public void moveZeroes(int[] nums) {
        int right = 0;
        int n = nums.length;
        int left = 0;
        while (right < n) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }

    public static void main(String[] args) {
        new P283移动零().moveZeroes(new int[] {1, 0, 2});
        new P283移动零().moveZeroes(new int[] {0, 1, 0, 3, 12});
        new P283移动零().moveZeroes(new int[] {0});
    }
}
