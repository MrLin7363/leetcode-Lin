package A1000PLAN.链表.反转链表;

import Construct.ListNode;

/**
 * //思路：两个节点两个节点依次从前到后反转断开遍历，1>2>3>4>5
 * //第一次遍历后是：null <1 2>3>4>5   两个链表 pre =1 ,head=2
 * //第二次遍历后是：  1<2  3>4>5   两个链表 pre =2 ,head=3
 * //第一次遍历是：  1<2<3  4>5
 * //最后：  1<2<3<4<5   头结点指向5   OK
 **/
public class P206反转链表 {
    /*
    迭代:三节点迭代反转链表法
    ON + O1
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode next = null;
        ListNode prev = null;
        while (head != null) {
            // 记住下一个节点
            next = head.next;
            // head指向前面，断开结点,第一次是指向null
            head.next = prev;
            // pre记住当前结点
            prev = head;
            // head后移
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
        new P206反转链表().reverseList2(head);
    }

    /*
    递归:重点理解 head.next.next = head;   1>2>3>4<5
    ON + ON
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
