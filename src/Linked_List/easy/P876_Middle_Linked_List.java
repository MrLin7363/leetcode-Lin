package Linked_List.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/20
  *@Describe: 快慢指针找中间结点 , 也可以两次遍历 找 length /2 的位置
 */

import Construct.ListNode;

public class P876_Middle_Linked_List {

    // 偶数链表返回右边的中间结点 100 + 63
    private ListNode middleNode3(ListNode head){
        ListNode slow=head,fast=head;
        while (fast!=null && fast.next!=null ){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    // 偶数链表返回左边的中间结点，返回左边的一般为找中间结点的题
    private ListNode middleNode2(ListNode head){
        if (head==null) return null;
        ListNode slow=head,fast=head.next;
        while (fast!=null && fast.next!=null ){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    // 偶数链表返回左边的中间结点，返回左边的一般为找中间结点的题，这个多用
    public ListNode middleNode(ListNode head) {
        if (head==null) return null;
        ListNode slow=head,fast=head;
        while (fast.next!=null && fast.next.next!=null ){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
}
