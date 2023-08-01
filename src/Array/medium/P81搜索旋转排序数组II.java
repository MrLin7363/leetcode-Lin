package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/15
  *@Describe: 与35区别是包含重复元素
 */

public class P81搜索旋转排序数组II {
    public boolean search(int[] nums, int target) {
        int left=0,right=nums.length-1;
        while (left<=right){
            int mid=left+(right-left)/2;
            if (nums[mid]==target){
                return true;
            }
            // 如果元素相同情况下，往后进
            if (nums[left] == nums[mid]) {
                left++;
                continue;
            }
            // mid左侧是有序数组
            if (nums[left]<=nums[mid]){
                // 往有序数组里面搜索
                if (nums[left]<=target && target<nums[mid]){
                    right=mid-1;
                }else{ // 往右侧无序数组搜索
                    left=mid+1;
                }
            }else{ // 右侧是有序数组
                // 往右侧有序数组搜索
                if (nums[mid]< target && target<= nums[nums.length-1]){
                    left=mid+1;
                }else{ // 往左侧无序数组搜索
                    right=mid-1;
                }
            }
        }
        return false;
    }
}
