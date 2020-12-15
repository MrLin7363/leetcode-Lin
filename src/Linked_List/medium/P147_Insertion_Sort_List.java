package Linked_List.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/12
  *@Describe: 链表排序
 */

import Construct.ListNode;

public class P147_Insertion_Sort_List {
    /*
    插入排序  99 + 15
     */
    public ListNode sortList(ListNode head) {
        if (head==null) return null;
        ListNode sentinel=new ListNode(-1);
        sentinel.next=head;
        ListNode lastSorted=head;
        ListNode cur=head.next;
        while (cur!=null){
            if (lastSorted.val<=cur.val){
                lastSorted=lastSorted.next;
            }else{
                ListNode prev=sentinel;
                while (prev.next.val<=cur.val){
                    prev=prev.next;
                }
                lastSorted.next=cur.next;
                cur.next=prev.next;
                prev.next=cur;
            }
            cur=lastSorted.next;
        }
        return sentinel.next;
    }
}
