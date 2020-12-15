package Linked_List.medium;

import Construct.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Junlin Chen
 * @Date: 2020/12/15 22:41
 * @Describe: 返回环开始的链表节点
 */
public class P142_Linked_List_Cycle_II {
    // 快慢指针 - 注意快慢指针的起始点一致 100 + 42
    public ListNode detectCycle(ListNode head) {
        if (head==null) return null;
        ListNode slow=head,fast=head;
        // 找相遇点
        while (fast!=null){
            slow=slow.next;
            if (fast.next!=null){
                fast=fast.next.next;
            }else{
                return null;
            }
            // 相遇结点
            if (fast==slow){
                ListNode begin=head;
                //从相遇点到入环点的距离加上 n−1 圈的环长，恰好等于从链表头部到入环点的距离。
                while (begin!=slow){
                    begin=begin.next;
                    slow=slow.next;
                }
                return begin;
            }
        }
        return null;
    }
    // hash 22 + 16
    public ListNode detectCycle3(ListNode head) {
        Set<ListNode> nodeSet=new HashSet<>();
        while (head != null){
            nodeSet.add(head);
            head=head.next;
            if (nodeSet.contains(head))
                return head;
        }
        return head;
    }
}
