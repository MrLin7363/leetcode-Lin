package Linked_List.easy;

import Construct.ListNode;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/30 15:56
 * @Describe:
 */
public class P83_Remove_Duplicates_from_Sorted_List {
    // 迭代
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre=head;
        ListNode end=head;
        while (end.next!=null){
            end=end.next;
            //有重复元素了
            if (end.val==pre.val){
                //删除重复元素，断开连接
                pre.next=end;
            }else{
                pre=end;
            }
        }
        return head;
    }
}
