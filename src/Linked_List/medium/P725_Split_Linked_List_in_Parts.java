package Linked_List.medium;

import Construct.ListNode;

/**
 * @author: Junlin Chen
 * @Date: 2020/12/21 22:48
 * @Describe:
 */
public class P725_Split_Linked_List_in_Parts {
    // 直接拆分链表 100 + 32
    public static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode node=root;
        int len=0;
        while (node!=null){
            node=node.next;
            len++;
        }
        int width=len/k;
        int rem=len%k;
        ListNode[] ans=new ListNode[k];
        node=root;
        for (int i = 0; i < k; i++) {
            ListNode head=node;
            // 如果节点个数小于要分割的个数，说明默认前面的分隔1，后面的null，这一步移动指针跳过
            // width + (i < rem ? 1 : 0) - 1; 让小于余数的前面的节点多分配一个 ， rem 永远小于 k
            // 如10个节点 k = 3 , 那么三个 链表个数分别是  4，3,3
            for (int j = 0; j < width + (i < rem ? 1 : 0) - 1; j++) {
                if (node!=null){
                    node=node.next;
                }
            }
            if (node!=null){
                ListNode prev = node;
                node = node.next;
                prev.next = null;
            }
            ans[i]=head;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
//        head.next.next.next=null;
//        head.next.next.next.next=null;
        System.out.println(splitListToParts(head,5));
    }
}
