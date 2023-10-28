package A1000PLAN.链表.easy;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/26
 **/
public class P160相交链表 {
    /*
    1.链表- 简单不展示
    2.同步移动指针，如果相交迟早相遇;如果不相交会同时到null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headA : p1.next;
            p2 = p2 == null ? headB : p2.next;
        }
        return p1;
    }
}
