package A1000PLAN.数组;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/25
 **/
public class P189轮转数组 {
    /*
    1. 推荐 数组翻转 O1空间   翻转全部数组-> 翻转[0,k mod n -1] -> 翻转[k mod n,n-1]
    2. 使用新数组 放到(i+k)mod n   On空间，On时间
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k % n - 1);
        reverse(nums, k % n, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        new P189轮转数组().rotate(new int[] {1, 2, 3, 4, 5, 6, 7}, 3);
    }
}
