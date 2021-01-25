package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/15
  *@Describe: DFS回溯- 剪枝必须，否则会有重复排列
  N * N!
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P47_Permutations_II {
    static boolean[] vis;

    public static void main(String[] args) {
        permuteUnique(new int[]{1,3,2,4,1});
    }
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public static void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !vis[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            // 1.vis[i]==true 是为了跳过每一层DFS for循环前面的数值，直接从剩余的数字中进行for循环
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }
}
