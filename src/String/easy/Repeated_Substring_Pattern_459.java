package String.easy;

import java.util.Arrays;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/14 19:12
 * @Describe:
 * 1、根据倍数和前缀规则(相同长度位置字符相等)判断, s[i]=s[i-n']
 * 2、字符串匹配两个相加
 * 3、KMP算法,判断 kmp(s+s,s)
 */
public class Repeated_Substring_Pattern_459 {
    public boolean repeatedSubstringPattern(String s) {
        int n=s.length();
        // [1,n/2] 范围内枚举就可以
        for (int i = 1; i * 2 <= n; i++) {
            // 如果是倍数
            if ( n % i == 0 ){
                boolean match=true;
                // s[i]=s[i-n']
                for (int j=i;j<n;j++){
                    if (s.charAt(j)!=s.charAt(j-i)){
                        match=false;
                        break;
                    }
                }
                if (match)
                    return true;
            }
        }
        return false;
    }
    /*
    2
     */
    public static boolean repeatedSubstringPattern2(String s) {
        System.out.println((s + s).indexOf(s, 1));
        return (s + s).indexOf(s, 1) != s.length();
    }
    /*
    3 KMP -
     */
    public static boolean repeatedSubstringPattern3(String s) {
        int[] next=getNexts(s.toCharArray(),s.length());
        int len=s.length();
        // next[len-1]!=-1    ->  最长相等前后缀存在  -> 有重复多次的字符串
        // len%(len-(next[len-1]+1))==0   ->   (数组长度-最长相等前后缀的长度) 正好可以被数组的长度整除，说明有该字符串有重复的子字符串。
        return next[len-1]!=-1 && len%(len-(next[len-1]+1))==0;
    }
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0]=-1;
        int j=-1;
        for (int i = 1; i < m; i++) {
            // 不匹配
            while (j!=-1 && b[j+1]!=b[i]){
                j=next[j]; // 移动j下标
            }
            // 匹配
            if (b[j+1]==b[i])
                j++; // 移动j+1
            // 最长公共前后缀结尾下标
            next[i]=j;
        }
        return next;
    }


    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern3("aac"));
    }
}
