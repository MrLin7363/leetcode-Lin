package hw.滑动窗口;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/11/29
 **/
public class P3无重复字符的最长子串 {

    /*
    模板就是这个
     */
    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        while (right < n) {
            char rightChar = s.charAt(right);
            // 如果长度大于限定值,缩短左边
            if (map.containsKey(rightChar)) {
                // 注意需要跳过连续重复的
                left = Math.max(map.get(rightChar), left);
            }
            ans = Math.max(ans, right - left + 1);
            map.put(rightChar, right + 1);
            right++;
        }
        return ans;
    }

    /*
    子串问题用双指针滑动窗口   72+55
    求最大，符合条件右节点右移,对于左端点，求符合条件最大的右端点
    不符合条件左指针收缩，要set有重复的判断不出来，所以这道题目只能遍历left节点，求最大右节点，而不能同时收缩
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0;
        int right = 0;
        int ans = 0;
        Set<Character> set = new HashSet<>();
        // while条件是左节点就行，因为每次循环左节点都会加+，但是加上right<n会更快
        while (left < n) {
            // 左节点右移，set移除左节点，相当于for循环
            if (left != 0) {
                set.remove(s.charAt(left - 1));
            }
            // while 不包含 右指针右移，算最大的ans
            // 这里符合条件右移,因为以左节点作为判断依据
            while (right < n && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right ));
                right++;
            }
            ans = Math.max(ans, right - left );
            left++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring3("pwwkew"));
    }
}
