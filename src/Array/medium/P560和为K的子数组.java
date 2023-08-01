package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/8
  *@Describe:
 */

import java.util.HashMap;
import java.util.Map;

public class P560和为K的子数组 {

    /*
     O N 前缀和+哈希   42+6
     */
    public int subarraySum3(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer,Integer> preSumMap=new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumMap.put(0,1);
        int preSum=0;
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            preSum+=nums[i];
            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            // 这里的 preSumMap.containsKey(preSum-k)   key的值 + k = preSum
            if (preSumMap.containsKey(preSum-k))
                count+=preSumMap.get(preSum-k);
            preSumMap.put(preSum,preSumMap.getOrDefault(preSum,0)+1);
        }
        return count;
    }
    public static void main(String[] args) {
        subarraySum2(new int[]{1,2,3,4},5);
    }
    /*
    前缀和 O N^2
     */
    public static int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        // 计算前缀和数组
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                // 区间和 [left..right]，注意下标偏移
                // 相减的结果是区间和的意思，用总的0到right下标所有数的和 - 0到left所有数的和，即是left 到 right的区间和
                if (preSum[right + 1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
    枚举 O N^2
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
