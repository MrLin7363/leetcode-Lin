 

package hw.单调栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/10
 **/
public class P496下一个更大元素1 {

    /*
    从右往左 单调栈 遍历，求出每个位置的下一个最大元素
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = m - 1; i >= 0; i--) {
            int num = nums2[i];
            // 如果当前元素大于等于栈顶元素就出栈
            while (!stack.isEmpty() && num >= nums2[stack.peek()]) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }


}
