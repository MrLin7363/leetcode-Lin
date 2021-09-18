package Array.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Junlin Chen
 * @Date: 2021/09/15 14:15
 * @Describe:
 */
public class P139_Word_Break {

    /*
     DP  65 + 62
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println( wordBreak("leetleetcodeleet", Arrays.asList("leet","code")));
        System.out.println( wordBreak("leetzleetcodeleet", Arrays.asList("leet","code")));
    }

}
