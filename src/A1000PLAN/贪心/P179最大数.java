package A1000PLAN.贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/15
 **/
public class P179最大数 {
    /*
    根据「结果」来决定 a 和 b 的排序关系：
    如果拼接结果 ab 要比 ba 好，那么我们会认为 a 应该放在 b 前面。
    另外，注意我们需要处理前导零（最多保留一位）[0,0] -> 0
     */
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = "" + nums[i];
        }
        Arrays.sort(ss, new Comparator<String>() {
            @Override
            public int compare(String ne, String old) {
                String s1 = ne + old;
                String s2 = old + ne;
                return s2.compareTo(s1); // 降序
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s : ss) {
            sb.append(s);
        }

        // 处理前导0
        int len = sb.length();
        int k = 0;
        while (k < len - 1 && sb.charAt(k) == '0') {
            k++;
        }
        return sb.substring(k);
    }
}
