package A1000PLAN.贪心;

import java.util.Arrays;

/**
 * desc: 到达最后一个点的最小跳跃次数
 *
 * @author Lin
 * @since 2023/9/11
 **/
public class P45跳跃游戏II {
    /*
    贪心：推荐 正向查找-每次到达最远位置更新跳跃次数，因为此时想不跳也不行
     */
    public int jump(int[] nums) {
        int maxPos = 0;
        int rightmost = 0;
        int step = 0;
        // 在遍历数组时，我们不访问最后一个元素，这是因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置，
        // 否则就无法跳到最后一个位置了。如果访问最后一个元素，在边界正好为最后一个位置的情况下，我们会增加一次「不必要的跳跃次数」，因此我们不必访问最后一个元素
        for (int i = 0; i < nums.length - 1; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == rightmost) {
                rightmost = maxPos;
                step++;
            }
        }
        return step;
    }

    /*
    贪心：反向查找-时间较长 O n^2
    */
    public int jump2(int[] nums) {
        int position = nums.length - 1;
        int step = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    step++;
                    break;
                }
            }
        }
        return step;
    }

    // 不好理解-不看
    public int jump3(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }

        int[] dp = new int[len]; //包含数组下标i的最小跳跃次数
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if ((j + nums[j] >= i)) { //可以从j到达i位置
                    dp[i] = Math.min(dp[i], dp[j] + 1); //从j到i只需要跳1次
                }
            }
        }

        return dp[len - 1];
    }

    public static void main(String[] args) {
        System.out.println(new P45跳跃游戏II().jump2(new int[] {2, 2, 1, 1, 4}));
        System.out.println(new P45跳跃游戏II().jump(new int[] {2})); // 一个数无需跳跃
    }
}
