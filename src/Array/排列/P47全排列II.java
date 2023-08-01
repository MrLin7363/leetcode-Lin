package Array.排列;
    
/**
  *@Author JunLin
  *@Date 2020/11/15
  *@Describe: DFS回溯- 剪枝必须，否则会有重复排列
  N * N!
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class P47全排列II {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[n];
        dfs(res, new ArrayDeque<>(), nums, 0, used);
        return res;
    }

    // idx记录填充到了几个
    private void dfs(List<List<Integer>> res, Deque<Integer> path, int[] nums, int idx, boolean[] used) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !vis[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            // 假如1,1,2   1已经遍历完了，第二个1的时候开头一样应该取消掉
            // nums[i] == nums[i - 1]能判断重复元素是数组排过序了
            // 1.vis[i]==true 是为了跳过每一层DFS for循环前面的数值，直接从剩余的数字中进行for循环
            if (used[i] || (i > 0 && nums[i] == nums[i - 1]) && !used[i - 1]) {
                continue;
            }
            path.offerLast(nums[i]);
            used[i] = true;
            dfs(res, path, nums, idx + 1, used);
            used[i] = false;
            path.pollLast();
        }
    }
}
