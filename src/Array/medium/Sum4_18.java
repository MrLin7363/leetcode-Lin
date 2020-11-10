package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe:
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum4_18 {

    // 25+9
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans=new ArrayList<>();
        int length=nums.length;
        if (nums==null || nums.length<4)
            return ans;
        Arrays.sort(nums); // O logn
        for (int i = 0; i < nums.length; i++) {
            if (i>0 && nums[i]==nums[i-1])
                continue; // 去掉第一层循环重复情况
            for (int j = i+1; j < nums.length; j++) {
                if (j>i+1 && nums[j]==nums[j-1])
                    continue;
                // 双指针法
                int left=j+1,right=nums.length-1;
                while (left<right){
                    int sum=nums[i]+nums[j]+nums[left]+nums[right];
                    if (sum==target) {
                        ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));
                        System.out.println(ans);
                        // 移动重复位数
                        left++;
                        right--;
                        while (left<right&&nums[left]==nums[left-1]) left++;
                        while (left<right&&nums[right]==nums[right+1]) right--;
                    }
                    else if (sum<target){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }
        return ans;
    }
    // 加上更多剪枝条件 23+9
    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> ans=new ArrayList<>();
        int length=nums.length;
        if (nums==null || nums.length<4)
            return ans;
        Arrays.sort(nums); // O logn
        for (int i = 0; i < nums.length-3; i++) {
            if (i>0 && nums[i]==nums[i-1])
                continue; // 去掉第一层循环重复情况
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i+1; j < nums.length-2; j++) {
                if (j>i+1 && nums[j]==nums[j-1])
                    continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                // 双指针法
                int left=j+1,right=nums.length-1;
                while (left<right){
                    int sum=nums[i]+nums[j]+nums[left]+nums[right];
                    if (sum==target) {
                        ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));
                        System.out.println(ans);
                        // 移动重复位数
                        left++;
                        right--;
                        while (left<right&&nums[left]==nums[left-1]) left++;
                        while (left<right&&nums[right]==nums[right+1]) right--;
                    }
                    else if (sum<target){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }
        return ans;
    }
}
