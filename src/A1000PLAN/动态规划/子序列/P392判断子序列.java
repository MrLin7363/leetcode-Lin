package A1000PLAN.动态规划.子序列;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/4
 **/
public class P392判断子序列 {
    /*
    https://leetcode.cn/problems/is-subsequence/solutions/1361126/by-nehzil-ixw6/
    状态定义： 长度为 i (0~i)，末尾项为 s[i−1] 的子字符串，与长度为 j，末尾项为 t[j−1] 的子字符串，二者的相同子序列长度。
    状态转移：判断末尾项，如果同 dp[i][j]=dp[i-1][j-1]+1  ; 不同： dp[i][j]=dp[i][j-1]
    初始化/边界条件: dp=0  if i==0 || j==0
    返回值：dp[i][j]的最大值是否和s的大小一样长
     */
    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 由于-1所以dp数组设置为0~i-1的范围
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 末尾项不一样，相当于s当前字符与t的下一个字符比较，t的当前元素t[j-1]删除
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[n][m] == s.length();
    }

    // 双指针
    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    // 自己写的遍历
    public boolean isSubsequence3(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        char[] ss = s.toCharArray();
        int i = 0;
        for (char c : t.toCharArray()) {
            if (c == ss[i]) {
                i++;
            }
            if (i == s.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new P392判断子序列().isSubsequence("abc", "ahbgdc"));
        System.out.println(new P392判断子序列().isSubsequence("axc", "ahbgdc"));
    }
}
