package Construct;
    
/**
  *@Author JunLin
  *@Date 2020/12/17
  *@Describe: 自定义的双链表
 */

public class DLinkedNode {
    public int key;
    public int value;
    public DLinkedNode prev;
    public DLinkedNode next;

    public DLinkedNode() {}

    public DLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
