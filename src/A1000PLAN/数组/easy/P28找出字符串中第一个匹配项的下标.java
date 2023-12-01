package A1000PLAN.数组.easy;

/**
 *desc:     kmp实现的，但是不去看
 *@author lin
 *@since 2023/11/21
 **/
public class P28找出字符串中第一个匹配项的下标 {
    /*
    hayStack 与needle长度一致的字符串比较
    还有一种KMP算法太难不看
     */
    public int strStr(String haystack, String needle) {
        int len = needle.length();
        for (int i = 0; i < haystack.length(); i++) {
            if (i + len > haystack.length()) {
                return -1;
            }
            String s = haystack.substring(i, i + len);
            if (s.equals(needle)) {
                return i;
            }
        }
        return -1;
        // return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        System.out.println(new P28找出字符串中第一个匹配项的下标().strStr("a", "a"));
        System.out.println(new P28找出字符串中第一个匹配项的下标().strStr("leetcode", "eet"));
    }
}
