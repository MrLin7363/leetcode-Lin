package A1000PLAN.深广遍历.回溯;

import java.util.Arrays;

/**
 * desc: P473火柴拼正方形  2305. 公平分发饼干  都一样
 *
 * @author Lin
 * @since 2023/8/29
 **/
public class P698划分为k个相等的子集 {
    /*
    回溯：剪枝，层：球  选：桶
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int max = -1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        if (sum % k != 0) {
            return false;
        }
        if (max > sum / k) {
            return false;
        }

        // 降序的情况，下面剪枝更快
        int[] reverse = Arrays.stream(nums).boxed().sorted((o1, o2) -> o2 - o1).mapToInt(n1 -> n1).toArray();

        int[] bucket = new int[k];
        return dfs(reverse, k, 0, bucket, sum / k);
    }

    /*
    球视角，每一次选择桶，尽可能让每次选择项少
     */
    private boolean dfs(int[] nums, int k, int index, int[] bucket, int target) {
        if (index == nums.length) {
            // 优化：因为当 index == num.length 时，所有球已经按要求装入所有桶，所以肯定是一个满足要求的解
            // for (int i = 0; i < k; i++) {
            //     if (bucket[i] != target) {
            //         return false;
            //     }
            // }
            return true;
        }
        for (int i = 0; i < k; i++) {
            // 剪枝：原因：如果元素和相等，那么 nums[index] 选择上一个桶和选择当前桶可以得到的结果是一致的
            if (i > 0 && bucket[i] == bucket[i - 1]) {
                continue;
            }
            // 剪枝：选择下一个桶
            if (bucket[i] + nums[index] > target) {
                continue;
            }
            //  加入桶
            bucket[i] += nums[index];

            // 有符合条件的
            if (dfs(nums, k, index + 1, bucket, target)) {
                return true;
            }

            // 移出桶
            bucket[i] -= nums[index];
        }
        return false;
    }

    public static void main(String[] args) {
        new P698划分为k个相等的子集().canPartitionKSubsets(new int[] {4, 4, 4}, 3);
    }
}
