package Array.medium;
    
/**
  *@Author JunLin
  *@Date 2020/11/15
  *@Describe:
 */
public class P151_Find_Minimum_in_Rotated_Sorted_Array {
    public int findMin(int[] nums) {
        if (nums.length==1)
            return nums[0];
        int left=0,right=nums.length-1;
        // 全部有序情况下
        if (nums[right]>nums[0])
            return nums[0];
        while (left<=right){
            int mid=left+(right-left)/2;
            if (nums[mid]>nums[mid+1]){
                return nums[mid+1];
            }
            if (nums[mid-1]>nums[mid]){
                return nums[mid];
            }
            // 左侧有序
            if (nums[mid]>nums[left]){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return -1;
    }
}
