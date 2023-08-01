package Array.排列;
    
/**
  *@Author JunLin
  *@Date 2020/11/19
  *@Describe: 给定的数据没有重复的     211 112 算两个排列，只算一个组合
  排列
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P46全排列 {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int len = nums.length;
        if (len == 0) {
            return ans;
        }
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>();
        backtrack(nums, used, 0, ans, path);
        return ans;
    }

    // idx记录填充了几个  可以不要idx,直接使用path.size()=nums.length判断填充了几个更好
    public static void backtrack(int[] nums, boolean[] used, int idx, List<List<Integer>> ans, Deque<Integer> path) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 这里i不能==idx这种，因为假如选取第二个元素，那么下一个元素可以是第一个，而不是不能选前面的
        for (int i = 0; i < nums.length; i++) {
            // 前面的不要再用
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            backtrack(nums, used, idx + 1, ans, path);
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = permute(nums);
        System.out.println(lists);
    }
}
