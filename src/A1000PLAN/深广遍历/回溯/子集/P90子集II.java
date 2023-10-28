package A1000PLAN.深广遍历.回溯.子集;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * desc: https://leetcode.cn/problems/subsets/solutions/229569/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-/?envType=study-plan-v2&envId=top-100-liked
 *
 * @author Lin
 * @since 2023/10/26
 **/
public class P90子集II {
    /*
    包含重复元素
     */
    private List<List<Integer>> res;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(new ArrayDeque<>(), nums, 0);
        return res;
    }

    /*
    ①画出递归树，找到状态变量(回溯函数的参数)，这一步非常重要※       参数 start，来标识当前的选择列表的起始位置
    ②根据题意，确立结束条件               这题每一步都加入结果，所以没有结束条件
    ③找准选择列表(与函数参数相关),与第一步紧密关联※
    ④判断是否需要剪枝     重复元素，排序，剪枝： i>start && nums[i-1]==nums[i]
    ⑤作出选择，递归调用，进入下一层
    ⑥撤销选择
     */
    private void dfs(Deque<Integer> path, int[] nums, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            dfs(path, nums, i + 1);
            path.removeLast();
        }
    }
}
