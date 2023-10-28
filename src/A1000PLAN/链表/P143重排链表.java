package A1000PLAN.链表;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/17
 **/
public class P143重排链表 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        new P143重排链表().reorderList(head);
    }

    /*
    1.中间节点+反转链表+交替合并链表 - 下面解法
    2.转化成List线性表-太简单不推荐
    3.中间节点+右半部分加入栈+交替合并
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode mid = middelNode(head);
        ListNode reverseNode = reverseList(mid.next);
        mid.next = null;

        merge(head, reverseNode);
    }

    // 交替合并链表
    private void merge(ListNode l1, ListNode l2) {
        ListNode l1Temp;
        ListNode l2Temp;
        while (l1 != null && l2 != null) {
            l1Temp = l1.next;
            l2Temp = l2.next;

            l1.next = l2;
            l1 = l1Temp;

            l2.next = l1;
            l2 = l2Temp;
        }
    }

    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode prev = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // 找中间偏左的
    private ListNode middelNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
