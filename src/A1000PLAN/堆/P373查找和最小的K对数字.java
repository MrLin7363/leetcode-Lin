package A1000PLAN.堆;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *desc:
 *@author lin
 *@since 2023/11/13
 **/
public class P373查找和最小的K对数字 {
    /*
    1.堆-推荐
    https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/solutions/1208350/cha-zhao-he-zui-xiao-de-kdui-shu-zi-by-l-z526/?envType=study-plan-v2&envId=top-interview-150
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return (nums1[o1[0]] + nums2[o1[1]]) - (nums1[o2[0]] + nums2[o2[1]]);
        });
        int n = nums1.length;
        int m = nums2.length;
        for (int i = 0; i < Math.min(n, k); i++) {
            pq.offer(new int[] {i, 0});
        }
        List<List<Integer>> res = new ArrayList<>();
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idx = pq.poll();
            res.add(Arrays.asList(nums1[idx[0]], nums2[idx[1]]));

            if (idx[1] + 1 < m) {
                pq.offer(new int[] {idx[0], idx[1] + 1});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new P373查找和最小的K对数字().kSmallestPairs(new int[] {1, 7, 11}, new int[] {2, 4, 6}, 3);
    }
}
