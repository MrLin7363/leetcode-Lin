package Linked_List.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/21
  *@Describe:  与第二题区别：数值高位位于链表头
 */

import Construct.ListNode;
import java.util.Deque;
import java.util.LinkedList;

public class P445_Add_Two_Numbers_II {
    // 62 + 82
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1=new LinkedList<>();
        Deque<Integer> stack2=new LinkedList<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2!=null){
            stack2.push(l2.val);
            l2=l2.next;
        }
        int carry=0;
        ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry!=0){
            int v1=stack1.isEmpty()?0:stack1.pop();
            int v2=stack2.isEmpty()?0:stack2.pop();
            int sum=v1+v2+carry;
            carry=sum/10;
            ListNode cur=new ListNode(sum%10);
            cur.next=ans;
            ans=cur;
        }
        return ans;
    }
}
