package Linked_List.easy;

import Construct.ListNode;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/10 20:28
 * @Describe:  移除含有 val的所有节点
 */
public class Remove_Linked_List_Elements_203 {
    //哨兵节点+双指针
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel=new ListNode(0);
        sentinel.next=head;
        ListNode pre=sentinel,cur=head;
        while (cur!=null){
            if (cur.val==val)
                pre.next=cur.next;
            else pre=cur;
            cur=cur.next;
        }
        return sentinel.next;
    }
    //先删除头结点

}
