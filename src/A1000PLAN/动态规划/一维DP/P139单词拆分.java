package A1000PLAN.动态规划.一维DP;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/18
 **/
public class P139单词拆分 {
    /*
    DP-推荐也行
    dp定义：dp[i]表示字符串0~i-1字符串能否被拆分
    转移方程：
    初始化： dp[0]=True，空字符可以被表示
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] == true && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    // 比如 leet 如果已经能被leet给拆分成一个就是true了， 不需要再判断le+et这种可能性，所以可以break
                    break;
                }
            }
        }
        return dp[n] == true;
    }

    /*
    DFS-推荐
     */
    public boolean wordBreakdfs(String s, List<String> wordDict) {
        int[] memo = new int[s.length()];
        return dfs(s, wordDict, memo, 0);
    }

    private boolean dfs(String s, List<String> wordDict, int[] memo, int start) {
        if (start >= s.length()) {
            return true;
        }
        // 剪枝 防止重复计算
        if (memo[start] == 1) {
            return true;
        }
        if (memo[start] == -1) {
            return false;
        }
        for (String word : wordDict) {
            if (s.startsWith(word, start) && dfs(s, wordDict, memo, start + word.length())) {
                memo[start] = 1;
                return true;
            }
        }
        // 未优化版本：字符串逐个匹配还要判断是否字典，上面优化：直接for字典去判断
        // for (int end = start + 1; end <= s.length(); end++) {
        //     // 如n=10 [6,10]区间计算完后==-1 ， 现在start=5,end=10,那么递归还要计算[5,9][5,10]...
        //     if (wordDict.contains(s.substring(start, end)) && dfs(s, wordDict, memo, end)) {
        //         memo[start] = 1;
        //         return true;
        //     }
        // }
        memo[start] = -1;
        return false;
    }

    /*
    BFS-不如DFS
    队列用下标维护；
     */
    public boolean wordBreakbfs(String s, List<String> wordDict) {
        boolean[] visited = new boolean[s.length()];
        Set<String> set = new HashSet<>(wordDict);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer start = queue.poll();
            if (visited[start]) { // 是访问过的，跳过
                continue;
            }
            visited[start] = true;
            for (int end = start + 1; end <= s.length(); end++) { // 用指针end去划分两部分
                String str = s.substring(start, end);
                if (set.contains(str)) {
                    if (end < s.length()) { // i还没越界，还能继续划分，让它入列，作为下一层待考察的节点
                        queue.offer(end);
                    }
                    if (end == s.length()) { // i==len，指针越界，说明s串一路被切出单词，现在没有剩余子串，不用划分，返回true
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new P139单词拆分().wordBreakbfs("leetcode", Arrays.asList("leet", "code")));
        System.out
            .println(new P139单词拆分().wordBreakdfs("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(new P139单词拆分().wordBreakdfs("aaaaab", Arrays.asList("a", "aa", "aaa", "aaaa")));
        System.out.println(new P139单词拆分().wordBreakdfs("leetcode", Arrays.asList("leet", "code")));
        System.out.println(new P139单词拆分().wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(new P139单词拆分().wordBreak("leetcode", Arrays.asList("leet", "code")));
    }
}
