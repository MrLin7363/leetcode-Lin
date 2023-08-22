package A1000PLAN.贪心;

import java.util.Arrays;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/11
 **/
public class P455分发饼干 {
    /*
    贪心
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = s.length - 1;
        int count = 0;
        for (int k = g.length - 1; k >= 0; k--) {
            if (i < 0) {
                return count;
            }
            if (s[i] >= g[k]) {
                i--;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        new P455分发饼干().findContentChildren(new int[] {1, 2, 3}, new int[] {1, 1,});
    }
}
