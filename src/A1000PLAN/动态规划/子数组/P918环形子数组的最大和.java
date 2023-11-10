package A1000PLAN.动态规划.子数组;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *desc:
 *@author lin
 *@since 2023/11/10
 **/
public class P918环形子数组的最大和 {
    /*
    最大的环形子数组和 = max(最大子数组和，数组总和-最小子数组和)
    https://leetcode.cn/problems/maximum-sum-circular-subarray/solutions/1152143/wo-hua-yi-bian-jiu-kan-dong-de-ti-jie-ni-892u/?envType=study-plan-v2&envId=top-interview-150
    DP定义:
    转移方程：
    遍历顺序：同时求最大子数组和 最小子数组和 数组总和
    初始化：==0时
    结果返回: max(最大子数组和，数组总和-最小子数组和)  第一种情况是最大子数组没有跨前后， 第二种情况跨前后
     */
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 两个数组可以优化成常量级
        int[] maxDp = new int[n];
        int[] minDp = new int[n];
        int max = nums[0];
        int min = nums[0];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            // 求最大子数组和
            maxDp[i] = Math.max(maxDp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, maxDp[i]);

            // 求最小子数组和
            minDp[i] = Math.min(minDp[i - 1] + nums[i], nums[i]);
            min = Math.min(min, minDp[i]);

            sum += nums[i];
        }
        // 注意：如果max<0，此时数组不包含>=0的元素，minDp包括了所有的数组，导致我们取到的子数组实际为空
        if (max < 0) {
            return max;
        }
        return Math.max(max, sum - min);
    }

    /*
    很难理解，不记-不推荐
    单调队列 + 前缀和: 问题转换为了在一个长度为 2n 的数组上，寻找长度不超过 n 的最大子数组和。
    https://leetcode.cn/problems/maximum-sum-circular-subarray/solutions/2254925/918-huan-xing-zi-shu-zu-de-zui-da-he-by-75meq/?envType=study-plan-v2&envId=top-interview-150
    prefixSums[i] 表示数组 repeated 从下标 0 到下标 i−1 的元素之和
    单调队列存储下标，满足从队首到队尾的下标对应的 prefixSums 的元素单调递增
     */
    public int maxSubarraySumCircular3(int[] nums) {
        int n = nums.length;
        int repeatLength = n * 2;
        int[] prefixSums = new int[repeatLength + 1];
        for (int i = 0; i < repeatLength; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i % n];
        }
        int maxSum = nums[0];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);
        for (int i = 1; i <= repeatLength; i++) {
            int prefixSum = prefixSums[i];
            if (deque.peek() < i - n) {
                deque.pollFirst();
            }
            int sum = prefixSum - prefixSums[deque.peekFirst()];
            maxSum = Math.max(maxSum, sum);
            while (!deque.isEmpty() && prefixSums[deque.peekLast()] >= prefixSum) {
                deque.pollLast();
            }
            deque.offer(i);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        new P918环形子数组的最大和().maxSubarraySumCircular3(new int[] {-3, -2, -3});
        new P918环形子数组的最大和().maxSubarraySumCircular3(new int[] {1, -2, 3, -2});
        new P918环形子数组的最大和().maxSubarraySumCircular(new int[] {8, 15, -10, 19});
    }
}
