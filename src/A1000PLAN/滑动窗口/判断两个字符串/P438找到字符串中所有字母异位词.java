package A1000PLAN.滑动窗口.判断两个字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/13
 **/
public class P438找到字符串中所有字母异位词 {
    /*
    在s中维护与t长度一样的窗口，如果里面字母个数一样，就是一个组合
    特殊情况，s长度小于t，无法构成字母异位词
    哈希map模板
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> curMap = new HashMap<>(); // 当前加入的符合条件的元素
        for (char ps : p.toCharArray()) {
            need.put(ps, need.getOrDefault(ps, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (need.containsKey(c)) {
                curMap.put(c, curMap.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(curMap.get(c))) {
                    // 一个字母满足
                    valid++;
                }
            }

            // 收缩窗口 ==也是可以的 这个也可以 if (right - left + 1 == s1.length()) {
            while (right - left + 1 >= p.length()) {
                // 符合条件:有足够的合法字母了
                if (valid == need.size()) {
                    res.add(left);
                }
                char leftChar = s.charAt(left);
                left++;
                if (need.containsKey(leftChar)) {
                    // 注意这里不能==，因为 Integer>256 是对象
                    if (need.get(leftChar).equals(curMap.get(leftChar))) {
                        valid--;
                    }
                    curMap.put(leftChar, curMap.getOrDefault(leftChar, 0) - 1);
                }
            }
            right++;
        }
        return res;
    }

    /*
    哈希数组模板 - 正常模板
     */
    public List<Integer> findAnagrams2(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }
        int[] cnt = new int[26];
        for (int i = 0; i < p.length(); ++i) {
            --cnt[p.charAt(i) - 'a'];
        }
        int left = 0;
        List<Integer> res = new ArrayList<>();
        int right = 0;
        while (right<s.length()){
            int x = s.charAt(right) - 'a';
            ++cnt[x];
            // 不符合条件
            while (cnt[x] > 0) {
                --cnt[s.charAt(left) - 'a'];
                ++left;
            }
            // 条件符合
            if (right - left + 1 == p.length()) {
                res.add(left);
            }
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        Integer integer = new Integer(25555);
        Integer integer1 = new Integer(25555);
        System.out.println(integer == integer1); // false
        new P438找到字符串中所有字母异位词().findAnagrams("abcdafds", "abc");
    }
}
