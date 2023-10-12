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
public class P141环形链表 {
    /*
    1.快慢指针-模板1
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /*
    快慢指针-模板2-不推荐
    P142 因为根据这个找到的相遇点，slow=head，fast不变同步移动，永远不会相遇
    */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next; // 因为判断条件为 != 所以要为.next
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    // 哈希-简单
    public boolean hasCycle3(ListNode head) {
        Set<ListNode> nodeSet=new HashSet<>();
        while (head != null){
            nodeSet.add(head);
            head=head.next;
            if (nodeSet.contains(head))
                return true;
        }
        return false;
    }
}
