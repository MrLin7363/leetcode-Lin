package DynamicProgramming.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/28
  *@Describe: 数组和不会超过1000
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P494Target_Sum {
    /*
   动态规划-两个一维数组 - 60+5
    */
    public static int findTargetSumWays(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            // 一行行遍历，所以要遍历完一行再确定,注意每次都要初始化，否则原先的数组存在的元素会产生干扰
            int[] next=new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp=next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }
    /*
    动态规划-二维 - 60+5
     */
    public static int findTargetSumWays2(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }

    public static void main(String[] args) {
        findTargetSumWays(new int[]{2,1,1,2},0);
    }
    /*
    递归记忆化 memo[][] 二维数组，官方  60+5
     */
    public int findTargetSumWays3(int[] nums, int S) {
        int[][] memo=new int[nums.length][2001]; // 解决数组下标相减后为负数情况，默认加1000
        for(int[] row:memo){
            Arrays.fill(row,Integer.MIN_VALUE);
        }
        return calculate(nums,0,0,S,memo);
    }
    private static int  calculate(int[] nums, int i, int sum, int target, int[][] memo) {
        if (i == nums.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        } else {
            if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                return memo[i][sum + 1000];
            }
            int add = calculate(nums, i + 1, sum + nums[i], target, memo);
            int subtract = calculate(nums, i + 1, sum - nums[i], target, memo);
            memo[i][sum+1000]=add+subtract;
            return memo[i][sum+1000];
        }
    }



        // 记忆化搜索-递归  46+10  hashMap
    private static Map<String,Integer> memo=new HashMap<>();
    public int findTargetSumWays4(int[] nums, int S) {
        return nums.length==0?0:backtrack4(nums,0,S);
    }
    private static int  backtrack4(int[] nums, int i, int rest){
        if(i==nums.length ){
            if (rest==0){
                return 1;
            }
            return 0;
        }
        // 把它俩转成字符串才能作为哈希表的键
        String key = i + "," + rest;
        // 避免重复计算
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int result=backtrack4(nums,i+1,rest-nums[i]) + backtrack4(nums,i+1,rest+nums[i]);
        memo.put(key,result);
        return result;
    }
}
