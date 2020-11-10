package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe: 返回靠近target的三数之和值
  排序+双指针  89+12
 */

import java.util.Arrays;

public class Sum3_Closest_16 {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1,0,1,1,55},3));
    }
    public static int threeSumClosest(int[] nums, int target) {
        if (nums==null || nums.length<3)
            return -1;
        Arrays.sort(nums); // O logn
//        int best=Integer.MAX_VALUE;
        int diff=Integer.MAX_VALUE; // 差距最大
        for (int i = 0; i < nums.length; i++) {
            if (i>0 && nums[i]==nums[i-1])
                continue; // 去掉第一层循环重复情况
            int left=i+1,right=nums.length-1;
            while (left<right){
                int sum=nums[i]+nums[left]+nums[right];
                if (sum==target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
//                if (Math.abs(sum-target)<Math.abs(best-target)) // 注意如果best直接用Integer最大值，- target可能会溢出。
                if (Math.abs(sum-target)<Math.abs(diff))
                    diff=target-sum;
                // 移动到下一个不相等的元素
                if (sum>target){
                    right--;
                    while (left<right && nums[right]==nums[right+1])
                        right--;
                }else{
                    left++;
                    while (left<right && nums[left]==nums[left-1])
                        left++;
                }
            }
        }
        return target-diff;
    }
}
