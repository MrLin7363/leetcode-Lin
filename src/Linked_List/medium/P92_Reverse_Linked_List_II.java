package Linked_List.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/14
  *@Describe:  反转指定区间链表
 */

import Construct.ListNode;

public class P92_Reverse_Linked_List_II {
    /*

     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        ListNode next = null;
        int begin = 0, end = 0;
        while (head != null ) {
            head=head.next;
            begin++;
            end++;
            if (begin==m) {
                ListNode pre = head;
            }
            if (end==n+1){
                break;
            }
        }
        return null;
    }
}
