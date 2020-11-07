package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe: 三数之和=0
   39+8
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum3_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        if (nums==null || nums.length<3)
            return ans;
        Arrays.sort(nums); // O logn
        for (int i = 0; i < nums.length; i++) {
            if (nums[0]>0) break; // 剪：第一个数大于 0，后面的数都比它大，肯定不成立了
            if (i>0 && nums[i]==nums[i-1])
                continue; // 去掉第一层循环重复情况
            int left=i+1,right=nums.length-1;
            int target=-nums[i]; // 后面两个数相加=-num[i] ，三数之和=0
            // 下面是双指针法求两数之和题目
            while (left<right){
                if (nums[left]+nums[right]==target) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0,
                    // left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }else if (nums[left]+nums[right]<target){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return ans;
    }


}
