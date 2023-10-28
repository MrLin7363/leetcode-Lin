package A1000PLAN.链表.排序相关;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/7
 **/
public class P147对链表进行插入排序 {
    /*
    插入排序 先找它 - 临时保存它 - 删掉它 - 给它找位置 - 插入它
    https://leetcode.cn/problems/insertion-sort-list/solutions/491483/wei-tu-jie-147dui-lian-biao-jin-xing-cha-ru-pai-xu/
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode sentinel = new ListNode(0, head);
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.next.val >= cur.val) { // 符合递增的逻辑
                cur = cur.next;
            } else {
                // 临时保存要插入点
                ListNode temp = cur.next;
                // 删除节点
                cur.next = cur.next.next;

                // 找要插入的位置
                ListNode prev = sentinel;
                while (temp.val > prev.next.val) {
                    prev = prev.next;
                }

                // 插入
                temp.next = prev.next;
                prev.next = temp;
            }
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode listNode = new P147对链表进行插入排序().insertionSortList(head);
        System.out.println(listNode);
    }
}
