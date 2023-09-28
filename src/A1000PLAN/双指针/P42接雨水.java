package A1000PLAN.双指针;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc: 双指针/单调栈/动态规划
 *
 * @author c30021507
 * @since 2023/9/25
 **/
public class P42接雨水 {
    public static void main(String[] args) {
        new P42接雨水().trap2(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        new P42接雨水().trap2(new int[] {2, 0, 0, 2});
        new P42接雨水().trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        new P42接雨水().trap(new int[] {2, 0, 0, 2});
    }

    /*
    推荐
    单调栈: 从大到小，遇到更大的出队
    计算情况：栈必须得有两个元素，height[i]>stack[top]的情况:top出队，下一个元素为 left, top其实为雨水区域的
     sum+= (i-left-1)*Math.min(height[i],height[left])-height[top]
     */
    public int trap2(int[] height) {
        int n = height.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                Integer top = stack.pop();
                // 出队后栈为空，无法构成雨水区域
                if (stack.isEmpty()) {
                    break;
                }
                Integer left = stack.peek();
                int width = i - left - 1;
                sum += width * (Math.min(height[i], height[left]) - height[top]);
            }
            stack.push(i);
        }
        return sum;
    }

    /*
    推荐
      DP：其实是两个最高值的阴影的重合区域
      leftMax[i] 0~i节点的最大高度
      O(N)  O(N)
    */
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return sum;
    }

    /*
    双指针：动态规划DP的优化版,将O(n)的空间优化为O(1)
    */
    public int trap1(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int leftMax = 0;
        int rightMax = 0;
        int sum = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                sum += leftMax - height[left];
                left++;
            } else {
                sum += rightMax - height[right];
                right--;
            }
        } return sum;
    }

    /*
    单指针：左右各来一次单指针到最高点：遍历过程中记录最高值，如果新的比最高值小,结果加上最高值-当前值，sum+=max-cur
    遍历截至为最高点，因为再往最高点另一边，max-cur不一定满足
    类似双指针的做法，只不过双指针的做法是边迭代边计算最高点
     */
    public int trap4(int[] height) {
        int sum = 0;
        int n = height.length;
        int max = 0;
        // 记录左遍历最高点坐标
        int maxRight = 0;
        for (int i = 0; i < n; i++) {
            if (height[i] >= max) {
                max = height[i];
                maxRight = i;
            }
        }
        max = 0;
        for (int i = 0; i <= maxRight; i++) {
            if (height[i] < max) {
                sum += max - height[i];
            }
            max = Math.max(max, height[i]);
        }

        max = 0;
        int maxLeft = 0;
        // 记录右遍历最高点坐标:注意[2,0,2]的情况，避免就是>=maxRight就不会重复计算
        for (int j = n - 1; j >= maxRight; j--) {
            if (height[j] >= max) {
                max = height[j];
                maxLeft = j;
            }
        }
        max = 0;
        for (int j = n - 1; j >= maxLeft; j--) {
            if (height[j] < max) {
                sum += max - height[j];
            }
            max = Math.max(max, height[j]);
        }
        return sum;
    }
}
