package A1000PLAN.堆;

import java.util.Comparator;
import java.util.PriorityQueue;

public class P973最接近原点的K个点 {
    public int[][] kClosest(int[][] points, int k) {
        // 默认是升序 小顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            // o1 新的，o2旧的， 结果负数加前面，正数+0 加后面
            @Override
            public int compare(int[] o1, int[] o2) {
                double dist1 = Math.sqrt(Math.pow(o1[0], 2) + Math.pow(o1[1], 2));
                double dist2 = Math.sqrt(Math.pow(o2[0], 2) + Math.pow(o2[1], 2));
                return dist1 < dist2 ? (int) -1.1 : 1;
                //  double v = dist1 - dist2;
                //  System.out.println((int)v); // -0.2222 = 0 所以不能这样，最好是比较大小
                //  return (int) v;
            }
        });
        for (int[] point : points) {
            queue.offer(point);
        }
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] result = new P973最接近原点的K个点().kClosest(new int[][]{{1, 3}, {-2, 2}}, 1);
        for (int[] re : result) {
            System.out.println(re[0] + "||||" + re[1]);
        }
    }
}
