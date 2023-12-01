package A1000PLAN.哈希.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *desc:
 *@author lin
 *@since 2023/11/15
 **/
public class P290单词规律 {
    /*
    map+set
    a b c d  ali bog ci df 一一对应就是true ，一个字符对一个单词,注意反过来也要一样
    https://leetcode.cn/problems/word-pattern/solutions/523490/liang-chong-si-lu-9894-9853-by-zheng-ron-4x63/
     */
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        String[] str = s.split(" ");
        if (pattern.length() != str.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(str[i])) {
                    return false;
                }
            } else {
                // 注意这里 a->dog b->dog 的情况
                if (set.contains(str[i])) {
                    return false;
                }
                set.add(str[i]);
                map.put(c, str[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new P290单词规律().wordPattern("abba", "dog dog dog dog");
        new P290单词规律().wordPattern("abba", "dog cat cat dog");
    }
}
