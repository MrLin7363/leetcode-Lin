package A1000PLAN.深广遍历.图.bfs.双向BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 *desc: P433最小基因变化 的进阶版本
 *      1. 单向BFS:可以穷举26个字母 和P433最小基因变化一样
 *      2. 双向BFS: https://leetcode.cn/problems/word-ladder/solutions/102383/suan-fa-shi-xian-he-you-hua-javashuang-xiang-bfs23/?envType=study-plan-v2&envId=top-interview-150
 *@author lin
 *@since 2023/11/9
 **/
public class P127单词接龙_hard {
    /*
    双向BFS:没优化成 -> 优先从队列少的优先遍历
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue1 = new ArrayDeque<>();
        Queue<String> queue2 = new ArrayDeque<>();
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        queue1.add(beginWord);
        visited1.add(beginWord);
        queue2.add(endWord);
        visited2.add(endWord);
        int step1 = 1;
        int step2 = 1;

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int size1 = queue1.size();
            for (int i = 0; i < size1; i++) {
                String str = queue1.poll();
                for (int j = 0; j < str.length(); j++) {
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (str.charAt(j) != k) {
                            // 可以替换
                            StringBuilder sb = new StringBuilder(str);
                            sb.setCharAt(j, k);
                            String next = sb.toString();
                            if (!wordSet.contains(next)) {
                                continue;
                            }
                            if (visited2.contains(next)) {
                                return step1 + step2;
                            }
                            if (!visited1.contains(next)) {
                                queue1.add(next);
                                visited1.add(next);
                            }
                        }
                    }
                }
            }
            step1++;

            int size2 = queue2.size();
            for (int i = 0; i < size2; i++) {
                String str = queue2.poll();
                // 注意：双向队列，由于endWord不能不存在，所以需要判断
                if (!wordSet.contains(str)) {
                    continue;
                }
                for (int j = 0; j < str.length(); j++) {
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (str.charAt(j) != k) {
                            // 可以替换
                            StringBuilder sb = new StringBuilder(str);
                            sb.setCharAt(j, k);
                            String next = sb.toString();
                            if (!wordSet.contains(next)) {
                                continue;
                            }
                            if (visited1.contains(next)) {
                                return step1 + step2;
                            }
                            if (!visited2.contains(next)) {
                                queue2.add(next);
                                visited2.add(next);
                            }
                        }
                    }
                }
            }
            step2++;
        }
        return 0;
    }

    /*
    双向BFS:-优化版：优先从队列少的优先遍历
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue1 = new ArrayDeque<>();
        Queue<String> queue2 = new ArrayDeque<>();
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        queue1.add(beginWord);
        visited1.add(beginWord);
        queue2.add(endWord);
        visited2.add(endWord);
        int step1 = 1;
        int step2 = 1;

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int size1 = queue1.size();
            int size2 = queue2.size();
            // 优先处理队列少的
            if (size1 <= size2) {
                for (int i = 0; i < size1; i++) {
                    String str = queue1.poll();
                    for (int j = 0; j < str.length(); j++) {
                        for (char k = 'a'; k <= 'z'; k++) {
                            if (str.charAt(j) != k) {
                                // 可以替换
                                StringBuilder sb = new StringBuilder(str);
                                sb.setCharAt(j, k);
                                String next = sb.toString();
                                if (!wordSet.contains(next)) {
                                    continue;
                                }
                                if (visited2.contains(next)) {
                                    return step1 + step2;
                                }
                                if (!visited1.contains(next)) {
                                    queue1.add(next);
                                    visited1.add(next);
                                }
                            }
                        }
                    }
                }
                step1++;
            } else {
                for (int i = 0; i < size2; i++) {
                    String str = queue2.poll();
                    // 注意：双向队列，由于endWord不能不存在，所以需要判断
                    if (!wordSet.contains(str)) {
                        continue;
                    }
                    for (int j = 0; j < str.length(); j++) {
                        for (char k = 'a'; k <= 'z'; k++) {
                            if (str.charAt(j) != k) {
                                // 可以替换
                                StringBuilder sb = new StringBuilder(str);
                                sb.setCharAt(j, k);
                                String next = sb.toString();
                                if (!wordSet.contains(next)) {
                                    continue;
                                }
                                if (visited1.contains(next)) {
                                    return step1 + step2;
                                }
                                if (!visited2.contains(next)) {
                                    queue2.add(next);
                                    visited2.add(next);
                                }
                            }
                        }
                    }
                }
                step2++;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        new P127单词接龙_hard().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        new P127单词接龙_hard().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"));
    }
}
