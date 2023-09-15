package A1000PLAN.滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/14
 **/
public class P187重复的DNA序列 {
    /*
    找到字符串中10长度的相同的字符串
    滑动窗口+哈希表
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i + 10 <= n; i++) {
            String cur = s.substring(i, i + 10);
            int count = map.getOrDefault(cur, 0);
            if (count == 1) {
                ans.add(cur);
            }
            map.put(cur, count + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        new P187重复的DNA序列().findRepeatedDnaSequences("AAAAAAAAAA");
    }
}

