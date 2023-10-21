package A1000PLAN.链表.快慢指针;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/28
 **/
public class P876链表的中间结点 {
    // 偶数链表返回右边的中间结点 100 + 63  -推荐
    private ListNode middleNode3(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 偶数链表返回左边的中间结点，返回左边的一般为找中间结点的题
    private ListNode middleNode2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 偶数链表返回左边的中间结点，返回左边的一般为找中间结点的题，这个多用  -推荐
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(5);
        System.out.println(new P876链表的中间结点().middleNode(head).val);
        System.out.println(new P876链表的中间结点().middleNode2(head).val);
        System.out.println(new P876链表的中间结点().middleNode3(head).val);
    }
}
