package Design.medium;
    
/**
  *@Author JunLin
  *@Date 2020/12/16
  *@Describe:
   LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

import Construct.DLinkedNode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class P146_LRU_Cache extends LinkedHashMap<Integer,Integer> {
    // 链表+哈希   19 + 6
    class LRUCache {
        private Map<Integer, DLinkedNode> cathe=new HashMap<>();
        private int size; // 目前装入的容量
        private int capacity; // 双链表大小
        private DLinkedNode head,tail;  // 哨兵结点/伪头部结点和伪尾部结点
        public LRUCache(int capacity) {
            this.size=0;
            this.capacity=capacity;
            // 使用伪头部和伪尾部节点
            head=new DLinkedNode();
            tail=new DLinkedNode();
            head.next=tail;
            tail.prev=head;
        }

        public int get(int key) {
            // 如果 key 存在，先通过哈希表定位，再移到头部
            DLinkedNode node=cathe.get(key);
            if (node==null){
                return -1;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node=cathe.get(key);
            if (node==null){
            // 如果 key 不存在，创建一个新的节点
                DLinkedNode newNode=new DLinkedNode(key,value);
                // 加入hash表
                cathe.put(key,newNode);
                // 添加至双向链表的头部
                addToHead(newNode);
                // 如果链表满了
                size++;
                if (size>capacity){
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail= removeTail();
                    // 删除哈希表中对应的项
                    cathe.remove(tail.key);
                    size--;
                }
            // 结点添加到头部
            }else{
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                node.value=value;
                moveToHead(node);
            }
        }
        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(DLinkedNode node){
            // 移除链表
            removeNode(node);
            // 添加到头部，注意头部有一个哨兵结点
            addToHead(node);
        }
        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }
}
