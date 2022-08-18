 

package hw.单调栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/9
 **/
public class P739每日温度 {

    /*
    单调栈
    请根据每日气温 列表 temperatures，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用0 来代替
    输入: temperatures = [73,74,75,71,69,72,76,73]
    输出：[1,1,4,2,1,1,0,0]
    75+30
    因为是求距离所以正序求
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = temperatures.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                ans[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }


}
