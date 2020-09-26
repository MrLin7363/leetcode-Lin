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
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodeSet=new HashSet<>();
        while (head != null){
            nodeSet.add(head);
            head=head.next;
            if (nodeSet.contains(head))
                return true;
        }
        return false;
    }
    /*public boolean hasCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)  return true;
        }
        return false;
    }*/
}
