package String.hard;

/**
 * @author: Junlin Chen
 * @Date: 2021/06/27 23:00
 * @Describe: 递归 + DP
 */
public class P10Regular_Expression_Matching {
    /*
    DP
     */
    public boolean isMatch(String s, String p) {
        return false;
    }

    /*
    递归 18+16
     */
    public boolean isMatchRC(String s, String p) {
        //  如果模式串匹配完了，s也是空的，则可以匹配
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match = (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
        // 模式串有两位且有*的情况
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // 1. 假设第一个不匹配,直接用完两个字符  如 .*，
            // 2. 假设第一个字符匹配，模式串不变，可以继续用
            return (isMatchRC(s, p.substring(2))) || (first_match && isMatchRC(s.substring(1), p));
        } else { // 模式串不是*的情况
            // 第一个匹配
            return first_match && isMatchRC(s.substring(1), p.substring(1));
        }
    }

}
