package A1000PLAN.堆.easy;

import java.util.PriorityQueue;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/13
 **/
public class P703数据流中的第K大元素 {
    static class KthLargest {
        PriorityQueue<Integer> pq;

        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            pq = new PriorityQueue<Integer>();
            for (int x : nums) {
                add(x);
            }
        }

        // 第k大就是倒数第k个元素
        public int add(int val) {
            pq.offer(val);
            // 维持堆里是最后几个大的元素
            if (pq.size() > k) {
                pq.poll();
            }
            return pq.peek();
        }
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[] {4, 5, 8, 2});
        kthLargest.add(3);
        kthLargest.add(5);
        kthLargest.add(10);
        kthLargest.add(9);
        kthLargest.add(4);
    }
}
