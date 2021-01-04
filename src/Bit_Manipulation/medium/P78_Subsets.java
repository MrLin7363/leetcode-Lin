package Bit_Manipulation.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Junlin Chen
 * @Date: 2020/12/30 19:45
 * @Describe: 题目的字串不会有重复的
 */
public class P78_Subsets {
    /*
    回溯： 树每一层是遍历nums元素  100 + 99
     */
    public List<List<Integer>> subsets(int[] nums) {
        ans.add(temp); // 添加一开始的空字符串
        backtrack(0,nums);
        return ans;
    }
    public void backtrack(int idx,int[] nums) {
        // 因为这里不需要==nums.length就可以算是一种结果，所以不用递归出口条件
        // ans.add(new ArrayList<>(temp)); 这行可以加在这里
        for (int i = idx; i < nums.length; i++) {
            // if (i > idx && nums[i] == nums[i - 1]) continue;  无重复，所以不需要剪枝
            temp.add(nums[i]);
            ans.add(new ArrayList<>(temp));
            backtrack(i+1,nums);
            temp.remove(temp.size()-1);
        }
    }
    /*
    dfs 100 + 73  树每一层是选或者不选当前元素
     */
    public List<List<Integer>> subsets2(int[] nums) {
        dfs(0, nums);
        return ans;
    }
    public void dfs(int idx,int[] nums){
        if (idx==nums.length){
            ans.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[idx]);
        dfs(idx+1,nums);
        temp.remove(temp.size()-1);
        dfs(idx+1,nums);
    }
    /*
    位运算迭代 100 + 73
     */
    List<Integer> temp=new ArrayList<>();
    List<List<Integer>> ans=new ArrayList<>();
    public List<List<Integer>> subsets4(int[] nums) {
        int n=nums.length;
        // 小于最多情况的 01序列， 如 nums={2,5,9}  则< 1000
        for (int mask=0; mask < (1<<n) ; mask++){
            temp.clear();
            for (int i = 0; i < n; i++) {
                // 如 mask == 111 则是三位数都添加
                if ( (mask & (1<<i) )!=0){
                    temp.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(temp));
        }
        return ans;
    }
    /*
    递归枚举  100 + 45
     */
    public List<List<Integer>> subsets3(int[] nums) {
          ans.add(new ArrayList<>());
          recursion(nums,0,ans);
          return ans;
    }
    public void recursion(int[] nums, int i, List<List<Integer>> res) {
        if (i>=nums.length) return;
        int size=res.size();
        for (int j = 0; j < size; j++) {
            List<Integer> newSub = new ArrayList<Integer>(res.get(j));
            newSub.add(nums[i]);
            res.add(newSub);
        }
        recursion(nums,i+1,res);
    }
    /**
     *  循环枚举 100 + 85
     */
    public static List<List<Integer>> enumerate(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<Integer>(res.get(i));
                newSub.add(n);
                res.add(newSub);
            }
        }
        return res;
    }
}
