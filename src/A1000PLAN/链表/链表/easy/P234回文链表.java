package A1000PLAN.链表.easy;

import Construct.ListNode;

/**
 * desc:
 * 1.ON+ON  复制链表元素到数组，前后判断
 * 2.ON+ON  递归
 * 3.ON+O1  快慢指针找中间节点 + 反转链表
 *
 * @author c30021507
 * @since 2023/10/9
 **/
public class P234回文链表 {
    /*
    3快慢指针找中间节点 + 反转链表
     */
    public boolean isPalindrome2(ListNode head) {
        // 反转链表的左侧节点
        ListNode prev = middleNode(head);
        ListNode reverseBegin = reverse(prev.next);
        boolean flag = true;
        ListNode secondHead = reverseBegin;
        while (secondHead != null && flag) {
            if (head.val != secondHead.val) {
                flag = false;
            }
            secondHead = secondHead.next;
            head = head.next;
        }
        // 可以恢复链表，题目不做要求
        prev.next = reverse(reverseBegin);
        return flag;
    }

    private ListNode reverse(ListNode head) {
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

    // 中间偏左的中间节点
    private ListNode middleNode(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*
    2.递归
    */
    private ListNode frontNode;

    public boolean isPalindrome(ListNode head) {
        frontNode = head;
        boolean pal = isPal(head);
        return pal;
    }

    private boolean isPal(ListNode cur) {
        if (cur != null) {
            if (!isPal(cur.next)) {
                return false;
            }
            if (frontNode.val != cur.val) {
                return false;
            }
            frontNode = frontNode.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        new P234回文链表().isPalindrome(head);
    }
}
