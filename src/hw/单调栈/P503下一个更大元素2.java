 

package hw.单调栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/10
 **/
public class P503下一个更大元素2 {

    /*
    数组扩展一倍，倒叙求最大
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = 2 * n - 1; i >= 0; i--) {
            int num = nums[i % n];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            ans[i%n]=stack.isEmpty()?-1:stack.peek();
            stack.push(num);
        }
        return ans;
    }

}
