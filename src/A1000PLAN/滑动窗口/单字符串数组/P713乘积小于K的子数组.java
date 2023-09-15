
package A1000PLAN.滑动窗口.单字符串数组;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/12
 **/
public class P713乘积小于K的子数组 {
    /*
    滑动窗口
    分析：
    1.定义左右指针
    2.指针起始位置为0；右指针遍历，每次移动一次；
    3.移动过程中积累从左指针到右指针的乘积为mul
    4.如果乘积>=k，则左指针右移，直到<k
    5.是ans记录mul<k的组合
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 注意k==0|1 都是返回0
        if (k <= 1) {
            return 0;
        }
        int n = nums.length;
        int l = 0;
        int r = 0;
        int ans = 0;
        int mul = 1;
        // 用右指针遍历整个数组，每次循环右指针右移一次
        while (r < n) {
            // 计算当前乘积等
            mul = mul * nums[r];
            // 不符合条件,左指针右移，更新条件
            while (mul >= k) {
                mul /= nums[l];
                l++;
            }
            // 符合条件->记录结果等
            //  nums[right]
            //  nums[right-1], nums[right]
            //  nums[right-2], nums[right-1], nums[right]
            //  nums[left], ......, nums[right-2], nums[right-1], nums[right]
            ans += r - l + 1;
            // 移动右指针
            r++;
        }
        return ans;
    }

    public static void main(String[] args) {
        new P713乘积小于K的子数组().numSubarrayProductLessThanK(new int[] {10, 5, 2, 6}, 1);
        new P713乘积小于K的子数组().numSubarrayProductLessThanK(new int[] {10, 5, 2, 6}, 100);
    }
}
