package Array.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: chenjunlin
 * @since: 2021/08/23
 * @descripe: 最长连续子序列，每一个元素能够递增起来
 */
public class P128最长连续序列 {

    /*
    DP 如果遇到连续的一次性记录完，最大值；下次遇到连续的不用再记录
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int maxLength = 0;
        for (int num : numSet) {
            // 如果是连续序列已经计算过，跳过
            if (!numSet.contains(num - 1)) {
                int curLength = 1;
                int curNum=num;
                // 一次就计算 连续序列的最大个数
                while (numSet.contains(curNum + 1)) {
                    curNum++;
                    curLength++;
                }
                maxLength = Math.max(curLength, maxLength);
            }
        }
        return maxLength;
    }


}
