package A1000PLAN.动态规划.子序列;

import java.util.Arrays;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/11
 **/
public class P334递增的三元子序列 {
    /*
    从左到右遍历数组 nums，遍历过程中维护两个变量 first和 second，分别表示递增的三元子序列中的第一个数和第二个数
    贪心思想是：为了找到递增的三元子序列，first和 second应该尽可能地小，此时找到递增的三元子序列的可能性更大
    遇到 num>second时，一定存在一个递增的三元子序列，该三元子序列的第二个数和第三个数分别是 second和 num，因此返回 true
     */
    public boolean increasingTriplet(int[] nums) {
        int first = nums[0];
        int second = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }

    /*
    On + On 双向遍历，维护每个元素左边的最小值和右边的最大值，如果存在左边更小右边更大就是结果
     */
    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(nums[i], leftMin[i - 1]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(nums[i], rightMax[i + 1]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /*
    动态规划-超时  On^2 + On
    DP定义：dp[i] 0~i的最长递增子序列的个数
    转移方程： dp[i]=max(dp[i],dp[j]+1)
    初始化：Arrays.fill(dp, 1); 其自身就是一个子序列
    遍历顺序：fori forj  j是和前面的比
    返回结果：
     */
    public boolean increasingTriplet3(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                if (dp[i] >= 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new P334递增的三元子序列().increasingTriplet(new int[] {20, 100, 10, 12, 5, 13});
        new P334递增的三元子序列().increasingTriplet(new int[] {2, 1, 5, 0, 4, 6});
    }
}
