package Array.medium;

/**
 * @author: Junlin Chen
 * @Date: 2020/11/10 19:28
 * @Describe:
 *
 */
public class First_and_Last_Position_Element_in_Sorted_Array_34 {
    /*
    二分：往左二分，往右二分，两次确定左右断点
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] res=new int[]{-1,-1};
        int left=binarySearch(nums,target,true);
        if (left==nums.length || nums[left]!=target) // target比所有数都要大，或者不等于
            return new int[]{-1,-1};
        int right=binarySearch(nums,target,false)-1;
        return new int[]{left,right};
    }
    private static int binarySearch(int[] nums,int target,boolean isLeft){
        int left=0,right=nums.length-1;
        while (left<=right){
            int mid=left+(right-left)/2;
            // 左进如果是相等情况下，搜索左端点时要再左进
            if (nums[mid]>target || ( isLeft && nums[mid]==target )){
                right=mid-1;
            }else {
                // 如果相等且不是搜索左端点时，右进
                left=mid+1;
            }
        }
        return left;
    }
    public static void main(String[] args) {
        System.out.println(searchRange(new int[]{0,1,1,1,3,4},1)[1]);
    }
}
