package A1000PLAN.链表;

import Construct.ListNode;

/**
 * desc: 请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 * @author 
 * @since 2023/10/17
 **/
public class P86分隔链表_双指针链表 {
    /*
    双指针
    两个链表small和big，依次加入节点， 最后再连接两个链表
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode smallSentinel = new ListNode(-1);
        ListNode bigSentinel = new ListNode(-1);
        ListNode small = smallSentinel;
        ListNode big = bigSentinel;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
                head = head.next;
                small.next = null;
            } else {
                big.next = head;
                big = big.next;
                head = head.next;
                big.next = null;
            }
        }
        small.next = bigSentinel.next;
        return smallSentinel.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(2);
        new P86分隔链表_双指针链表().partition(head, 3);
    }
}