package A1000PLAN.栈.单调栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * desc:https://leetcode.cn/problems/largest-rectangle-in-histogram/solutions/266844/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode-/
 *
 * @author Lin
 * @since 2023/10/28
 **/
public class P84柱状图中最大的矩形_hard {
    /*
     1.枚举宽：超时 固定宽度，找左右最小高度
     2.枚举高：超时 找比当前高度小的左右边界，这里遍历找效率低，第3种方法单调栈找高效
     3.单调栈-两个：枚举高： 从左到右找最近的小于其高度的柱子; 从右到左找最近的小于其高度的柱子; 类似每日温度
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] left = new int[n];
        int[] right = new int[n];

        // 找左边界
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // 找右边界
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int ans = -1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    // 优化成一次遍历
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Deque<Integer> mono_stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i; // 可以一次遍历同时求出右边界，只要左边界加上这行
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        new P84柱状图中最大的矩形_hard().largestRectangleArea(new int[] {2, 1, 5, 6, 2, 3});
    }
}
