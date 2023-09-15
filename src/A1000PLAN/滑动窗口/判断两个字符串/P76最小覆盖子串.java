package A1000PLAN.滑动窗口.判断两个字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/14
 **/
public class P76最小覆盖子串 {
    /*
    判断s包含t的最小子串
    滑动-map模板,map适合含有非字母的
     */
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n < m) {
            return "";
        }
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>(); // 当前加入的符合条件的元素
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        // String res = ""; // 这里直接记录string,可以换成 start=0,len=Integet.MAX的方式记录
        int start = 0;
        int len = Integer.MAX_VALUE;
        int valid = 0;
        while (right < n) {
            char c = s.charAt(right);
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }

            // 注意：这里不判断长度，是因为这里的子串可以比t串大，只要包含t就行，而438，567两题是找长度一样的串
            while (valid == need.size()) {
                if (right - left + 1 < len) {
                    len = right - left + 1;
                    start = left;
                }
                // if (res.equals("")){
                //     res = s.substring(left, right + 1);
                // }
                // if (res.length() > right - left + 1) {
                //     res = s.substring(left, right + 1);
                // }
                char leftChar = s.charAt(left);
                left++;
                if (need.containsKey(leftChar)) {
                    if (need.get(leftChar).equals(window.get(leftChar))) {
                        valid--;
                    }
                    window.put(leftChar, window.get(leftChar) - 1);
                }
            }

            right++;
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        System.out.println(new P76最小覆盖子串().minWindow("ADOBECODEBANC", "ABC"));
    }
}
