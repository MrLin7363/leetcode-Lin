 

package hw.案例;

import java.util.LinkedList;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/18
 **/
public class 电梯模拟 {
    int calDistance(int initFloor, int[][] passengers) {
        int length = passengers.length;
        LinkedList<int[]> paths = new LinkedList();
        for (int i = 0; i < length; i++) {
            if (paths.isEmpty()) {
                paths.add(passengers[i]);
                continue;
            }
            int[] last = paths.getLast();
            int direction = last[1] - last[0];
            // 1.新进入那个和电梯运行同方向 2.新进入乘客起点在上个乘客同方向的 路径后面
            if (((direction) * (passengers[i][1] - passengers[i][0])) > 0
                    && (direction) * (passengers[i][0] - last[0]) > 0) {
                // 可以顺路的乘客，当作一个乘客，更新最后到达的位置
                last[1] = direction > 0 ? Math.max(last[1], passengers[i][1]) : Math.min(last[1], passengers[i][1]);
            } else {
                // 不顺路的第二次接
                paths.add(passengers[i]);
            }
        }
        int sum = 0;
        for (int[] path : paths) {
            // 去接乘客的距离+到终点的距离
            sum += (Math.abs(path[0] - initFloor) + Math.abs(path[1] - path[0]));
            initFloor = path[1];
        }
        return sum;
    }
}
