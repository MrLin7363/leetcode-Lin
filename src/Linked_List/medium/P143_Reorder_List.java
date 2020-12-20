package Linked_List.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/20
  *@Describe: 最后一个插第二个，倒数第二个插第三个，间隔插入
 */

import Construct.ListNode;
import java.util.ArrayList;
import java.util.List;

public class P143_Reorder_List {
    /*
    链表中点 + 逆序 + 交叉合并  99 + 55
     */
    public void reorderList(ListNode head) {
        if (head==null) return;
        ListNode mid=getMidNode(head);
        ListNode list1=head;
        ListNode list2=mid.next;
        mid.next=null;
        list2=reverseList(list2);
        mergeList(list1, list2);
    }
    public void mergeList(ListNode l1,ListNode l2) {
        ListNode l1_temp;
        ListNode l2_temp;
        while (l1 != null && l2 != null) {
            l1_temp = l1.next;
            l2_temp = l2.next;

            l1.next=l2;
            l2.next=l1_temp;

            l1=l1_temp;
            l2=l2_temp;
        }
    }
    public ListNode reverseList(ListNode head){
        ListNode pre=null,next=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }
    public ListNode getMidNode(ListNode head){
        ListNode fast=head,slow=head;
        while (fast.next!=null && fast.next.next!=null ){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    /*
    线性插入：将链表转化为有下标的数组，交叉合并  17 + 91
     */
    public void reorderList2(ListNode head) {
        if (head==null) return;
        List<ListNode> list=new ArrayList<>();
        ListNode node=head;
        while (node!=null){
            list.add(node);
            node=node.next;
        }
        int i=0,j=list.size()-1;
        while (i<j){
            list.get(i).next=list.get(j);
            i++;
            if (i==j){
                break;
            }
            list.get(j).next=list.get(i);
            j--;
        }
        list.get(i).next=null;
    }
}
