package A1000PLAN.链表.反转链表;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/8
 **/
public class P92反转链表II {
    /*
    断层单独反转 借用P206题
    */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode sentinel = new ListNode(-1, head);
        // 找断层的开始和结束节点
        ListNode cur = head;
        ListNode prev = sentinel; // 反转端的左侧
        ListNode leftBegin; // 要反转段的左端点
        ListNode rightEnd; // 要反转端的右端点
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        leftBegin = prev.next;
        rightEnd = prev;
        for (int i = 0; i < right - left + 1; i++) {
            rightEnd = rightEnd.next;
        }
        cur = rightEnd.next;
        // 断开连接
        prev.next = null;
        rightEnd.next = null;
        // 反转链表
        reverse(leftBegin);
        // 连接反转后的链表-左侧
        prev.next = rightEnd;
        // 连接右侧
        leftBegin.next = cur;
        return sentinel.next;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = null;
        ListNode prev = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        new P92反转链表II().reverseBetween(head, 2, 4);
    }

    /*
    头插法
    */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode sentinel = new ListNode(-1, head);
        ListNode prev = sentinel;

        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        ListNode cur = prev.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return sentinel.next;
    }
}
