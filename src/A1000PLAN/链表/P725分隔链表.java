package A1000PLAN.链表;

import Construct.ListNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/21
 **/
public class P725分隔链表 {
    /*
    先添加基础个数，再逐个添加剩下的
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        // 每一组的基础个数
        int basicCount = size / k;
        // 剩余的个数
        int remainder = size % k;
        // 前 remainder个部分的长度各为 basicCount+1，其余每个部分的长度各为 basicCount
        ListNode[] res = new ListNode[k];
        cur = head;
        for (int i = 0; i < k && cur != null; i++) {
            int partSize = i < remainder ? basicCount + 1 : basicCount;
            res[i] = cur;
            for (int j = 1; j < partSize; j++) {
                cur = cur.next;
            }
            // 断开一整段
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        // ListNode head4 = new ListNode(4);
        // ListNode head5 = new ListNode(5);
        // ListNode head6 = new ListNode(6);
        // ListNode head7 = new ListNode(7);
        // ListNode head8 = new ListNode(8);
        // ListNode head9 = new ListNode(9);
        // ListNode head10 = new ListNode(10);
        head.next = head2;
        head2.next = head3;
        // head3.next = head4;
        // head4.next = head5;
        // head5.next = head6;
        // head6.next = head7;
        // head7.next = head8;
        // head8.next = head9;
        // head9.next = head10;

        ListNode[] listNodes = new P725分隔链表().splitListToParts(head, 5);

        System.out.println(listNodes);
    }
}
