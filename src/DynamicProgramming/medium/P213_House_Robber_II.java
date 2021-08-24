package DynamicProgramming.medium;

/**
 * @author: chenjunlin
 * @since: 2021/08/09
 * @descripe: 相邻不能偷，数组是个环，最后一个和第一个是接着的
 */
public class P213_House_Robber_II {

    public int rob(int[] nums) {
        if (nums.length==1){
            return nums[0];
        }
        if (nums.length==2){
            return Math.max(nums[0],nums[1]);
        }
        return Math.max(getIntervalMax(nums,0,nums.length-2),getIntervalMax(nums,1,nums.length-1));
    }

    private int getIntervalMax(int[]nums,int begin,int end){
        int first = nums[begin];
        int second = Math.max(first,nums[begin+1]);
        for (int i = begin+2; i <= end; i++) {
            int temp = second;
            second = Math.max(second,first+nums[i]);
            first=temp;
        }
        return second;
    }

}
