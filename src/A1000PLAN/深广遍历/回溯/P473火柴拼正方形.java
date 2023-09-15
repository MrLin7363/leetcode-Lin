package A1000PLAN.深广遍历.回溯;

import java.util.Arrays;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/29
 **/
public class P473火柴拼正方形 {
    /*
    路径： P698 k=4 的版本看 P698
    选择列表：
    结束条件：
     */
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        int max = -1;
        for (int i = 0; i < matchsticks.length; i++) {
            sum += matchsticks[i];
            max = Math.max(max, matchsticks[i]);
        }
        if (sum % 4 != 0) {
            return false;
        }
        int target = sum / 4;
        if (max > target) {
            return false;
        }
        // 倒序更快
        Arrays.sort(matchsticks);
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }

        int[] bucket = new int[4];
        return dfs(matchsticks, 0, bucket, target);
    }

    private boolean dfs(int[] matchsticks, int index, int[] bucket, int target) {
        if (index == matchsticks.length) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            if (i > 0 && bucket[i] == bucket[i - 1]) {
                continue;
            }
            if (matchsticks[index] + bucket[i] > target) {
                continue;
            }
            bucket[i] += matchsticks[index];
            if (dfs(matchsticks, index + 1, bucket, target)) {
                return true;
            }
            bucket[i] -= matchsticks[index];
        }
        return false;
    }
}
