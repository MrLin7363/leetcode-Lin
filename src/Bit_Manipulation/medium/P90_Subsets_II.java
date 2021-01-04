package Bit_Manipulation.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/2
  *@Describe:
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P90_Subsets_II {

    /*
    回溯剪枝 99 + 87
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> temp=new ArrayList<>();
        List<List<Integer>> ans=new ArrayList<>();
//        ans.add(temp);
        Arrays.sort(nums); // 排除重复的要先排序
        backtrack(ans,temp,0,nums);
        return ans;
    }
    public void backtrack(List<List<Integer>> ans,List<Integer> temp,int start,int[] nums){
        ans.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            //和上个数字相等就跳过
            if (i>start && nums[i]==nums[i-1]){
                continue;
            }
            temp.add(nums[i]);
//            ans.add(new ArrayList<>(temp));  如果加在这里，则要在主函数的一开始就添加空子集
            backtrack(ans,temp,i+1,nums);
            temp.remove(temp.size()-1);
        }
    }

    /*
  循环迭代，记住原先旧的解个数   99 + 67
   */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        ans.add(new ArrayList<>());// 空子集
        Arrays.sort(nums);
        int start=1;
        for (int i = 0; i < nums.length; i++){
            List<List<Integer>> ans_temp=new ArrayList<>(); // 每次初始化
            int size=ans.size();
            for (int j = 0; j < size; j++) {
                // 在上次旧解的基础上加的是重复解,注意 i 和 j 的区别
                if ( i>0 && nums[i]==nums[i-1] && j<start ){
                    continue;
                }
                // 注意这里要新建，否则引用原来的部分了
                List<Integer> list = ans.get(j);
                List<Integer> temp=new ArrayList<>(list);
                temp.add(nums[i]);
                ans_temp.add(temp);
            }
            // 更新前解的开始位置, <start 就是旧解
            start=ans.size();
            ans.addAll(ans_temp);
        }
        return ans;
    }
}
