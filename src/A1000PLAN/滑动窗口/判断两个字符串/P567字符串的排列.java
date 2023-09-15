package A1000PLAN.滑动窗口.判断两个字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/13
 **/
public class P567字符串的排列 {
    /*
    判断 s2是否包含s1排列
    哈希map模板
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> curMap = new HashMap<>(); // 当前加入的符合条件的元素
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            // 更新条件
            if (need.containsKey(c)) {
                curMap.put(c, curMap.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(curMap.get(c))) {
                    valid++;
                }
            }
            // 收缩窗口  if (right - left + 1 == s1.length())  也行
            while (right - left + 1 >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char leftChar = s2.charAt(left);
                left++;
                if (need.containsKey(leftChar)) {
                    if (need.get(leftChar).equals(curMap.get(leftChar))) {
                        valid--;
                    }
                    curMap.put(leftChar, curMap.get(leftChar) - 1);
                }
            }
            right++;
        }
        return false;
    }

    // 正常模板
    public boolean checkInclusion2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        int right = 0;
        while (right < m) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            // 不符合条件，左指针右移
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
            right++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new P567字符串的排列().checkInclusion("aaa", "a"));
        System.out.println(new P567字符串的排列().checkInclusion2("aaa", "a"));
    }
}
