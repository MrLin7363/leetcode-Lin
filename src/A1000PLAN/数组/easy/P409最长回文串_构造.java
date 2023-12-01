package A1000PLAN.数组.easy;

/**
 *desc: 这题不是求字符串里的最长回文串，而是能用这些字符构造的最长回文串长度
 *@author lin
 *@since 2023/11/21
 **/
public class P409最长回文串_构造 {
    /**
     * 贪心:尽可能的多偶数次数的字符添加构造回文串
     * 1.所有字符都出现偶数次数的回文串 2.一个字符出现1次，其他都出现偶数次
     */
    public int longestPalindrome(String s) {
        int[] counter = new int[128];
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            counter[charArray[i]]++;
        }
        int res = 0;
        boolean hasOdd = false; // 是否有出现奇数次数的字符
        for (int v : counter) {
            // 所有字符都拿来构造
            res += v;
            // 如果是奇数次数，则-1达到偶数次数的字符 如ccc  cc是偶数
            if (v % 2 == 1) {
                res--;
                hasOdd = true;
            }
        }
        if (hasOdd) {
            res += 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new P409最长回文串_构造().longestPalindrome("ccc"));
        System.out.println(new P409最长回文串_构造().longestPalindrome("bcvbAZ"));
    }
}
