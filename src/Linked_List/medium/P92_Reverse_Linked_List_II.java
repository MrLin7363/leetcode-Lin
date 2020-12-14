package Linked_List.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/14
  *@Describe:  反转指定区间链表
 */

import Construct.ListNode;

public class P92_Reverse_Linked_List_II {
    /*
    迭代反转，先找到前驱结点，在反转剩余的节点数
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        ListNode cur = head, prev = null;
        while (m>0){
            prev=cur;
            cur=cur.next;
            m--;
            n--;
        }
        ListNode con = prev, tail = cur;

        return null;
    }
}
