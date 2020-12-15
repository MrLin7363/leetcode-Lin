package Linked_List.easy;

import Construct.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/30 15:21
 * @Describe: 快慢指针判断链表环
 */
public class Linked_List_Cycle_141 {
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
    /*
    官方
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

    /*
    注意这个因为只是环，判断，不用找中间结点  100 + 65
     */
    public boolean hasCycle(ListNode head) {
        if (head==null) return false;
        ListNode slow=head;
//        ListNode fast=head.next;  加不加都行，这里不加，因为环会相遇，如果是找中位数要加.next
        ListNode fast=head;
        // 保证了 fast.next!=null 那么 fast.next.next=null 没事 ，如果  null.next 就会报错
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)  return true;
        }
        return false;
    }
}
