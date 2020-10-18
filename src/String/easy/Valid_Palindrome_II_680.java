package String.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/10/17
  *@Describe: 最多删除一个字符，看是否还是回文
  1.贪心算法，遍历到不相等的字符，删除一个字符后还是回文，那整个就是回文，否则不是，判断两个区间
  2.先判断是否回文,暴力算法，若不是回文，遍历删除一个字符看是否是回文  O  n^2
 */

public class Valid_Palindrome_II_680 {

    /*
    贪心
     */
    public boolean validPalindrome(String s) {
        for (int i = 0; i < s.length()/2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j=s.length() - 1 - i;
                // 左右两个区间，分别对应删除左边字符还是右边字符
                return isPalindrome(s,i+1,j) || isPalindrome(s,i,j-1);
            }
        }
            return true;
    }
    public static boolean isPalindrome(String s,int i,int j) {
        for (int k = i; k <= i+(j-i)/2; k++) {
            if (s.charAt(k)!=s.charAt(j-k+i))
                return false;
        }
        return true;
    }
    /*
    暴力解法不推荐，不过回文的判断算法推荐
     */
    public boolean validPalindrome2(String s) {
        StringBuilder sb=new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            char c=sb.charAt(i);
            sb.deleteCharAt(i);
            if (isPalindrome2(sb)) return true;
            sb.insert(i,c);
        }
        return isPalindrome2(sb);
    }
    // CharSequence接收StringBuilder
    public static boolean isPalindrome2(CharSequence s) {
        for (int i = 0; i < s.length()/2; i++) {
            if (s.charAt(i)!=s.charAt(s.length()-1-i))
                return false;
        }
        return true;
    }
}

