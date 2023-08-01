package Array.easy;

/**
 * @author: Junlin Chen
 * @Date: 2021/07/04 20:55
 * @Describe:
 */
public class P268丢失的数字 {
    /*
    位运算
     */
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
    /*
    数学公式
     */
    public int missingNumber2(int[] nums) {
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }
}
