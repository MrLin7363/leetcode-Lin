 

package hw.前缀和;

import java.util.HashMap;
import java.util.Map;

/**
 * desc: k 的倍数，个数至少为2
 * 注意 0 始终视为 k 的一个倍数
 * @author junlin
 * @since 2022/1/29
 **/
public class P523连续的子数组和 {

    /**
     * 同余定理
     * 前缀和 + map
     * P[i]=nums[0]+...+nums[i] P 是前缀和数组 , 每个连续子数组和 sum(i,j)可以写成 P[j]-P[i-1]
     *
     * 1.其中(P[i] - P[j]) % k == 0条件满足则找到符合条件的子数组，根据同余定理，条件就可以转换为找到数组中任意两个除k同余的数
     * ，同余定理 ： 即找到 P[i] % k == P[j] % k
     * 上述需要间隔1个元素  i-j>1
     * 2.直接 P[i]%k==0  i>0  也是符合条件
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        // 余数，下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int leftSum = nums[i] % k;
            // 从1下标开始,和前面的和加起来是6的倍数
            if (leftSum == 0 && i > 0) {
                return true;
            }
            if (map.containsKey(leftSum)) {
                int index = map.get(leftSum);
                if (i - index >= 2) {
                    return true;
                }
            } else {
                map.put(leftSum, i);
            }
        }
        return false;
    }

    /**
     * 数组的至少两个元素的和  ==  k的倍数
     * 前缀和 转变为前缀和数组(nums[i]-num[j]) %k ==0
     */
    public boolean checkSubarraySum4(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        for (int i = 1; i < nums.length; i++) {
            // 从1下标开始,和前面的和加起来是6的倍数
            if (nums[i] % k == 0) {
                return true;
            }
            // 算间距两个之间的距离
            for (int j = 0; j < i - 1; j++) {
                if ((nums[i] - nums[j]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] presum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            //这里需要注意，我们的前缀和是presum[1]开始填充的
            presum[i + 1] = nums[i] + presum[i];
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] % k == 0&&i>0) {
                return true;
            }
            for (int j = i; j < nums.length; ++j) {
                //注意偏移，因为我们的nums[2]到nums[4]等于presum[5]-presum[2]
                //所以这样就可以得到nums[i,j]区间内的和
                if ((presum[j + 1] - presum[i])%k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P523连续的子数组和 fuc = new P523连续的子数组和();
        // 29到23之间存在6，间隔两个元素，说明这两个元素为6
        System.out.println(fuc.checkSubarraySum4(new int[]{0,0}, 1)); // 23,25,29,35,42
        System.out.println(fuc.checkSubarraySum(new int[]{23, 2, 4, 6, 6}, 7)); // 23,25,29,35,41
        System.out.println(fuc.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6)); // 23,25,29,35,42
        System.out.println(fuc.checkSubarraySum(new int[]{0}, 1)); // 23,25,29,35,42
        System.out.println(fuc.checkSubarraySum(new int[]{37, 5}, 6)); // 37,42
        System.out.println(fuc.checkSubarraySum(new int[]{1, 5}, 6)); // 1,6
        System.out.println(fuc.checkSubarraySum(new int[]{1, 6}, 6)); // 1,7  1,6之间增加了6不代表两个相加得6
        System.out.println(fuc.checkSubarraySum(new int[]{1, 2, 3}, 6)); // 1,3,6
//        System.out.println(fuc.checkSubarraySum2(new int[]{1, 2, 12}, 6)); // 1,3,6
        System.out.println(fuc.checkSubarraySum2(new int[]{1,1}, 2)); // 1,3,6

        System.out.println(0%1);
    }

}
