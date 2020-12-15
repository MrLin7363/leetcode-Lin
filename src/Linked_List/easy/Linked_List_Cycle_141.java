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
   /* public boolean hasCycle(ListNode head) {
        Set<ListNode> nodeSet=new HashSet<>();
        while (head != null){
            nodeSet.add(head);
            head=head.next;
            if (nodeSet.contains(head))
                return true;
        }
        return false;
    }*/
    //
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
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
    注意这个因为只是环，判断，不用找中间结点
    如果用快慢指针找中间结点
     */
    public boolean hasCycle(ListNode head) {
        if (head==null) return false;
        ListNode slow=head;
        ListNode fast=head.next;
        // 保证了 fast.next!=null 那么 fast.next.next=null 没事 ，如果  null.next 就会报错
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)  return true;
        }
        return false;
    }
}
