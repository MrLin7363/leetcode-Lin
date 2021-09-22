package Array.easy;

/**
 * @author: Junlin Chen
 * @Date: 2021/09/18 14:31
 * @Describe:
 */
public class P283_MoveZeroes {

    /*
    双指针 100+19
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length,left=0,right=0;
        while (right<n){
            if (nums[right]!=0){
                swap(nums,left,right);
                left++;
            }
            right++;
        }
    }

    private void swap(int[] nums, int left , int right){
        int temp=nums[left];
        nums[left]=nums[right];
        nums[right]=temp;
    }

}
