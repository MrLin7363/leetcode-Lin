package A1000PLAN.链表;

import Construct.ListNode;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/10/10
 **/
public class P2两数相加 {
    /*
    链表顺序相加，进位记录在下一位加上就行(向前进位)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(-1);
        int carry = 0;
        int v;
        int sum;
        ListNode prev = sentinel;
        while (l1 != null || l2 != null) {
            int l1Value = l1 == null ? 0 : l1.val;
            int l2Value = l2 == null ? 0 : l2.val;
            sum = l1Value + l2Value + carry;
            v = sum % 10;
            carry = sum / 10;

            ListNode cur = new ListNode(v);
            prev.next = cur;
            prev = cur;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            prev.next = new ListNode(carry);
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(8);
        l1.next.next = new ListNode(9);
        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(2);
        ListNode listNode = new P2两数相加().addTwoNumbers(l1, l2);
        System.out.println(listNode);
    }
}