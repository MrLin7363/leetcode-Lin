package String.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/14 19:12
 * @Describe:
 * 1、根据倍数和前缀规则(相同长度位置字符相等)判断, s[i]=s[i-n']
 * 2、字符串匹配两个相加
 * 3、
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
    public static boolean repeatedSubstringPattern2(String s) {
        System.out.println((s + s).indexOf(s, 1));
        return (s + s).indexOf(s, 1) != s.length();
    }
    /*
    3
     */
    public static boolean repeatedSubstringPattern3(String s) {
        System.out.println((s + s).indexOf(s, 1));
        return (s + s).indexOf(s, 1) != s.length();
    }
    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern2("aacaac"));
    }
}
