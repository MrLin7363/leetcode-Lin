package A1000PLAN.链表.删除;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/16
 **/
public class P82删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode sentinel = new ListNode(-1, head);
        ListNode prev = sentinel;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            // 有同的
            if (cur.next.val == cur.val) {
                // 找到同的最后一个
                while (cur.next != null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                // 一次性跳跃
                prev.next = cur;
            } else {
                prev = prev.next;
                cur = cur.next;
            }
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(4);
        new P82删除排序链表中的重复元素II().deleteDuplicates(head);
    }
}
