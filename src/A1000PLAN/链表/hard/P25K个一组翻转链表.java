package A1000PLAN.链表.hard;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/19
 **/
public class P25K个一组翻转链表 {
    /*
    迭代：反转链表
    // 1.断开第i组链表与前后联系:
    // 3.反转链表
    // 2.连接前后
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }

        ListNode sentinel = new ListNode(-1, head);
        ListNode prev = sentinel;
        cur = head;
        for (int i = k; i <= size; i = i + k) {
            // 1.断开第i组链表与后联系
            ListNode node = cur;
            // cur移动到要分割前面节点
            for (int j = 0; j < k - 1; j++) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur = next;

            // 2.反转链表
            ListNode begin = reverseNode(node);
            // 3.前面的连接后面新反转好的链表
            prev.next = begin;
            // prev节点移动到当前链表的最后，node就是
            prev = node;
        }
        // 如果有剩下的连接剩下的
        prev.next = cur;
        return sentinel.next;
    }

    private ListNode reverseNode(ListNode head) {
        if (head == null) {
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
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);
        new P25K个一组翻转链表().reverseKGroup(head, 2);
    }

}
