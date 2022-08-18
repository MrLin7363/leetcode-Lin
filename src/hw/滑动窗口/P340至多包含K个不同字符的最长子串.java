 

package hw.滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/8
 **/
public class P340至多包含K个不同字符的最长子串 {

    /*
    给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        int left = 0;
        int right = 0;
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < n) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);
            // 不符合条件,左指针收缩
            while (map.keySet().size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar)==0){
                    map.remove(leftChar);
                }
                left++;
            }
            ans=Math.max(ans,right-left+1);
            right++;
        }
        return ans;
    }

}
