package A1000PLAN.位运算.mid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/8
 **/
public class P318最大单词长度乘积 {
    /*
    位运算-推荐 O^2 单词和剩下单词比较，每次 比较两个字符串是否又重复的 采用位运算 O1 复杂度
     */
    public int maxProduct(String[] words) {
        // 填充位运算，每个元素是个二进制
        int[] mask = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                mask[i] |= 1 << word.charAt(j) - 'a';
            }
        }
        int ans = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                // 没有重复的
                if ((mask[i] & mask[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }

    /*
    暴力 >On^2 + 剪枝   接近O^3  单词和剩下单词比较，每次比较又需要遍历一次单词
     */
    public int maxProduct2(String[] words) {
        List<String> sortedWords = Arrays.stream(words).sorted((o1, o2) -> o2.length() - o1.length())
            .collect(Collectors.toList());
        Set<Character> set = new HashSet<>();
        int ans = 0;
        for (int i = 0; i < sortedWords.size() - 1; i++) {
            char[] chars = sortedWords.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                set.add(chars[j]);
            }

            // 剪枝:最大长度都比ans小就不用比较内部了,及后面的了
            if (i + 1 < sortedWords.size() && sortedWords.get(i).length() * sortedWords.get(i + 1).length() < ans) {
                break;
            }

            // 与后面的比较
            for (int j = i + 1; j < sortedWords.size(); j++) {
                boolean repeat = false;
                char[] chars2 = sortedWords.get(j).toCharArray();

                // 剪枝:最大长度都比ans小就不用比较内部了,及后面的了
                if (chars.length * chars2.length < ans) {
                    break;
                }

                for (int k = 0; k < chars2.length; k++) {
                    if (set.contains(chars2[k])) {
                        repeat = true;
                        break;
                    }
                }

                if (!repeat) {
                    ans = Math.max(ans, chars.length * chars2.length);
                }
            }
            set.clear();
        }
        return ans;
    }

    public static void main(String[] args) {
        new P318最大单词长度乘积().maxProduct(new String[] {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"});
    }
}
