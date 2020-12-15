package Linked_List.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/15
  *@Describe: 移除所有重复的结点
 */

import Construct.ListNode;

public class P82_Rm_Duplicates_Sorted_List_II {
    /*
    哨兵结点+ 前驱结点   100 + 15
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel=new ListNode(0,head);
        ListNode prev=sentinel;
        while (head!=null){
            if (head.next!=null && head.val==head.next.val) {
                // 遍历相同值的一段
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // 跳过这段相同的
                prev.next=head.next;
            }else{
                prev=prev.next;
            }
            head=head.next;
        }
        return sentinel.next;
    }
}
