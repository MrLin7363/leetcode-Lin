package Linked_List.easy;

import Construct.ListNode;

import java.util.ArrayList;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/10 23:29
 * @Describe:
 */
public class Palindrome_Linked_List_234 {

    /*
    1、快慢指针反转后半数组，快指针到达尾节点，两个指针都向前遍历判断回文，最后再恢复链表
    需要：反转方法,找到中间节点方法
     */
    public boolean isPalindromeByFastSlow(ListNode head) {
        if (head==null) return true;
        //找到中间节点
        ListNode firstHeadEnd=endOfFirstHead(head);
        //反转链表，secondHalfStart为反转后链表头结点
        ListNode secondHalfStart=reverseList(firstHeadEnd.next);
        //用个标志位返回，因为最后还要恢复链表，这里用head代表前半端，因为无需记住head位置，
        // 所以可以直接用，但是还是用个结点p1代表可读性高
        boolean flag=true;
        ListNode p2=secondHalfStart;
        //判断回文,只用判断后半段，因为奇数时，中间节点在前半端
        while (p2!=null && flag){
            if (head.val!=p2.val)
                flag=false;
            head=head.next;
            p2=p2.next;
        }
        //恢复链表，连接前半端最后一个节点和再次反转后的后半端的头结点。这里可以不恢复也可以的，题目没要求
        firstHeadEnd.next=reverseList(secondHalfStart);
        return flag;
    }
    /*
    反转链表
     */
    private ListNode reverseList(ListNode head){
        ListNode pre=null,cur=head;
        while (cur!=null){
            cur=cur.next;
            head.next=pre;
            pre=head;
            head=cur;
        }
        return pre;
    }
    /*
    找中间节点
     */
    private ListNode endOfFirstHead(ListNode head){
        ListNode slow=head,fast=head;
        while (fast.next!=null && fast.next.next!=null ){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }


    /*
    ------------------------------------------------------------------------------
    2、复制到数组链表，然后用双指针
     */
    public boolean isPalindromeByArrayList(ListNode head) {
        ArrayList<Integer> arrayList=new ArrayList<>();
        //复制到数组链表
        while (head!=null){
            arrayList.add(head.val);
            head=head.next;
        }
        //双指针判断
        int i=0,j=arrayList.size()-1;
        //停止条件为i==j
        while (i<j){
            //注意这里要用equals，因为是Integer，不是int
            if (!arrayList.get(i++).equals(arrayList.get(j--)))
                return false;
        }
        return true;
    }

    /*
    ------------------------------------------------------------------------------
    3、递归
     */
    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode!=null){
            //如果返回false,则继续返回false
            if (!recursivelyCheck(currentNode.next)) return false;
            if (frontPointer.val!=currentNode.val) return false;
            frontPointer=frontPointer.next;
        }
        return true;
    }
    public boolean isPalindromeByRecursion(ListNode head) {
        frontPointer=head;
        return recursivelyCheck(head);
    }

}
