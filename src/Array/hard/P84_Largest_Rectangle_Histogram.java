package Array.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * @author: Junlin Chen
 * @Date: 2021/07/04 15:24
 * @Describe: 柱状图的最大面积
 */
public class P84_Largest_Rectangle_Histogram {

    /*
   单调栈-一次遍历 ,右边界在 往右遍历的过程中确定 77+75
    */
    public int largestRectangleAreaMono2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right,n);// 默认右边界是最右的
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()]=i; // 确定栈中上一个节点的右边界
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        int area = 0;
        for (int i = 0; i < n; i++) {
            area = Math.max(area, heights[i] * (right[i] - left[i] - 1));
        }
        return area;
    }

    /*
    单调栈-两次遍历  68+90
     */
    public int largestRectangleAreaMono(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        // 纪录该柱子 左侧最近且高度小于该柱子的下标，求左边界
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        // 纪录该柱子 右侧最近且高度小于该柱子的下标，求右边界
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        int area = 0;
        for (int i = 0; i < n; i++) {
            area = Math.max(area, heights[i] * (right[i] - left[i] - 1));
        }
        return area;
    }


    /*
    暴力超时 - 枚举宽
     */
    public int largestRectangleArea5(int[] heights) {
        int n=heights.length;
        int area=0;
        for (int left = 0; left < n; left++) {
            int minHeight=Integer.MAX_VALUE;
            for (int right = left; right < n; right++) {
                // 确定高度
                minHeight=Math.min(minHeight,heights[right]);
                // 计算面积
                area=Math.max(area,(right-left+1)*minHeight);
            }
        }
        return area;
    }
    /*
    暴力超时-枚举高
     */
    public int largestRectangleArea4(int[] heights) {
        int n = heights.length;
        int area = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            int left = mid, right = mid;
            // 确定左右边界
            while (left > 0 && heights[left - 1] >= height) {
                left--;
            }
            while (right < n - 1 && heights[right + 1] >= height) {
                right++;
            }
            // count area
            area = Math.max(area, (right - left + 1) * height);
        }
        return area;
    }
}
