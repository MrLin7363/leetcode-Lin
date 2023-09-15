package A1000PLAN.滑动窗口.单字符串数组;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/15
 **/
public class P904水果成篮 {
    /*
    题目转化：找连续子数组，这个数组最多含有两个不同的nums[i]，返回最大长度
     */
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int left = 0;
        int right = 0;
        // fruits[i],出现次数count
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        while (right < n) {
            int cur = fruits[right];
            // 不符合条件
            while (map.get(cur) == null && map.size() == 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }

            // 符合条件增大窗口
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        new P904水果成篮().totalFruit(new int[] {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4});
        new P904水果成篮().totalFruit(new int[] {0, 1, 2, 2});
    }
}
