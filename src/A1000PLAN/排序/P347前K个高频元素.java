package A1000PLAN.排序;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 快速选择排序算法 - 求一段数
 * P973最接近原点的K个点QuickSort 的进阶版， 将题目数组换为 int[][]{{数字，频率}，{数字，频率}}，就是类似973这题了，
 * 快排从大到小 所以左排右排需要根据  与中轴右边的距离比较  right-i+1
 * 如果快排改为为从大到小，则与  i-left+1 比较
 */
public class P347前K个高频元素 {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // 因为是新增数组，所以不采用 int[][]的形式，会有一些 00 初始化
        List<int[]> list = new ArrayList<>();
        map.forEach((key, value) -> {
            list.add(new int[]{key, value});
        });

        quickSort(list, 0, list.size() - 1, k);

        int[] res = new int[k];
        int i = 0, j = list.size() - 1;
        while (k > 0) {
            res[i++] = list.get(j--)[0];
            k--;
        }
        return res;
    }

    /**
     * 这里由于快排是从小到大，如果设置为从大到小，则与  i-left+1 比较
     * 中轴结点到尾节点距离  right-i+1
     * k<right-i+1 说明前K大的在右边区间里
     * k>right-i+1 说明前K大的在左边区间里，并且左排时，k = k - (right - i + 1)
     */
    private void quickSort(List<int[]> list, int left, int right, int k) {
        int i = left;
        int j = right;
        int pivotValue = list.get(left)[1];
        int[] pivot = list.get(left);
        while (i < j) {
            while (i < j && list.get(j)[1] >= pivotValue) {
                j--;
            }
            while (i < j && list.get(i)[1] <= pivotValue) {
                i++;
            }
            if (i < j) {
                int[] temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        list.set(left, list.get(i));
        list.set(i, pivot);

        // 从大到小前K个，算中轴结点到尾节点距离  right-i+1
        // 右排
        if (k < right - i + 1) {
            quickSort(list, i + 1, right, k);
        }
        // 左排, 左排的时候也可以优化： 提前把右边的数值加入结果数组，因为右边的都是满足条件的情况，这里加入结果数组，快排后不需要再从尾向前加入结果数组
        if (k > right - i + 1) {
            quickSort(list, left, i - 1, k - (right - i + 1));
        }
    }

    public static void main(String[] args) {
        new P347前K个高频元素().topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2);
    }

    // 从小到大排
    private void quickSortAsc(List<int[]> list, int left, int right, int k) {
        int i = left;
        int j = right;
        int pivotValue = list.get(left)[1];
        int[] pivot = list.get(left);
        while (i < j) {
            while (i < j && list.get(j)[1] <= pivotValue) {
                j--;
            }
            while (i < j && list.get(i)[1] >= pivotValue) {
                i++;
            }
            if (i < j) {
                int[] temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        list.set(left, list.get(i));
        list.set(i, pivot);

        // 左排
        if (k < i - left + 1) {
            quickSort(list, left, i - 1, k);
        }
        // 右排
        if (k > i - left + 1) {
            quickSort(list, i + 1, right, k - (i - left + 1));
        }
    }
}
