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
    100 + 31
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        // 开始反转的结点
        ListNode cur = head, prev = null;
        while (m>1){
            prev=cur;
            cur=cur.next;
            m--;
            n--;
        }
        //  1 - 2 - 3 - 4 - 5   翻 3 , 4
        // 1 > 2 < 3 > 4 > 5
        // 1> 2 < 3 < 4 > 5
        // 1 > 2 > 4 > 3 > 5
        // con = prev = 2 , cur= tail = 3
        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;  // 用于最后连接 3 < 4 的反转
        // 三节点反转链表法
        ListNode third=null;
        while (n>0){  // 只需要反转剩下的个数
            third=cur.next;
            cur.next=prev;
            prev=cur;
            cur=third;
            n--;
        }
        // Adjust the final connections as explained in the algorithm
        if (con!=null){
            con.next = prev;
        }else{
            head=prev;// 只有一个结点的情况下
        }
        tail.next = cur;
        return head;
    }


}
