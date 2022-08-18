 

package hw.滑动窗口;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/2
 **/
public class P1208尽可能使字符串相等 {

    /*
    满足条件-右指针右移扩展   90+28
    不满足-左指针收缩
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] diff = new int[n];
        int right = 0;
        int left = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int sum = 0;
        while (right < n) {
            sum += diff[right];
            // 不符合条件
            while (sum > maxCost) {
                sum-=diff[left];
                left++;
            }
            ans = Math.max(ans, right - left+1);
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        result.add("Asdas");
        result.add("12e");
        result.add("/asdasdqw");
        System.out.println(result.toArray(new String[0]));
    }

}
