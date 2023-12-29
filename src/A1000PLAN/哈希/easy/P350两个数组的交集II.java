package A1000PLAN.哈希.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/8
 **/
public class P350两个数组的交集II {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int[] res = new int[nums1.length];
        int index = 0;
        for (int i : nums2) {
            int count = map.getOrDefault(i, 0);
            if (count > 0) {
                count--;
                res[index++] = i;
                if (count > 0) {
                    map.put(i, count);
                } else {
                    map.remove(i);
                }
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }
}
