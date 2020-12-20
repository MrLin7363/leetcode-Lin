package Linked_List.easy;

import Construct.ListNode;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/10 20:01
 * @Describe:
 * //思路：两个节点两个节点依次从前到后反转断开遍历，1>2>3>4>5
 * //第一次遍历后是：null <1 2>3>4>5   两个链表 pre =1 ,head=2
 * //第二次遍历后是：  1<2  3>4>5   两个链表 pre =2 ,head=3
 * //第一次遍历是：  1<2<3  4>5
 * //最后：  1<2<3<4<5   头结点指向5   OK
 */
public class Reverse_Linked_List_206 {
    // 三节点迭代反转链表法
    public ListNode reverseList(ListNode head) {
        if (head.next==null) return head;
        ListNode pre=null;
        ListNode next=null;
        while (head!=null){
            //记住下一个结点
            next=head.next;
            //head指向前面，断开结点,第一次是指向null
            head.next=pre;
            //pre记住当前结点
            pre=head;
            //head后移
            head=next;
        }
        return pre;
    }
    /*
     递归版本
     //第一次递归交换后是：  1>2>3>4<5
     */
    public ListNode reverseListRecursion(ListNode head) {
        if (head==null || head.next==null) return head;
        //递归第一次返回是最后一个节点,p是记住最后一个节点，也就是反转链表的头结点，一直不变
        ListNode p = reverseListRecursion(head);
        //这里的head是最后一个节点也就是p的前一个节点
        head.next.next=head;
        head.next=null;
        return p;
    }
}
