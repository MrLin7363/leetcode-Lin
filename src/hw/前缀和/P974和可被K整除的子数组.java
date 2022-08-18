 

package hw.前缀和;

import java.util.HashMap;
import java.util.Map;

/**
 * desc: 连续的
 *
 * @author junlin
 * @since 2022/1/30
 **/
public class P974和可被K整除的子数组 {

    /**
     * A = [4,5,0,-2,-3,1], K = 5
     * 输出：7
     * 4,9,9,7,4,5
     * P[i] % k == P[j] % k
     */
    public int subarraysDivByK(int[] nums, int k) {
        // key:余数  value:符合条件的子数组个数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int ans = 0;
        for (int num : nums) {
            sum += num; // 遍历的时候顺便同步和
//            int leftSum2 = Math.abs(sum) % k;
//            int leftSum3 = sum % k;
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正，以上两种都错误
            int leftSum =  (sum % k + k) % k;
            int count = map.getOrDefault(leftSum, 0);
            ans += count;
            map.put(leftSum, count + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = -5;
        System.out.println(i / 5);
        P974和可被K整除的子数组 bgin = new P974和可被K整除的子数组();
        // 0也能被6整除
        System.out.println(bgin.subarraysDivByK(new int[]{2, -2, 2, -4}, 6));
        System.out.println(bgin.subarraysDivByK(new int[]{-5}, 5));
        System.out.println(bgin.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
    }

}
