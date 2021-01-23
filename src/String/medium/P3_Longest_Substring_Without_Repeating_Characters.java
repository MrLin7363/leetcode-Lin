package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/23
  *@Describe:
 */

import java.util.HashSet;
import java.util.Set;

public class P3_Longest_Substring_Without_Repeating_Characters {
    /*
    滑动窗口
     */
    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> hashSet=new HashSet<>();
        int n=s.length();
        // 右指针，相当于我们在字符串的左边界，还没有开始移动
        int right=0;
        int ans=0;
        for (int i = 0; i < n; i++) {
            if (i!=0){
                // 左指针i 向右移动一格，移除一个字符
                hashSet.remove(s.charAt(i-1));
            }
            while (right<n && !hashSet.contains(s.charAt(right))){
                hashSet.add(s.charAt(right));
                // 不断地移动右指针
                right++;
            }
            ans=Math.max(ans,right-i);
        }
        return ans;
    }

    public static void main(String[] args) {
        lengthOfLongestSubstring("abcabcbb");
    }
}
