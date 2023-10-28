package A1000PLAN.链表.删除;

import Construct.ListNode;

/**
 * desc: https://leetcode.cn/problems/remove-linked-list-elements/solutions/10957/203yi-chu-lian-biao-yuan-su-by-lewis-dxstabdzew/
 *
 * @author 
 * @since 2023/9/26
 **/
public class P203移除链表元素 {
    /*
    哨兵结点
     */
    public ListNode removeElements1(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode prev = sentinel;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return sentinel.next;
    }

    /*
    递归
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    /*
    删除头结点另外考虑
     */
    public ListNode removeElements2(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        new P203移除链表元素().removeElements2(head, 6);
    }
}
