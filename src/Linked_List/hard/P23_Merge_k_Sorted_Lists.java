package Linked_List.hard;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/12
  *@Describe:
 */

import Construct.ListNode;
import java.util.PriorityQueue;

public class P23_Merge_k_Sorted_Lists {
    /*
    自定义优先队列  20 + 5
    队列比较的是每一个链表的头结点，依次将最小的用新的链表串起来
     */
    static class Status implements Comparable<Status>{
        int val;
        ListNode node;

        public Status(int val, ListNode node) {
            this.val = val;
            this.node = node;
        }
        // 优先队列会根据这个去找最小的先出队列
        @Override
        public int compareTo(Status status) {
            return this.val-status.val;
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Status> queue=new PriorityQueue<>();
//        PriorityQueue<ListNode> queue=new PriorityQueue<>((x,y)-> x.val-y.val);
        for(ListNode node:lists){
            if (node!=null){
                queue.offer(new Status(node.val,node));
            }
        }
        ListNode head=new ListNode(-1); // 结果数组哨兵
        ListNode tail=head;
        while (!queue.isEmpty()){
            Status minFirst=queue.poll(); // 头结点最小的 链表
            tail.next=minFirst.node; // 将头结点放进结果数组
            tail=tail.next;
            if (minFirst.node.next!=null){
                queue.offer(new Status(minFirst.node.next.val,minFirst.node.next)); // 将该链表的下一个链表放入队列
            }
        }
        return head.next;
    }

    /*
    分治迭代  80  +  90
     */
    public ListNode mergeKLists4(ListNode[] lists) {
        int len=lists.length;
        if (len==0) return null;
        // <<1 是两倍
        for (int step = 1; step < len; step<<=1) {
            for (int i = step; i < len; i+=step<<1) {
                // 如 step ==2 时， 交换的是 0-2 4-6 8-10
                lists[i-step]=mergeTwoLists(lists[i],lists[i-step]);
            }
        }
        return lists[0];
    }
    /*
    分治合并 100 + 46
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists,0,lists.length);
    }
    public ListNode merge(ListNode[] lists,int l,int r){
        if (l==r) return lists[l];
        if (l>r) return null;
        int mid=(l+r)>>1; // /2的意思
        return mergeTwoLists(merge(lists,l,mid), merge(lists,mid+1, r ) );
    }
    /*
    顺序合并  11 + 79
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        ListNode ans=new ListNode();
        for (int i = 0; i < lists.length; i++) {
            ans=mergeTwoLists(lists[i],ans);
        }
        return ans;
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //注意加条件
        if (l1==null ||l2==null)
            return l1!=null ? l1 : l2;
        ListNode sentinel = new ListNode(-1);
        ListNode prev = sentinel;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return sentinel.next;
    }
}
