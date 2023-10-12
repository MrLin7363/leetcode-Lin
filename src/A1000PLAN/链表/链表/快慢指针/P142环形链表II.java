package A1000PLAN.链表.快慢指针;

import Construct.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/26
 **/
public class P142环形链表II {
    /*
    找到环点
    1.快慢指针-模板1
    注意：这里不能初始化fast=head.next类似141题的模板2，因为这样找到的相遇点再同步移动永远不会相遇
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 找到相遇点,slow回到head fast同步移动,相遇即是起始点
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
        new P142环形链表II().detectCycle(head);
    }

    // hash 22 + 16
    public ListNode detectCycle3(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();
        while (head != null) {
            nodeSet.add(head);
            head = head.next;
            if (nodeSet.contains(head)) {
                return head;
            }
        }
        return head;
    }
}
