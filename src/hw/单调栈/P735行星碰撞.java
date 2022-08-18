package hw.单调栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/23
 **/
public class P735行星碰撞 {

    /*
    首先，循环每一个元素时，在什么情况下无脑入栈呢？

    栈为空
    栈顶元素为负数(下一个为负数则一起向左，下一个为正数则分向两边)
    当前元素为正数（栈顶为正一起向右，栈顶为负分向两边）
    下来，我们需要看碰撞的场景又细分为什么情况：

    栈顶元素大于abs(当前元素)，当前元素被撞毁
    栈顶元素等于abs(当前元素)，栈顶弹出和当前元素抵消
    栈顶元素小于abs(当前元素)，栈顶弹出，并与新栈顶完成上述判断
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();
        int index = 0;
        while (index < asteroids.length) {
            // 没有碰撞，栈顶元素向左，或当前元素向右
            if (deque.isEmpty() || deque.peek() < 0 || asteroids[index] > 0) {
                deque.push(asteroids[index]);
            } else if (deque.peek() <= -asteroids[index]) {
                // 栈顶元素都会被撞坏
                Integer pop = deque.pop();
                if (pop < -asteroids[index]) {
                    // 新的元素不会被撞坏，继续遍历队列
                    continue;
                }
            }
            index++;
        }
        int[] res = new int[deque.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = deque.pop();
        }
        return res;
    }

}
