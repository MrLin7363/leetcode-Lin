package Linked_List.medium;
    
/**
  *@Author JunLin
  *@Date 2020/12/21
  *@Describe:
 */

import Construct.ListNode;

public class P328_Odd_Even_Linked_List {

    /*
     分离合并 ：将奇偶结点分开连接，最后再拼接两条链
     100 + 36
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return head;
        ListNode evenHead = head.next;
        ListNode evenNode = evenHead;
        ListNode oddNode = head;
        // 断开：结束条件为 oddNode 是尾结点
        while (evenNode != null && evenNode.next != null) {
            oddNode.next = evenNode.next;
            oddNode = oddNode.next;
            evenNode.next = oddNode.next;
            evenNode = evenNode.next;
        }
        // 拼接
        oddNode.next = evenHead;
        return head;
    }

}
