package Linked_List.hard;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/12
  *@Describe:
 */

import Construct.ListNode;

public class P23_Merge_k_Sorted_Lists {
    /*

     */

    /*
    分治合并 100 + 46
     */
    public ListNode mergeKLists(ListNode[] lists) {
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
