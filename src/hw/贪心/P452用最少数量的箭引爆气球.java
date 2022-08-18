 

package hw.贪心;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/25
 **/
public class P452用最少数量的箭引爆气球 {

    /*
    区间按结尾从小到大排序，循环排好序的数组，如果后面一个区间的开始大于前面一个区间的结尾，就需要新增一条箭
     */
    public static int findMinArrowShots2(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        // 直接用这个 - 的话可能会存在溢出问题
//        Arrays.sort(points, (o1, o2) -> o1[1] - o2[1]);
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int pos = points[0][1];
        int ans = 1;
        for (int[] point : points) {
            if (point[0] > pos) {
                pos = point[1];
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // 溢出会返回到负数循环
        System.out.println(2147483647 + 1);
        System.out.println(2147483647 + 2);
        int[][] pointe = new int[2][2];
        pointe[0][0] = -2147483646;
        pointe[0][1] = -2147483645;
        pointe[1][0] = 2147483646;
        pointe[1][1] = 2147483647;
        findMinArrowShots2(pointe);
    }
}
