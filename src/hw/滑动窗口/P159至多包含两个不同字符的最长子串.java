 

package hw.滑动窗口;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/7
 **/
public class P159至多包含两个不同字符的最长子串 {

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        int left = 0;
        int right = 0;
        Map<Character,Integer> map = new HashMap<>();
        int ans=0;
        while (right < n) {
            // eceba
            char rightChar = s.charAt(right);
            map.put(rightChar,map.getOrDefault(rightChar,0)+1);
            // 不符合条件，左指针收缩，超过两个不同的字符
            while (map.keySet().size() > 2 ) {
                char leftChar = s.charAt(left);
                map.put(leftChar,map.get(leftChar)-1);
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

    public static void main(String[] args) {
        lengthOfLongestSubstringTwoDistinct(new String("ccaabbb"));
    }

}
