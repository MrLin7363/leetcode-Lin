package Linked_List.hard;

import Construct.ListNode;

/**
 * @author: Junlin Chen
 * @Date: 2020/12/15 21:51
 * @Describe: 每k个一组节点 反转
 */
public class P25_Reverse_Nodes_in_kGroup {
    /*
    100 + 53 迭代  判断 - 反转制定区间 -连接
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode sentinel=new ListNode(0,head);
        ListNode prev=sentinel;
        while (head!=null){
            ListNode tail=prev;
            // 移动tail并检查剩下节点组是否还需要反转
            for (int i = 0; i < k; i++) {
                tail=tail.next;
                if (tail==null){
                    return sentinel.next;
                }
            }
            ListNode next=tail.next;
            // 反转制定区间内的链表，并返回头尾节点
            ListNode[] reverse = myReverse(head, tail);
            head=reverse[0];
            tail=reverse[1];
            // 连接剩下的
            prev.next=head;
            prev=tail;
            head=next;
        }
        return sentinel.next;
    }
    public ListNode[] myReverse(ListNode head,ListNode tail){
        ListNode prev=tail.next;
        ListNode cur=head;
        while (prev!=tail){
            ListNode next=cur.next;
            cur.next=prev; // 第一次反转时，头结点就连接了下一个结点
            prev=cur;
            cur=next;
        }
        return new ListNode[]{tail,head};
    }
}
