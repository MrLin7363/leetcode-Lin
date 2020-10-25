package String.medium;/*

/**
  *@Author JunLin
  *@Date 2020/10/25
  *@Describe: 字母异位词， ["eat","tea","tan","ate","nat","bat"] -> [["bat"],["nat","tan"],["ate","eat","tea"]]
  1. hashTable 存储已经排好序的字符串  , O NKlogK
  return new ArrayList(ans.values()); 和  return new ArrayList<>(ans.values()); 不一样
  2. hashTable 存储每位字符出现次数，类似位图  O  NK
  abbccc will be #1#2#3#0#0#0...#0    a出现1次，b出现2次，c3次，其余0次
 */

import java.util.*;

public class Group_Anagrams_49 {
    public List<List<String>> groupAnagramsBySortedString(String[] strs) {
        if (strs.length==0) return new ArrayList();
        Map<String,List> ans=new HashMap<>();
        for (String s:strs){
            char[] ca=s.toCharArray();
            Arrays.sort(ca); // nlogn
            String key=String.valueOf(ca);
            if (!ans.containsKey(key))
                ans.put(key,new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
    public List<List<String>> groupAnagramsByCount(String[] strs) {
        if (strs.length==0) return new ArrayList();
        Map<String,List> ans=new HashMap<>();
        int[] count= new int[26];
        for (String s:strs){
            Arrays.fill(count,0);
            // 计算当前字符串的个数
            for (char c:s.toCharArray())
                count[c-'a']++;
            // 制作key
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < count.length; i++) {
                builder.append("#"+count[i]);
            }
            String key=builder.toString();
            if (!ans.containsKey(key))
                ans.put(key,new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }


}
