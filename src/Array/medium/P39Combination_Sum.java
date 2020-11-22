package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/21
  *@Describe:
 */

import java.util.*;

public class P39Combination_Sum {

    /*
    自己写的 41 + 33
    41 + 98
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates.length == 0) {
            return ans;
        }
        Arrays.sort(candidates);
        backtrack(candidates, target, ans, 0, new ArrayDeque<Integer>(), 0);
        System.out.println(ans);
        return ans;
    }

    private static void backtrack(int[] candidates, int target, List<List<Integer>> ans, int sum, Deque<Integer> path, int startId) {
        // 结束条件： >= target
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = startId; i < candidates.length; i++) {
            // 因为数组拍好了序，所以后面肯定比target大
            if (sum + candidates[i] > target) {
                break;
            }
            // 改进不用管 sum 变量 ，空间 从 31 -> 98
//            sum+=candidates[i];
            path.addLast(candidates[i]);
            // 因为下一次的搜索可以重复元素相加，所以 i 不加1
            backtrack(candidates, target, ans, sum + candidates[i], path, i);
//            sum-=candidates[i];
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        combinationSum2(new int[]{3, 1, 4, 2}, 4);
    }

    /*
    ----------------------------------------------------
    https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
    target - =0 的方式，差不多思路
    5 + 52
     */

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs2(candidates, 0, len, target, path, res);
        System.out.println(res);
        return res;
    }

    private static void dfs2(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            dfs2(candidates, i, len, target - candidates[i], path, res);

            path.removeLast();
            System.out.println("递归之后 => " + path);

        }
    }
    /*
    官方答案 100
    难懂暂时不看
     */
    public static List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs3(candidates, target, ans, combine, 0);
        System.out.println(ans);
        return ans;
    }

    public static void dfs3(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs3(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            System.out.println("递归之前 => " + combine + "，剩余 = " + (target - candidates[idx]));
            dfs3(candidates, target - candidates[idx], ans, combine, idx);
            System.out.println("递归之后 => " + combine + "，剩余 = " + (target - candidates[idx]));
            combine.remove(combine.size() - 1);
        }
    }
}
