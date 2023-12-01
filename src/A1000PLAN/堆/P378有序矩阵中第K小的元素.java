package A1000PLAN.堆;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *desc:
 *@author lin
 *@since 2023/11/13
 **/
public class P378有序矩阵中第K小的元素 {
    /*
    堆-归并排序:每一行都是有序的，加入行首，类似P23合并K个链表
     */
    public int kthSmallest(int[][] matrix, int k) {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new int[] {matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] index = pq.poll();
            if (index[2] < matrix[0].length) {
                pq.offer(new int[] {matrix[index[1]][index[2] + 1], index[1], index[2] + 1});
            }
        }
        return pq.poll()[0];
    }
}
