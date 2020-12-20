package Linked_List.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/20
  *@Describe: 相交链表
 */

import Construct.ListNode;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P160_Intersection_Two_Lists {
    /*
    双指针
    1. 同时遍历到尾结点， 如果尾结点不同，则不相交
    2. 互相交换到对方链表的头结点，再遍历到相同的位置
    97 + 82
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA==null || headB==null) return null;
        ListNode curA=headA,curB=headB;
        while (curA!=curB){
            curA=curA.next;
            curB=curB.next;
            if (curA==null&&curB==null){
                return null; // 如果不相交，第二次也会同时到达尾结点
            }
            if (curA==null){
                curA=headB;
            }
            if (curB==null){
                curB=headA;
            }
        }
        return curA;
    }
    /*
    哈希 26 + 25
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> set=new HashSet<>();
        while (headA!=null){
            set.add(headA);
            headA=headA.next;
        }
        while (headB!=null)
        {
            if (set.contains(headB)){
                return headB;
            }
            headB=headB.next;
        }
        return null;
    }
}
