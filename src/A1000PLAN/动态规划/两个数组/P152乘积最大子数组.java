package A1000PLAN.动态规划.两个数组;

/**
 * desc:找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积
 * 子数组其实和子串差不多,只不过子串是字符串
 *
 * @author Lin
 * @since 2023/9/11
 **/
public class P152乘积最大子数组 {
    /*
    解释：如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样就可以负负得正，并且我们希望这个积尽可能「负得更多」，
        即尽可能小。如果当前位置是一个正数的话，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大
    DP定义：dp[i] 以第 i 个元素结尾的乘积最大子数组的乘积
    转移方程： 如果都是正数  dp[i]=Math.max(nums[i],dp[i-1]*nums[i]) 但这里有负数
            maxF[i]=Math.max(nums[i],maxF[i-1]*nums[i],minF[i-1]*nums[i])
            minF[i]=Math.max(nums[i],maxF[i-1]*nums[i],minF[i-1]*nums[i])
    初始化：只取当前位置的dp数据就是nums[i]
    */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] maxF = new int[n];
        int[] minF = new int[n];
        // 初始化
        System.arraycopy(nums, 0, maxF, 0, maxF.length);
        System.arraycopy(nums, 0, minF, 0, minF.length);

        for (int i = 1; i < n; i++) {
            maxF[i] = Math.max(nums[i], Math.max(maxF[i - 1] * nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(nums[i], Math.min(maxF[i - 1] * nums[i], minF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }

    // 优化为转化为常量
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int maxV = nums[0];
        int minV = nums[0];
        int ans = nums[0];
        for (int i = 1; i < n; i++) {
            int mx = maxV;
            int mi = minV;
            maxV = Math.max(nums[i], Math.max(mx * nums[i], mi * nums[i]));
            minV = Math.min(nums[i], Math.min(mx * nums[i], mi * nums[i]));
            ans = Math.max(ans, maxV);
        }
        return ans;
    }

    public static void main(String[] args) {
        new P152乘积最大子数组().maxProduct(new int[] {2, 3, -2, 4});
        new P152乘积最大子数组().maxProduct2(new int[] {2, 3, -2, 4});
    }
}
