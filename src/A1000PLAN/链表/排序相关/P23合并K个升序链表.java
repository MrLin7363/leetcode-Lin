package A1000PLAN.链表.排序相关;

import Construct.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * desc:    1.顺序合并
 * 2.归并合并-推荐
 * 3.堆-推荐：存每个节点值，依次加入一个新list
 *
 * @author 
 * @since 2023/9/27
 **/
public class P23合并K个升序链表 {
    /*
    2.归并合并
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left > right) { // 没有元素的情况
            return null;
        }
        if (left < right) {
            int mid = left + (right - left) / 2; // 偶数偏左
            ListNode leftNode = merge(lists, left, mid);
            ListNode rightNode = merge(lists, mid + 1, right);
            return mergeTwoLists(leftNode, rightNode);
        }
        // 只有一个元素，不需要排序合并
        return lists[left];
    }

    // 合并两个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode sentinel = new ListNode(0);
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
        // 剩下的拼接上
        cur.next = list1 == null ? list2 : list1;
        return sentinel.next;
    }

    /*
    1.顺序合并-最低性能
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    /*
    3.堆：存每个链表第一个节点值，依次加入一个新list,这样不会形成环
    注意错误：一次存所有结点值：如果值相同，如-2,-1,-1那可能堆出队是 0,2,1索引，而1索引的-1下一个是-1，形成环
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        // 加入每个链表首结点
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        ListNode sentinel = new ListNode(0);
        ListNode cur = sentinel;
        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null) {
                queue.add(cur.next);
            }
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(0);
        ListNode[] lists = new ListNode[2];
        lists[0] = head;
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        lists[1] = head2;
        new P23合并K个升序链表().mergeKLists(lists);
    }
}
