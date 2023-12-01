package A1000PLAN.队列.单调队列;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/15
 **/
public class P239滑动窗口最大值_hard {
    /*
    双向单调队列：
    如果新来的值大于队列里的值，队列依次出队；(从大到小)
    移动窗口，如果满足则记录最大值
    输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
    输出: [3,3,5,5,6,7]

    解释过程中队列中都是具体的值，方便理解，具体见代码
    初始状态：L=R=0,队列:{}
    i=0,nums[0]=1。队列为空,直接加入。队列：{1}
    i=1,nums[1]=3。队尾值为1，3>1，弹出队尾值，加入3。队列：{3}
    i=2,nums[2]=-1。队尾值为3，-1<3，直接加入。队列：{3,-1}。此时窗口已经形成，L=0,R=2，result=[3]
    i=3,nums[3]=-3。队尾值为-1，-3<-1，直接加入。队列：{3,-1,-3}。队首3对应的下标为1，L=1,R=3，有效。result=[3,3]
    i=4,nums[4]=5。队尾值为-3，5>-3，依次弹出后加入。队列：{5}。此时L=2,R=4，有效。result=[3,3,5]
    i=5,nums[5]=3。队尾值为5，3<5，直接加入。队列：{5,3}。此时L=3,R=5，有效。result=[3,3,5,5]
    i=6,nums[6]=6。队尾值为3，6>3，依次弹出后加入。队列：{6}。此时L=4,R=6，有效。result=[3,3,5,5,6]
    i=7,nums[7]=7。队尾值为6，7>6，弹出队尾值后加入。队列：{7}。此时L=5,R=7，有效。result=[3,3,5,5,6,7]

    https://leetcode.cn/problems/sliding-window-maximum/solutions/10025/shuang-xiang-dui-lie-jie-jue-hua-dong-chuang-kou-2/
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 队列存下标(当前最大)，可以判断是否达到窗口
        Deque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            // 判断队首是否在窗口中，不在出队
            while (deque.peek() <= i - k) {
                // 移除队列头
                deque.poll();
            }
            // 如果已经是窗口
            if (i + 1 >= k) {
                ans[i - k + 1] = nums[deque.peek()];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        new P239滑动窗口最大值_hard().maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3);
        new P239滑动窗口最大值_hard().maxSlidingWindow(new int[] {1}, 1);
    }
}
