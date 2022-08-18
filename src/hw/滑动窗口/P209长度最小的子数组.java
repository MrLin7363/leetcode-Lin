 

package hw.滑动窗口;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/11/26
 **/
public class P209长度最小的子数组 {

    /*
    给定一个含有 n 个正整数的数组和一个正整数 target
    找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
    加强版，右指针不左移  99+66
    求最小，符合条件收缩左节点，对于右端点，求最小的左端点
    求最大，不符合条件收缩左节点，对于左端点，求最大的右端点
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            // 符合条件
            while (sum >= target) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


    /*
    自己写的垃圾版，注意是大于等于目标target不是等于   8+90
     */
    public static int minSubArrayLen2(int target, int[] nums) {
        int left=0;
        int right=0;
        int sum=0;
        int minRes=Integer.MAX_VALUE;
        int n=nums.length;
        while(left<n&&right<n){
            sum+=nums[right];
            if (sum>=target){
                minRes=Math.min(minRes,right-left+1);
                left++;
                right=left;
                sum=0;
            }else{
                right++;
            }
        }
        return minRes==Integer.MAX_VALUE?0:minRes;
    }

    public static void main(String[] args) {
        minSubArrayLen(11,new int[]{1,2,3,4,5});
    }

}
