package DynamicProgramming.medium;

/**
 * @author: Junlin Chen
 * @Date: 2020/11/25 21:42
 * @Describe: 分割等和子集:给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 如 5 & 1
 *  101
 *  001
 * =001=1
 * 为奇数
 */
public class P416Partition_Equal_Subset_Sum {

    /*
    一维数组
     */
    public boolean canPartition2(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        //特判：如果是奇数，就不符合要求
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; //如果第一列不为true，则下面要加个if判断
        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }
        for (int i = 0; i < len; i++) {
            for (int j = target; j >= 0 && nums[i] <= j; j--) {
                if (dp[target]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    /*
    48 + 54 初始化为0的二维数组优化版本
     */
    public boolean canPartition(int[] nums) {
        int len=nums.length;
        int sum=0;
        for (int i = 0; i < len; i++) {
            sum+=nums[i];
        }
        //特判：如果是奇数，就不符合要求
        if ( (sum&1)==1){
            return false;
        }
        int target=sum/2;
        boolean[][] dp=new boolean[len][target+1];
        dp[0][0]=true; //如果第一列不为true，则下面要加个if判断
        // 单独一个数肯定可以
        // 第一行默认只有 dp[0][0] 和 dp[0][nums[0]] =true 其他都为false
        if (nums[0]<=target){
            dp[0][nums[0]]=true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                // 上一行的元素，只选上一个元素
                dp[i][j]=dp[i-1][j];
                // ==选取当前元素 >选取前一列的元素
                if (j>=nums[i]){
                    dp[i][j]=dp[i-1][j] || dp[i-1][j-nums[i]];
                }
                // 说明有元素能组成偶数和的一半
                if (dp[i][target]){
                    return true;
                }
            }
        }
        return dp[len-1][target];
    }

}
