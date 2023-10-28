package A1000PLAN.哈希;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc:字母异位词， ["eat","tea","tan","ate","nat","bat"] -> [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 1. hashTable 存储已经排好序的字符串  , O NKlogK
 * return new ArrayList(ans.values()); 和  return new ArrayList<>(ans.values()); 不一样
 * 2. hashTable 存储每位字符出现次数，类似位图  O  NK
 * abbccc will be #1#2#3#0#0#0...#0    a出现1次，b出现2次，c3次，其余0次
 *
 * @author Lin
 * @since 2023/10/25
 **/
public class P49字母异位词分组 {
    /*
    2.hashTable 存储每位字符出现次数
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] count = new int[26];
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            Arrays.fill(count, 0);
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                count[chars[i] - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append("#" + count[i]);
            }
            if (!map.containsKey(sb.toString())) {
                map.put(sb.toString(), new ArrayList<>());
            }
            map.get(sb.toString()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        new P49字母异位词分组().groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
    }
}
