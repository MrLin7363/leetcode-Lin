package A1000PLAN.链表.排序相关;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/26
 **/
public class P148排序链表 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        // ListNode listNode = new P148排序链表().sortList(head);
        ListNode listNode2 = new P148排序链表().sortList2(head);
        System.out.println(listNode2);
    }

    /*
    1. 分治思想：归并排序,递归自顶向下 O(logn)空间复杂度
    归并+合并两个有序链表+快慢指针找中点

    分割 cut 环节： 找到当前链表 中点，并从 中点 将链表断开（以便在下次递归 cut 时，链表片段拥有正确边界）
    我们使用 fast,slow 快慢双指针法，奇数个节点找到中点，偶数个节点找到中心左边的节点。
    找到中点 slow 后，执行 slow.next = None 将链表切断。
    递归分割时，输入当前链表左端点 head 和中心节点 slow 的下一个节点 tmp(因为链表是从 slow 切断的)
    cut 递归终止条件： 当 head.next == None 时，说明只有一个节点了，直接返回此节点
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 找中间结点
        ListNode mid = findMiddleNode(head);
        // 断开链表
        ListNode next = mid.next;
        mid.next = null;
        // 头结点和中间结点next为两段
        ListNode leftNode = sortList(head);
        ListNode rightNode = sortList(next);
        // 合并两个有序链表
        return mergeTwoLists(leftNode, rightNode);
    }

    private ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*
    2.自底向上，O(1)的空间复杂度 - 推荐记录，经常考第二种方法
        1.计算链表总长
        2.以subLength=1开始分割排序链表，每次分割完继续以subLength*2去分割；知道subLength==length
        两两合并
        3.分割过程，拆分两个subLength子链+剩下的链表， 两两合并，接着继续找下两个合并,
            过程将合并的链表最终合成1条链表，下一次再以subLength*2去排序合并
            [4,1,3,2] -> [1,4,2,3](subLength==1) -> [1,2,3,4](subLength==2)
     */
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode count = head;
        int length = 0;
        while (count != null) {
            count = count.next;
            length++;
        }

        // 两两合并
        ListNode sentinel = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = sentinel; // 记录每轮合并后的开始位置
            ListNode cur = prev.next; // 记录每轮合并后的下一个位置
            // 每次while两两合并subLength的子链表
            while (cur != null) {
                // 1.找第一个链表首尾结点，node1第一个链表开始结点，cur尾结点
                ListNode node1 = cur;
                for (int i = 1; i < subLength && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }

                // 2.找第二个链表首尾结点，node2第二个链表开始结点，cur尾结点
                ListNode node2 = cur.next;
                // 断开第一个子链表与后面链表
                cur.next = null;
                cur = node2;
                for (int i = 1; i < subLength && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }

                // 3.断开第二个链表与后面联系, 此时cur可能为null,如剩余只能拆分为1个链表的情况
                if (cur != null) {
                    ListNode next = cur.next;
                    cur.next = null;
                    cur = next;
                }
                // 4.合并一二链表
                ListNode merge = mergeTwoLists(node1, node2);
                // 5.将前面合并的链表和后面的链表连接起来
                prev.next = merge;
                // 移动prev到合并后链表的尾结点，便于下一次连接
                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }
        return sentinel.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode sentinel = new ListNode();
        ListNode cur = sentinel;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return sentinel.next;
    }
}
