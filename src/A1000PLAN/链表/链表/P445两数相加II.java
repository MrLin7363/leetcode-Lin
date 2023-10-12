package A1000PLAN.链表;

import Construct.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * desc: 这题将两个链表反转就是P2题目一样，但是题目要求不反转
 *
 * @author c30021507
 * @since 2023/10/11
 **/
public class P445两数相加II {
    /*
    本题的主要难点在于链表中数位的顺序与我们做加法的顺序是相反的，为了逆序处理所有数位，
    我们可以使用  栈：把所有数字压入栈中，再依次取出相加
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode prev = null;
        int carry = 0;
        // 注意carry>0的情况如 5+5
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int i = stack1.isEmpty() ? 0 : stack1.pop();
            int j = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = i + j + carry;
            int cur = sum % 10;
            carry = sum / 10;

            ListNode node = new ListNode(cur);
            node.next = prev;
            prev = node;
        }
        return prev;
    }
}
