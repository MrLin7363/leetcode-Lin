package Linked_List.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/21
  *@Describe:
 */

import Construct.ListNode;

public class P2_Add_Two_Numbers {
    // 100 + 47
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head=null,tail=null;
        int carry=0;
        while (l1!=null || l2!=null){
            int v1=l1!=null?l1.val:0;
            int v2=l2!=null?l2.val:0;
            int sum=v1+v2+carry;
            if (head==null){ // 第一个数
                head=tail=new ListNode(sum%10);
            }else{
                tail.next=new ListNode(sum%10);
                tail=tail.next;
            }
            if (l1!=null){
                l1=l1.next;
            }
            if (l2!=null){
                l2=l2.next;
            }
            carry=sum/10;
        }
        if (carry>0){
            tail.next=new ListNode(carry);
        }
        return head;
    }
}
