package Array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
  *@Author JunLin
  *@Date 2021/1/7
  *@Describe: 合并区间
 */

public class P56_Merge_Intervals {

    /*
    5+ 5
    1.排序按左端点
    2.遍历区间，如果不重合，添加进结果
    如果重合，上一个结果右边界限右移
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length==0) return new int[0][2];
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] intervals1, int[] intervals2) {
                return intervals1[0]-intervals2[0];
            }
        });
        List<int[]> merged=new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left=intervals[i][0],right=intervals[i][1];
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (merged.size()==0 || merged.get(merged.size()-1)[1]<left){
                merged.add(new int[]{left,right});
            }else{
                // 反之将当前区间合并至结果数组的最后区间
                merged.get(merged.size()-1)[1]=Math.max(merged.get(merged.size()-1)[1],right);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /*
    简短版 5 + 5
     */
    public int[][] merge2(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1); // 把多余的数组元素去掉，  [0,0]
    }

}
