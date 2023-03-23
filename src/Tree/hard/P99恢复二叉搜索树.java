package Tree.hard;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/11
  *@Describe:
 */

import Construct.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P99恢复二叉搜索树 {
    /*
    显性中序遍历，一个数组存储所有的结点
    数组 + 递归中序    34 + 20
     */
    public void recoverTree2(TreeNode root) {
        List<Integer> nums=new ArrayList<>();
        nums=getInorder(root,nums);
        int[] swap=findTwoSwapped(nums);
        recover(root,2,swap[0],swap[1]);
    }
    // 输出中序遍历序列数组
    public List<Integer> getInorder(TreeNode root, List<Integer> nums){
        if (root==null) return null;
        getInorder(root.left,nums);
        nums.add(root.val);
        getInorder(root.right,nums);
        return nums;
    }
    // 寻找错误的结点，树只有两个错误的结点
    public int[] findTwoSwapped(List<Integer> nums){
        int n=nums.size();
        int x=-1,y=-1;
        for (int i = 0; i < n-1; i++) {
            if (nums.get(i)>=nums.get(i+1)){
                y=nums.get(i+1);// 找到 x >=结点中 最大的结点，交换
                if (x==-1){
                    x=nums.get(i);
                }else{
                    break;
                }
            }
        }
        return new int[]{x,y};
    }

    // 交换两个结点值
    public void recover(TreeNode root,int count,int x, int y ){
        if (root!=null){
            if (root.val==x || root.val==y){
                root.val= root.val==x ? y : x;
                if (--count==0){ // 两次交换结束
                    return;
                }
            }
        recover(root.left,count,x,y);
        recover(root.right,count,x,y);
        }
    }

    /*
    -------------------------------------------------------------------------------------------------
    隐性中序遍历   两个值 + 迭代   61 + 40
     */
    public void recoverTree3(TreeNode root) {
        Deque<TreeNode> stack=new ArrayDeque<>();
        TreeNode x=null, y=null, pre=null;
        while (!stack.isEmpty() || root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if (pre!=null && pre.val>root.val){
                y=root;
                if (x==null){
                    x=pre;
                }
            }
            pre=root;
            root=root.right;
        }
        // 交换结点
        swap(x,y);
    }
    public void swap(TreeNode x,TreeNode y){
        int temp=x.val;
        x.val=y.val;
        y.val=temp;
    }

    /*
    -------------------------------------------------------------------------------------------------
    Mirror  87 + 87
     */
    public void recoverTree(TreeNode root) {
        TreeNode x=null,y=null,pre=null,predecessor=null;
        while (root!=null){
            if (root.left!=null){
                predecessor=root.left;
                while (predecessor.right!=null && predecessor.right!=root){
                    predecessor=predecessor.right;
                }
                if (predecessor.right==null){
                    predecessor.right=root;
                    root=root.left;
                }else{
                    if (pre!=null && pre.val>root.val){
                        y=root;
                        if (x==null){
                            x=pre;
                        }
                    }
                    pre=root;
                    predecessor.right=null;
                    root=root.right;
                }
            }else{
                if (pre!=null && pre.val>root.val){
                    y=root;
                    if (x==null){
                        x=pre;
                    }
                }
                pre=root;
                root=root.right;
            }
        }
        swap(x,y);
    }

}
