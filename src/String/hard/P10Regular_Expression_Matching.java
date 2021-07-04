package String.hard;

/**
 * @author: Junlin Chen
 * @Date: 2021/06/27 23:00
 * @Describe: 递归 + DP
 */
public class P10Regular_Expression_Matching {
    /*
    DP  注意 dp[0][0] 是一个空字符串,但是 p.chartAt(0) 是第一个字符    数组 默认为 false
   100+50
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 初始化第一行，第一列默认都为false,空字符串匹配不了任意
        for (int col = 1; col < dp[0].length; col++) {
            char p1 = p.charAt(col-1);
            if (col>1){
                if (p1=='*'){
                    dp[0][col]=dp[0][col-2]; // * 前面字符使用0次
                }else{
                    dp[0][col]=false;
                }
            }else{
                if (p1=='*'){
                    dp[0][col]=true;
                }
            }
        }
        for (int row = 1; row < dp.length; row++) {
            char s1 = s.charAt(row - 1);
            for (int col = 1; col < dp[row].length; col++) {
                char p1 = p.charAt(col - 1);
                if (s1 == p1 || p1 == '.') {
                    dp[row][col] = dp[row - 1][col - 1];
                } else if (p1 == '*') {
                    if (col > 1) {
                        if (dp[row][col - 2] == true) {
                            dp[row][col] = true; // *前面的字符出现0次
                        }else{
                            if (p.charAt(col-2)==s1 || p.charAt(col-2)=='.'){ // *前面字符和s字符一致或者 为.
                                dp[row][col] = dp[row - 1][col];  // *前面的字符出现1次或多次
                            }
                        }
                    } else {
                        dp[row][col] = false; // 第一个 * 都不能匹配，这里else可以省略
                    }
                }
            }
        }
        return dp[m][n];
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
