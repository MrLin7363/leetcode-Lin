package A1000PLAN.动态规划;

import java.util.Arrays;

/**
 * desc: 摆动序列： 差值一正一负，或者只有一个差值； 1个元素没有差值不算摆动序列
 *
 * @author Lin
 * @since 2023/8/15
 **/
public class P376摆动序列 {
    /*
    DP 简化1版  因为dp数组的值由上一个元素决定，所以可以数组>元素
    up[i]表示前i个元素中 某个为结尾的最长的【上升摆动序列】的长度
    down[i]表示前i个元素中 某个为结尾的最长的【下降摆动序列】的长度
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int up=1;
        int down=1;
        for (int i = 1; i < n; i++) {
            // 持续上升情况
            if (nums[i] > nums[i - 1]) {
                //! 上升摆动：
                //情况1：连续的上升，不用当前元素，沿用之前的上升摆动长度值
                //情况2：下降过程，从上一个下降摆动过来 + 当前元素 成为上升摆动
                up = Math.max(up, down + 1);  // 终极版 省去比较过程， up=down+1
                //! 此过程是上升摆动，所以下降摆动未变化，所以沿用之前的值
            } else if (nums[i] < nums[i - 1]) {
                //! 下降摆动：
                //情况1：连续的下降，不用当前元素，沿用之前的下降摆动长度
                //情况2: 下降过程，由上一个上升摆动过来 + 当前元素 成为下降摆动
                down = Math.max(up + 1, down); // 终极版 down=up+1
                //! 此过程是下降摆动，所以上升摆动未变化，所以沿用之前的值
            }
        }
        return Math.max(up, down);
    }

    /*
    DP 未简化版
    up[i]表示前i个元素中 某个为结尾的最长的【上升摆动序列】的长度
    down[i]表示前i个元素中 某个为结尾的最长的【下降摆动序列】的长度
     */
    public int wiggleMaxLength3(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] up = new int[n];
        int[] down = new int[n];
        Arrays.fill(up, 0);
        Arrays.fill(down, 0);
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                //! 上升摆动：
                //情况1：连续的上升，不用当前元素，沿用之前的上升摆动长度值
                //情况2：下降过程，从上一个下降摆动过来 + 当前元素 成为上升摆动
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);

                //! 此过程是上升摆动，所以下降摆动未变化，所以沿用之前的值
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                //! 下降摆动：
                //情况1：连续的下降，不用当前元素，沿用之前的下降摆动长度
                //情况2: 下降过程，由上一个上升摆动过来 + 当前元素 成为下降摆动
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);

                //! 此过程是下降摆动，所以上升摆动未变化，所以沿用之前的值
                up[i] = up[i - 1];
            } else {
                //!既不是上升也不是下降，直接沿用直接的值
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    public static void main(String[] args) {
        new P376摆动序列().wiggleMaxLength(new int[] {5, 6, 7, 4, 6});
    }
}
