package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/19
  *@Describe: 给定的数据没有重复的
  排列
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P46_Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int len=nums.length;
        if (len==0) {
            return ans;
        }
        boolean[] used=new boolean[len];
        Deque<Integer> path = new ArrayDeque<>();
        backtrack(nums,used,0,ans,path);
        return ans;
    }
    public static void backtrack(int[] nums,boolean[] used, int idx, List<List<Integer>> ans,Deque<Integer> path ){
        if (idx==nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 前面的不要再用
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            System.out.println("  递归之前 => " + path);
            backtrack(nums, used, idx + 1, ans, path);
            System.out.println("递归之后 => " + path);
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
