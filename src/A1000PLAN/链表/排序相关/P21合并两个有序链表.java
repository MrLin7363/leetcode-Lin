package A1000PLAN.链表.排序相关;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/26
 **/
public class P21合并两个有序链表 {
    // 迭代
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode sentinel = new ListNode(0);
        ListNode cur = sentinel;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        // 剩下的拼接上
        cur.next = list1 == null ? list2 : list1;
        return sentinel.next;
    }

    /*
    递归-需要多理解
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(3);
        new P21合并两个有序链表().mergeTwoLists2(head, head2);
    }
}
