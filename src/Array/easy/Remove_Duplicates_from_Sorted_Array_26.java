package Array.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/22 12:29
 * @Describe:
 */
public class Remove_Duplicates_from_Sorted_Array_26 {
    public static int removeDuplicates(int[] nums) {
        int i=0;
        if(nums.length==0)
            return 0;
        for(int j=1;j<nums.length;j++){
            if(nums[i]!=nums[j]){
                i++;
                nums[i]=nums[j];
            }
        }
        return i+1;
        /*int len=1;
        while(i<nums.length-1){
            if (nums[i]==nums[i+1]){
                i++;
            }else{
                len++;
                i++;
            }
        }
        return len;*/
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,1,2}));
    }
}
