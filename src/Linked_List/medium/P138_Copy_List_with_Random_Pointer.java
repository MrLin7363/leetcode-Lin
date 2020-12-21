package Linked_List.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/21
  *@Describe:
 */

import java.util.HashMap;
import java.util.Map;

public class P138_Copy_List_with_Random_Pointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    /*
    O 1 迭代  100 + 91
    1.遍历拷贝next相间的结点  A > A' > B > B'
    2.更新新节点的 A'.random = A.random.next
    3. 断开新旧结点next连接
     */
    public Node copyRandomList(Node head) {
        if (head==null) return null;
        Node oldNode=head;
        while (oldNode!=null){
            Node newNode=new Node(oldNode.val);
            newNode.next=oldNode.next;
            oldNode.next=newNode;
            oldNode=newNode.next;
        }
        oldNode=head;
        while (oldNode!=null){
            oldNode.next.random=(oldNode.random!=null)?oldNode.random.next:null;
            oldNode=oldNode.next.next;
        }
        oldNode=head;
        Node newNode=head.next;
        Node newNodeHead=head.next;
        while (oldNode!=null){
            oldNode.next=newNode.next;
            oldNode=oldNode.next;
            newNode.next=(oldNode!=null)?oldNode.next:null;
            newNode=newNode.next;
        }
        return newNodeHead;
    }
    /*
    ------------------------------------------------------------------------------------
    O N迭代  100 + 59
     */
    public Node copyRandomList2(Node head) {
        if (head==null) return null;
        Node oldNode=head;
        // 复制
        Node newNode=new Node(head.val);
        // 标志访问过
        visitedHash.put(oldNode,newNode);
        // 遍历迭代
        while (oldNode!=null){
            newNode.random=getCloneNode(oldNode.random);
            newNode.next=getCloneNode(oldNode.next);

            oldNode=oldNode.next;
            newNode=newNode.next;
        }
        // 取到复制链表头结点
        return visitedHash.get(head);
    }
    public Node getCloneNode(Node node){
        if (node==null) return null;
        if (visitedHash.containsKey(node)){
            return visitedHash.get(node);
        }else{
            Node newNode=new Node(node.val);
            visitedHash.put(node,newNode);
            return newNode;
        }
    }
    /*
    --------------------------------------------------------------------------------------
    递归，链表作为 图   100 + 27
     */
    Map<Node,Node> visitedHash=new HashMap<>();
    public Node copyRandomList3(Node head) {
        if (head==null) return null;
        if (visitedHash.containsKey(head)){
            return visitedHash.get(head); // 直接返回不用新建
        }
        // 复制
        Node node=new Node(head.val);
        // 复制的node结点设置为已经访问过
        visitedHash.put(head,node);
        node.next=copyRandomList3(head.next);
        node.random=copyRandomList3(head.random);
        return node;
    }
}
