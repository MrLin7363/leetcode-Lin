package Array.hard;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/11
  *@Describe:
 */

public class P154寻找旋转排序数组中的最小值II {
    /*
    二分 medium题 13+6
     */
    public int findMin(int[] nums) {
        int low=0,high=nums.length-1;
        while (low<high){
            int mid=low+(high-low)/2;
            // 最小值在mid右边
            if (nums[mid]>nums[high]){
                low=mid+1;
            }else if (nums[mid]<nums[high]){
                high=mid; // 注意不要-1
            }else{ // 相等的情况下，-high知道low=high，因为有相等的元素
                high-=1;
            }
        }
        return nums[low];
    }
}
