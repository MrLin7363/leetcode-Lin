package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/6
  *@Describe:
 */

import Construct.TreeNode;
import java.util.HashMap;
import java.util.Map;

public class P106_Construct_BT_Postorder_Inorder_Traversal {

    /*
    递归 95 + 26
     */
    Map<Integer,Integer> indexMap=new HashMap<>(); // hashmap 记录中序遍历的下标，好找根节点
    //  从后往前遍历后序遍历数组的下标标识，因为左右根，所以要先添加add完右子树，才添加左子树
    int index;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len=inorder.length;
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i],i);
        }
        index=len-1;
        return buildTree(inorder,postorder,0,len-1);
    }
    /**
     * @param inorder
     * @param postorder
     * @param inLeft
     * @param inRight
     * @return
     */
    public TreeNode buildTree(int[] inorder,int[] postorder,int inLeft,int inRight){
        if (inLeft>inRight) return null;
        // 选择 index 位置的元素作为当前子树根节点
        int root_val=postorder[index];
        TreeNode root=new TreeNode(root_val);
        // 根据 root 所在位置分成左右两棵子树
        int mid=indexMap.get(root_val);// 中间位置下标
        index--;
        // 先添加右子树
        root.right=buildTree(inorder,postorder,mid+1,inRight);
        root.left=buildTree(inorder,postorder,inLeft,mid-1);
        return root;
    }
}
