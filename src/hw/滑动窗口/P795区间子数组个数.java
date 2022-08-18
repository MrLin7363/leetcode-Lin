package hw.滑动窗口;

public class P795区间子数组个数 {

    /**
     * 子数组最大元素在区间[left,right]
     * 这题不能单纯用滑动窗口双指针，因为不是单纯的一个右指针向右，然后左指针收缩
     * 前缀和 + 滑动窗口思想
     * 1.前缀和思路，两次滑窗，找到最大值小于等于R的子数组个数，找到最大值小于等于L-1的子数组个数，然后相减
     * 2.前缀和思路，两次滑窗，找到最大值大于L的子数组个数，找到最大值大于R+1的子数组个数，然后相减
     */
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return getValue(nums, right) - getValue(nums, left - 1);
    }

    /**
     * 最大值小于等于 value 的子数组个数
     */
    private int getValue(int[] nums, int value) {
        int ans = 0;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            // 递增
            cur = nums[i] <= value ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        P795区间子数组个数 ss = new P795区间子数组个数();
        ss.getValue(new int[]{2, 1, 4, 3}, 3);
        ss.getValue(new int[]{2, 1, 4, 3}, 1);
        ss.getValue(new int[]{2, 34, 3}, 50);
        System.out.println(ss.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
        System.out.println(ss.numSubarrayBoundedMax(new int[]{73, 55, 36, 5, 55, 14, 9, 7, 72, 52}, 32, 69));
        System.out.println(ss.numSubarrayBoundedMax(new int[]{2, 9, 2, 5, 6}, 2, 8));
    }

}
