package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/28
  *@Describe: 有序链表转平衡二叉搜索树
  值类型存的是数据本身。引用类型存的是地址，地址指向真实数据。
  注意这个地址值是一个数。假设head = 0x1. globalHead = head 之后。globalHead = 0x1, 再然后head = 0x2, globalHead 还是 0x1。
 */

import Construct.ListNode;
import Construct.TreeNode;


public class P109ConvertSortedList_BST {
    ListNode globalHead;
    // 分治+中序  31+5
    public TreeNode sortedListToBST2(ListNode head) {
         globalHead=head;
        int len=getLength(head);
        return buildTree2(0,len-1);
    }
    private int getLength(ListNode head){
        int res=0;
        while (head!=null){
            head=head.next;
            res++;
        }
        return res;
    }
    private TreeNode buildTree2(int left,int right){
        if (left>right) return null;
        int mid=(left+right+1)/2;
        TreeNode root=new TreeNode();
        // 先建立树形结构，再依次赋值，这样链表的不用每次都循环
        root.left=buildTree2(left,mid-1);
        // 中序遍历操作
        root.val=globalHead.val;
        globalHead=globalHead.next;
        root.right=buildTree2(mid+1,right);
        return root;
    }


        // 分治 -递归 31+ 9        不好的地方是链表找中间点效率不如二分数组高
    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head,null); // null 可以代表链表的结尾
    }
    private TreeNode buildTree(ListNode left, ListNode right){
        if (left==right)
            return null;
        ListNode mid=getMedian(left,right);
        TreeNode root=new TreeNode(mid.val);
        root.left=buildTree(left,mid); // [left,mid)
        root.right=buildTree(mid.next,right); // [mid+1,right)  mid已经被选过了
        return root;
    }
    // 快慢指针获取中间节点
    private ListNode  getMedian(ListNode left, ListNode right){
        ListNode slow=left;
        ListNode fast=left;
        while (fast!=right && fast.next!=right){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
}
