package Linked_List.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/15
  *@Describe:
 */

import Construct.ListNode;
import java.util.Deque;
import java.util.LinkedList;

public class P19_Rm_Nth_Node_From_End_List {
    /*
    双指针确定倒数第几个结点 100 + 9
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel=new ListNode(0,head);
        ListNode first=head;
        ListNode second=sentinel;
        // 第n个结点
        for (int i = 0; i < n; i++) {
            first=first.next;
        }
        // 确定倒数第n个结点
        while (first!=null){
            first=first.next;
            second=second.next;
        }
        second.next=second.next.next;
        return sentinel.next;
    }

    /*
     栈   10 + 22   -----------------------------------------
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy=new ListNode(0,head);
        Deque<ListNode> stack=new LinkedList<>();
        ListNode cur=dummy;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        // ++ i 和 i ++ 一样，都是算完加，这里最后一个也要先出队一个
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next=prev.next.next;
        return  dummy.next;
    }

    /*
    计算链表长度，再顺序遍历 -----------------------------------------
    100 + 44
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy=new ListNode(0,head); // 哨兵结点，哑结点
        int length=getLength(head);
        ListNode cur=head;
//        ListNode prev=dummy; // 遍历到要删除结点的前面一个结点，不需要前驱结点
        for (int i = 1; i < length-n+1; i++) {
            cur=cur.next;
        }
        cur.next=cur.next.next;
        return dummy.next;
    }
    private int getLength(ListNode head){
        int count=0;
        while (head!=null){
            head=head.next;
            count++;
        }
        return count;
    }
    /*
     DFS回溯：自己写的递归100 + 13，        -----------------------------------------
     先递归到最后，每次回溯都会减一，回溯到结点时删除该结点
     100 + 17
     */
    int n;
    public ListNode removeNthFromEnd4(ListNode head, int n) {
        ListNode prev=new ListNode(0,head);
        this.n=n;
        deleteRecursion(prev,prev);
        return prev.next;
    }
    public void deleteRecursion(ListNode node,ListNode prev){
        if (node==null) return;
        prev=node;
        ListNode cur=node.next;
        deleteRecursion(cur,prev);
        // 断开连接，删除该结点
        if (n==0){
            prev.next=cur.next;
        }
        n--;
    }
    /*
    递归改进版，不需要perv 100 + 41
     */
    public ListNode removeNthFromEnd5(ListNode head, int n) {
        ListNode prev=new ListNode(0,head);
        this.n=n;
        deleteRecursion5(prev);
        return prev.next;
    }
    public void deleteRecursion5(ListNode node ){
        if (node==null) return;
        ListNode cur=node;
        deleteRecursion5(cur.next);
        // 断开连接，删除该结点
        if (n==0){
            cur.next=cur.next.next;
        }
        n--;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        P19_Rm_Nth_Node_From_End_List s=new P19_Rm_Nth_Node_From_End_List();
        System.out.println(s.removeNthFromEnd2(head,1));
    }



}
