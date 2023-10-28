package A1000PLAN.链表.删除;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/16
 **/
public class P83删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == cur.val) {
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
        head.next.next = new ListNode(2);
        new P83删除排序链表中的重复元素().deleteDuplicates(head);
    }
}
