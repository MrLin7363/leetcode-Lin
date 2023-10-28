package A1000PLAN.链表.删除;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/16
 **/
public class P237删除链表中的节点 {
    /**
     * node是链表中的一个节点，无法访问head节点
     * 思路：交换下一个节点值，然后删除下一个节点
     * 4>5>1>9 删除5  4>1>1>9   4>1>9
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
