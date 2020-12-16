package Linked_List.medium;

import Construct.ListNode;
import utils.ListUtil;

/**
 * @author: Junlin Chen
 * @Date: 2020/12/15 23:28
 * @Describe:
 */
public class P86_Partition_List {
    /*
    用两个链表记录再连接  100 + 19
     */
    public ListNode partition(ListNode head, int x) {
        ListNode first_head=new ListNode(0);
        ListNode second_head=new ListNode(0);
        ListNode first_tail=first_head;
        ListNode second_tail=second_head;
        while (head!=null){
            if (head.val<x){
                first_tail.next=head;
                first_tail=head; //  first_tail=first_tail.next; 也行
            }else{
                second_tail.next=head;
                second_tail=head;
            }
            head=head.next;
        }
        //本来是 5 > 2 最后 5 作为尾结点要断开，否则是环
        second_tail.next=null;
        // 连接两个链表
        first_tail.next=second_head.next;
        return first_head.next;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(4);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(2);
        head.next.next.next.next=new ListNode(5);
        head.next.next.next.next.next=new ListNode(2);
        P86_Partition_List s=new P86_Partition_List();
        ListUtil.printlnList(s.partition(head,3));
    }

}
