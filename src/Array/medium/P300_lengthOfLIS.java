package Array.medium;

/**
 * @author: Junlin Chen
 * @Date: 2021/10/04 16:31
 * @Describe:
 */
public class P300_lengthOfLIS {
    /*
    DP  60+79
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n==0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0]=1;
        int maxAnx=1;
        for (int i = 1; i < n; i++) {
            dp[i]=1;
            for (int j = 0; j < i; j++) {
                if (nums[j]<nums[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            maxAnx=Math.max(maxAnx,dp[i]);
        }
        return maxAnx;
    }
}
