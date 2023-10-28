package A1000PLAN.链表.设计.双向链表;

/**
 * desc: 单向链表（简单这里不展示）/   双向链表
 *
 * @author 
 * @since 2023/10/16
 **/
public class P707设计链表 {
    // 节点下标从0开始
    static class MyLinkedList {
        private DListNode head;

        private DListNode tail;

        private int size;

        private class DListNode {
            private int val;

            private DListNode prev;

            private DListNode next;

            public DListNode(int val) {
                this.val = val;
            }
        }

        public MyLinkedList() {
            head = new DListNode(-1);
            tail = new DListNode(-1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public int get(int index) {
            if (index >= size) {
                return -1;
            }
            DListNode cur = head;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
            return cur.val;
        }

        public void addAtHead(int val) {
            DListNode node = new DListNode(val);
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
            size++;
        }

        public void addAtTail(int val) {
            DListNode node = new DListNode(val);
            node.prev = tail.prev;
            tail.prev.next = node;
            node.next = tail;
            tail.prev = node;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }

            DListNode prev = head;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            DListNode cur = new DListNode(val);

            prev.next.prev = cur;
            cur.next = prev.next;
            prev.next = cur;
            cur.prev = prev;

            size++;
        }

        public void deleteAtIndex(int index) {
            if (index >= size) {
                return;
            }
            DListNode prev = head;
            while (index > 0) {
                prev = prev.next;
                index--;
            }
            prev.next = prev.next.next;
            prev.next.prev = prev;
            size--;
        }
    }

    public static void main(String[] args) {
        MyLinkedList my = new MyLinkedList();
        my.addAtHead(1);
        my.addAtTail(3);
        my.addAtIndex(1,2);
        my.get(1);
        my.deleteAtIndex(1);
        my.get(1);
    }
}
