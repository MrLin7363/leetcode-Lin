package A1000PLAN.栈.单调栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/24
 **/
public class P739每日温度 {
    /*
    栈-几天后下一个更高的温度
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<int[]> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                int[] pop = stack.pop();
                res[pop[1]] = i - pop[1];
            }
            stack.push(new int[] {temperatures[i], i});
        }
        return res;
    }

    public static void main(String[] args) {
        new P739每日温度().dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73});
    }
}
