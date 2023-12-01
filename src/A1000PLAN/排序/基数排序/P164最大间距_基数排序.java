package A1000PLAN.排序.基数排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *desc:
 *@author lin
 *@since 2023/11/27
 **/
public class P164最大间距_基数排序 {
    /*
    1. 计算最大值, 计算最大位数
    2. 初始化10个桶
    3. 数组按位循环，依次加入桶，再依次出桶，设置到数组
    4. 求最大相邻间距
     */
    public int maximumGap(int[] nums) {
        int maxVal = Arrays.stream(nums).max().getAsInt();
        int ratioSize = 0;
        while (maxVal != 0) {
            ratioSize++;
            maxVal /= 10;
        }

        List<List<Integer>> buckets = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            buckets.add(new LinkedList<>());
        }

        for (int i = 0; i < ratioSize; i++) {
            // 入桶
            for (int j = 0; j < nums.length; j++) {
                int ratio = (nums[j] / (int) Math.pow(10, i)) % 10;
                buckets.get(ratio).add(nums[j]);
            }
            // 出桶
            int idx = 0;
            for (int j = 0; j < 10; j++) {
                for (Integer num : buckets.get(j)) {
                    nums[idx++] = num;
                }
                buckets.get(j).clear();
            }
        }
        int ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            ans = Math.max(ans, Math.abs(nums[i] - nums[i + 1]));
        }
        return ans;
    }

    public static void main(String[] args) {
        new P164最大间距_基数排序().maximumGap(new int[] {3, 6, 9, 1});
    }
}
