 

package hw.单调栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/10
 **/
public class P962最大宽度坡 {

    /*
    （单调栈：存的是从 A[0] 开始的递减序列）
    如 6 1 2 0 5  递减就是 6 1 0  2虽然比1大，但是 1和5之间的距离一定比2和5大，所以非递减队列里的不用考虑宽度也行
    然后再从末尾向前遍历，如果大于栈顶，则更新一次最大宽度
     */
    public static int maxWidthRamp(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < n; i++) {
            if (stack.isEmpty() || nums[i] < nums[stack.peek()]) {
                stack.push(i);
            }
        }
        int maxWidth = 0;
        // 从末尾向前遍历
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
                Integer pop = stack.pop();
                maxWidth = Math.max(maxWidth, i - pop);
            }
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        maxWidthRamp(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 1});
    }

}
