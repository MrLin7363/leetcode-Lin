package A1000PLAN.数组.区间类;

import java.util.Arrays;

/**
 *desc:
 *@author lin
 *@since 2023/11/30
 **/
public class P986区间列表的交集 {
    /*
    两个区间列表，切换着来合并， 如果当前区间右值比较大，则保留，对面区间列表选下一个比较
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int f = 0;
        int s = 0;
        // 长度预留2倍最大值或者直接用list替代
        int[][] res = new int[Math.max(firstList.length, secondList.length) * 2][2];
        int index = 0;
        while (f < firstList.length && s < secondList.length) {
            int[] first = firstList[f];
            int[] second = secondList[s];
            // 合并
            index = merge(res, index, first, second);
            // 保留first
            if (first[1] >= second[1]) {
                s++;
            } else {
                f++;
            }
        }
        // 截取数组index长度个元素
        return Arrays.copyOf(res, index);
    }

    private static int merge(int[][] res, int index, int[] first, int[] second) {
        int start = Math.max(first[0], second[0]);
        int end = Math.min(first[1], second[1]);
        if (start <= end) {
            res[index++] = new int[] {start, end};
        }
        return index;
    }

    public static void main(String[] args) {
        int[][] firstList = new int[][] {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = new int[][] {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        new P986区间列表的交集().intervalIntersection(firstList, secondList);
    }
}
