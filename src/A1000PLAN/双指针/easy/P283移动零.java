
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
    1.同步（右先移）双指针：左指针先遇0停，右指针遇到非0停，互换元素
    每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变
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
