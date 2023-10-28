package A1000PLAN.哈希;

import java.util.HashSet;
import java.util.Set;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/25
 **/
public class P128最长连续序列 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
        }
        int max = 0;
        for (int n : numSet) {
            // 找到每个连续序列的开始节点
            if (!numSet.contains(n - 1)) {
                int curLen = 0;
                int curNum = n;
                while (numSet.contains(curNum)) {
                    curLen++;
                    curNum += 1;
                }
                max = Math.max(curLen, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        new P128最长连续序列().longestConsecutive(new int[] {100, 4, 200, 1, 3, 2});
    }
}
