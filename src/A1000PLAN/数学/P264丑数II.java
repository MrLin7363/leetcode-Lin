package A1000PLAN.数学;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *desc:
 *@author lin
 *@since 2023/12/1
 **/
public class P264丑数II {
    /*
    最小堆，比较 2x,3x,5x   第n次从堆中取出的就是第n个丑数
     */
    public int nthUglyNumber(int n) {
        Queue<Long> queue = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        queue.add(1L);
        set.add(1L);
        int ugly = 0;
        int[] factors = new int[] {2, 3, 5};
        for (int i = 0; i < n; i++) {
            long cur = queue.poll();
            ugly = (int)cur;
            for (int factor : factors) {
                long num = cur * factor;
                if (set.add(num)) {
                    queue.add(num);
                }
            }
        }
        return ugly;
    }
}
