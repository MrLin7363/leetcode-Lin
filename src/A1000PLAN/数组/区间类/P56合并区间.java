package A1000PLAN.数组.区间类;

import java.util.Arrays;
import java.util.Comparator;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/25
 **/
public class P56合并区间 {
    /*
    5+ 5
    1.排序按左端点
    2.遍历区间，如果不重合，添加进结果
    如果重合，上一个结果右边界限右移
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[][] res = new int[intervals.length][intervals[0].length];
        int index = -1;
        for (int i = 0; i < intervals.length; i++) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (index == -1 || intervals[i][0] > res[index][1]) {
                res[++index] = intervals[i];
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[index][1] = Math.max(res[index][1], intervals[i][1]);
            }
        }
        return Arrays.copyOf(res, index + 1); // 把多余的数组元素去掉，  [0,0]
    }

    public static void main(String[] args) {
        int[][] res = new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        new P56合并区间().merge(res);
    }
}
