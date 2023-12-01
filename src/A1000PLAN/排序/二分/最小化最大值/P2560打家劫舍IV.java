package A1000PLAN.排序.二分.最小化最大值;

import java.util.Arrays;

/**
 *desc: https://leetcode.cn/problems/house-robber-iv/solutions/2093952/er-fen-da-an-dp-by-endlesscheng-m558/
 *@author lin
 *@since 2023/11/28
 **/
public class P2560打家劫舍IV {
    /*
    二分+DP
    此处二分值越小，越满足要求 ,左闭右闭区间找左边界, []闭区间
    最小化最大值
     */
    public int minCapability(int[] nums, int k) {
        int left = Arrays.stream(nums).min().getAsInt();
        int right = Arrays.stream(nums).max().getAsInt();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 最多能偷x间如果>=k 说明还可以最小化,可以减少偷的间数
            if (check(nums, k, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    /*
    DP 定义： dp[i]: 0~i中偷金额不超过val的房屋，最多能偷多少间
    转移方程： d[i]=Max(dp[i-1],dp[i-2]+1)
    可以简化为常量空间
     */
    private boolean check(int[] nums, int k, int val) {
        if (nums.length == 1) {
            return nums[0] <= val;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0] <= val ? 1 : 0;
        dp[1] = nums[1] <= val ? 1 : dp[0];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] <= val) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + 1);
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[nums.length - 1] >= k;
    }

    /*
    最优：二分+贪心
    2.左开右闭区间找左边界 [)
     */
    public int minCapability2(int[] nums, int k) {
        int left = Arrays.stream(nums).min().getAsInt();
        int right = Arrays.stream(nums).max().getAsInt() + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 最多能偷x间如果>=k 说明还可以最小化,可以减少偷的间数
            if (check2(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 贪心
    private boolean check2(int[] nums, int k, int mx) {
        int cnt = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= mx) {
                cnt++; // 偷 nums[i]
                i++; // 跳过下一间房子
            }
        }
        return cnt >= k;
    }
    public static void main(String[] args) {
        new P2560打家劫舍IV().minCapability(new int[] {1}, 1);
        new P2560打家劫舍IV().minCapability2(new int[] {2, 3, 5, 9}, 2);
    }
}
