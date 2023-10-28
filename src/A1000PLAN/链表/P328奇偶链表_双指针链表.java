package A1000PLAN.链表;

import Construct.ListNode;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/17
 **/
public class P328奇偶链表_双指针链表 {
    /*
    两个双指针链表
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode oddSentinel = new ListNode(-1);
        ListNode evenSentinel = new ListNode(-1);

        boolean isOdd = true;
        ListNode odd = oddSentinel;
        ListNode even = evenSentinel;
        while (head != null) {
            if (isOdd) {
                odd.next = head;
                odd = odd.next;
            } else {
                even.next = head;
                even = even.next;
            }
            isOdd = !isOdd;
            head = head.next;
        }
        even.next = null;
        odd.next = evenSentinel.next;
        return oddSentinel.next;
    }
}
