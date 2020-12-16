package Linked_List.medium;

import Construct.ListNode;

/**
  *@Author JunLin
  *@Date 2020/12/16
  *@Describe: 旋转链表，右移 k 个
 */

public class P61_Rotate_List {
    /*
    一次遍历完链表，找到长度，并连接链表头，然后找到要断开的 join点，join 结点后的放到表头 100 + 49
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null || head.next==null) return head;
        ListNode old_tail=head;
        int n=1;
        while (old_tail.next!=null){
            old_tail=old_tail.next;
            n++;
        }
        // close the linked list into the ring
        old_tail.next = head;

        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        ListNode new_tail = head;
        for (int i = 0; i < n - k % n - 1; i++) //  n - ( k % n) -1
            new_tail = new_tail.next;
        ListNode new_head = new_tail.next;
        // break the ring
        new_tail.next = null;
        return new_head;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        P61_Rotate_List s=new P61_Rotate_List();
        System.out.println(s.rotateRight(head,2));
    }
}
