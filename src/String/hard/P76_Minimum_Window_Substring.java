package String.hard;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/17
  *@Describe: s中包含t
 */

import java.util.HashMap;
import java.util.Map;

public class P76_Minimum_Window_Substring {
    /*
    hashMap+滑动窗口  5+65
     */
    Map<Character, Integer> original = new HashMap<>();// 要匹配的字符串各字符的个数
    Map<Character, Integer> current = new HashMap<>();// 目前窗口有的各字符的个数
    public String minWindow(String s, String t) {
        int tLen=t.length();
        int sLen=s.length();
        // 初始化要匹配的字符
        for (int i = 0; i < tLen; i++) {
            original.put(t.charAt(i),original.getOrDefault(t.charAt(i),0)+1);
        }
        int l=0,r=-1; // 实时滑动的左右边界
        int ansL=-1,ansR=-1; // 最终区间的左右边界
        int minLen=Integer.MAX_VALUE;
        while (r<sLen){
            r++;
            // 添加目前的字符
            if (r<sLen && original.containsKey(s.charAt(r))){
                current.put(s.charAt(r),current.getOrDefault(s.charAt(r),0)+1);
            }
            // 如果包含了目标所有字符，左边界右移
            while (check()&&l<=r){
                // 如果当前区间比之前的区间小 [0,0] len=1
                if (r-l+1<minLen){
                    minLen=r-l+1;
                    ansL=l;
                    ansR=l+minLen; // substring end index不截取，所以这里这样写，也可以写 r+1
                }
                // 左边界右移，包含字符减少
                if (original.containsKey(s.charAt(l))){
                    current.put(s.charAt(l),current.getOrDefault(s.charAt(l),0)-1);
                }
                l++;
            }
        }
        return ansL==-1? "":s.substring(ansL,ansR);
    }
    // 判断当前区间是否包含足够的目标字符 O n
    public boolean check() {
        for (Map.Entry entry:original.entrySet()){
            Character key=(Character)entry.getKey();
            Integer val=(Integer)entry.getValue();
            if (current.getOrDefault(key,0)<val){
                return false;
            }
        }
        return true;
    }
}
