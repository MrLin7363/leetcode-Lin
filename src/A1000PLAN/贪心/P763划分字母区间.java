package A1000PLAN.贪心;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/27
 **/
public class P763划分字母区间 {
    /*
    和下面一样思路，不过通过26数组记录了字母最后的下标 On
     */
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] last = new int[26];
        // 字母最后出现的下标
        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }

    /*
    贪心双指针 <On^2 自己写的
    遇到相同的直接归并为一个数组
    然后跳到数组末尾继续遍历
    如果新的数组有交叉就合并数组
     */
    public List<Integer> partitionLabels2(String s) {
        List<int[]> path = new ArrayList<>();
        int n = s.length();
        int left = 0;
        int right = n - 1;
        while (left < n) {
            // 这里可以优化 加上 right> path.get(path.size() - 1)[1]
            while (left < right && s.charAt(left) != s.charAt(right)) {
                right--;
            }
            // 判断是否交叉，交叉则合并
            if (!path.isEmpty() && path.get(path.size() - 1)[1] >= left) {
                if (path.get(path.size() - 1)[1] < right) {
                    path.get(path.size() - 1)[1] = right;
                }
            } else {
                path.add(new int[] {left, right});
            }
            left++;
            right = n - 1;
        }

        List<Integer> res = new ArrayList<>();
        for (int[] each : path) {
            res.add(each[1] - each[0] + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        //  "ababcbaca"、"defegde"、"hijhklij"  9 7 8
        new P763划分字母区间().partitionLabels("ababcbacadefegdehijhklij");
    }
}
