package Linked_List.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/14
  *@Describe:  反转指定区间链表
 */

import Construct.ListNode;

public class P92_Reverse_Linked_List_II {
    /*
    递归: 注意左指针全局，右指针回溯作为局部变量 100 + 64
     */
    private boolean stop;
    private ListNode left;
    public ListNode reverseBetween(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

    public void recurseAndReverse(ListNode right, int m, int n) {
        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }
        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;
        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }
        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);
        /*  1 > 2 > 3 > 4 > 5 > 6
            left = 3 , right = 5
            1 > 2 > 5 > 4 > 3 > 6  left =4,right =4 停止交换数据
         */
        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this point.
        //  right.next == this.left 指两个结点交换的时候， 交换后回溯时，left是全局变量，right是局部变量会变
        // 回溯时就是 两个指针向中间交换结点的意思
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }
        // 交换完停止数据交换
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;
            // 全局左指针右移，右指针回溯的时候左移
            this.left = this.left.next;
        }
    }



    /*
    迭代反转，先找到前驱结点，在反转剩余的节点数
    100 + 31
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        // 开始反转的结点
        ListNode cur = head, prev = null;
        while (m>1){
            prev=cur;
            cur=cur.next;
            m--;
            n--;
        }
        // 1 - 2 - 3 - 4 - 5   翻 3 , 4
        // 1 > 2 < 3 > 4 > 5
        // 1> 2 < 3 < 4 > 5
        // 1 > 2 > 4 > 3 > 5
        // con = prev = 2 , cur= tail = 3
        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;  // 用于最后连接 3 < 4 的反转
        // 三节点反转链表法
        ListNode third=null;
        while (n>0){  // 只需要反转剩下的个数
            third=cur.next;
            cur.next=prev;
            prev=cur;
            cur=third;
            n--;
        }
        // Adjust the final connections as explained in the algorithm
        if (con!=null){
            con.next = prev;
        }else{
            head=prev;// 只有一个结点的情况下
        }
        tail.next = cur;
        return head;
    }


}
