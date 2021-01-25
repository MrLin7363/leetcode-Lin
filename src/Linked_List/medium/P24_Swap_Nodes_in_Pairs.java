package Linked_List.medium;

import Construct.ListNode;


/**
 * @author: Junlin Chen
 * @Date: 2020/12/15 20:46
 * @Describe: 相邻两个节点交换
 */
public class P24_Swap_Nodes_in_Pairs {
    /*
    递归 100 + 79
     */
    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next==null) return head;
        ListNode newHead=head.next;
        head.next=swapPairs(newHead.next); // 比如第一个结点.next 等于第一个结点的.next.next的swapPairs
        newHead.next=head;
        return newHead;
    }

    /*
    官方迭代  100 + 46
    */
    public ListNode swapPairs2(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }

    /*
    自己写的迭代 100 + 31
     */
    public ListNode swapPairs3(ListNode head) {
        ListNode sentinel = new ListNode(0,head);
        ListNode prev = sentinel;
        while ( head!=null && head.next!=null ){
            // swap
            prev.next=head.next;
            prev=head.next;
            head.next=prev.next;
            prev.next=head;
            prev=head;
            head=head.next;
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        P24_Swap_Nodes_in_Pairs s=new P24_Swap_Nodes_in_Pairs();
        System.out.println(s.swapPairs(head));
    }
}
