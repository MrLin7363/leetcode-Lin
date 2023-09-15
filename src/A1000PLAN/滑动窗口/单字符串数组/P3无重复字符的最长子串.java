package A1000PLAN.滑动窗口.单字符串数组;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/14
 **/
public class P3无重复字符的最长子串 {
    /*
    滑动-正常模板
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int ans = 0;
        while (right < n) {
            char c = s.charAt(right);
            // 不符合收缩窗口
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            // 符合条件记录
            set.add(c);
            ans = Math.max(ans, set.size());
            // 右移右指针
            right++;
        }
        return ans;
    }
    /*
    模板就是这个
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int left = 0;
        int right = 0;
        // 记录每个字符的最大下标，遇到重复的，直接跳到这下标
        Map<Character, Integer> window = new HashMap<>();
        int ans = 0;
        while (right < n) {
            char rightChar = s.charAt(right);
            // 如果长度大于限定值,缩短左边
            if (window.containsKey(rightChar)) {
                // 注意需要跳过连续重复的
                left = Math.max(window.get(rightChar), left);
            }
            ans = Math.max(ans, right - left + 1);
            window.put(rightChar, right + 1);
            right++;
        }
        return ans;
    }
    public static void main(String[] args) {
        new P3无重复字符的最长子串().lengthOfLongestSubstring2("pwwpew");
    }
}
