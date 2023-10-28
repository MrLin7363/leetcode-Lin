package A1000PLAN.链表;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/17
 **/
public class P61旋转链表 {
    /*
    推荐官方：
    将链表连接成环，直接找到最后一个节点再断开就行
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        // 要断开的第几个节点
        int end = len - k % len;
        if (end == len) { // 最后一个断开，就是原链表
            return head;
        }

        // 连接成环
        cur.next = head;
        while (end-- > 0) {
            cur = cur.next;
        }
        head = cur.next;
        cur.next = null;
        return head;
    }

    /*
    自己写的，O kn
    1.计算链表的长度，真正右移次数为  k % length
    2.右移逻辑：尾节点作为头结点，尾节点指向第一个节点，倒数第二个节点指向null
     */
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        k = k % length;

        // 右移
        ListNode prev = head;
        for (int i = 0; i < k; i++) {
            while (prev.next.next != null) {
                prev = prev.next;
            }
            prev.next.next = head;
            head = prev.next;
            prev.next = null;
            prev = head;
        }
        return head;
    }
}
