package A1000PLAN.双指针;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/22
 **/
public class P31下一个排列 {
    /*
    https://leetcode.cn/problems/next-permutation/solutions/80560/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
    解释：大小数交换，如 123465 的下一个是 [4][5]互换，然后后面升序->123546 ->[4][6] 123564
    算法过程：
    1.从右往左找升序的第一个点【小数】
    2.从右往左找比【小数】大的【大数】，且尽可能靠左
    3.【大数】右边的进行升序排序,使总体的值更小
     */
    public void nextPermutation(int[] nums) {
        // 倒数第二个数判断升序
        int i = nums.length - 2;
        // 找小数
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 找大数
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            // 交换小大
            swap(nums, i, j);
            // i后面的升序:a[i]是第一个升序的点，a[j]>a[i] 区间 [i+1,n) 必为降序，所以这里可以用双指针反转进行升序操作
        }
        // 放到最后必执行是应对，123的情况->321
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left) {
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        new P31下一个排列().nextPermutation(new int[] {1,1});
        new P31下一个排列().nextPermutation(new int[] {1, 2, 3, 5, 4, 6});
        new P31下一个排列().nextPermutation(new int[] {1, 2});
    }
}
