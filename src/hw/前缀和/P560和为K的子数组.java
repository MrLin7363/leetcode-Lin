

package hw.前缀和;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/30
 **/
public class P560和为K的子数组 {

    public int subarraySum2(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumMap = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumMap.put(0, 1);
        int preSum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            // 先获得前缀和为 preSum - k 的个数，加到计数变量里    这里的 preSumMap.containsKey(preSum-k)
            // key的值 + k = preSum
            if (preSumMap.containsKey(preSum - k))
                count += preSumMap.get(preSum - k);
            preSumMap.put(preSum, preSumMap.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    /*
    P[i]=nums[0]+...+nums[i] P 是前缀和数组 , 每个连续子数组和 sum(i,j)可以写成 P[j]-P[i-1]
     */
    public int subarraySum(int[] nums, int k) {
        //前缀和数组
        int[] presum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            //这里需要注意，我们的前缀和是presum[1]开始填充的
            presum[i + 1] = nums[i] + presum[i];
        }
        //统计个数
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i; j < nums.length; ++j) {
                //注意偏移，因为我们的nums[2]到nums[4]等于presum[5]-presum[2]
                //所以这样就可以得到nums[i,j]区间内的和
                if (presum[j + 1] - presum[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        P560和为K的子数组 bb = new P560和为K的子数组();
//        System.out.println(bb.subarraySum(new int[]{1, 1, 1}, 2));
//        System.out.println(bb.subarraySum(new int[]{1, 2, 3}, 3));
        System.out.println(bb.subarraySum(new int[]{8, 8, -16, 4, 12}, 16));
        System.out.println(bb.subarraySum(new int[]{1,3,2}, 3));
//        System.out.println(bb.subarraySum2(new int[]{1, -1, 0}, 0));
    }

}
