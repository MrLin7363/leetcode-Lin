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
public class P205同构字符串 {
    /*
    参考P290  map+set
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(t.charAt(i))) {
                    return false;
                }
            } else {
                if (set.contains(t.charAt(i))) {
                    return false;
                }
                set.add(t.charAt(i));
                map.put(c, t.charAt(i));
            }
        }
        return true;
    }
}
