package A1000PLAN.贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/14
 **/
public class P435无重叠空间 {
    /*
    按左端点升序排序,然后记录去掉长的那个区间后的当前最右边界，再与下一个比较
    记录去掉的重叠区间
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            // 1. o1 新的，o2旧的， 结果负数加前面，正数+0 加后面  2. 新进来的会逐个和之前的比较再插入
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int overlapCount = 0; // 去掉的重叠区间个数
        int end = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            // 不重叠,更新最右
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
            } else {
                // 去掉右端点比较大的重叠区间，实际上就是记录右端点的
                overlapCount++;
                end = Math.min(end, intervals[i][1]);
            }
        }
        return overlapCount;
    }

    /*
    按右端点从小到大排序
    记录不重叠区间，最后 区间数-不重叠区间数
    也可以记录重叠区间个数反过来就行
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[1] - interval2[1];
            }
        });

        int n = intervals.length;
        int right = intervals[0][1];
        int noOverlapCount = 1;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= right) {
                right = intervals[i][1];
                noOverlapCount++;
            }
        }
        return n - noOverlapCount;
    }
    /*
    也可以记录重叠区间个数
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= right) {
                right = intervals[i][1];
            } else {
                ans++;
            }
        }
        return ans;
     */

    public static void main(String[] args) {
        new P435无重叠空间().eraseOverlapIntervals2(new int[][] {{1, 100}, {11, 22}, {1, 11}, {2, 12}});
    }
}
