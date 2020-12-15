package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/6
  *@Describe:
 */

import Construct.TreeNode;
import java.util.HashMap;
import java.util.Map;

public class P105_Construct_BT_Preorder_Inorder_Traversal {
    /*
    递归97 + 37
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 结点值 -> 结点下标
        Map<Integer,Integer> indexMap=new HashMap<>();
        int len=preorder.length;
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i],i);
        }
        return myBuildTree(preorder,inorder,indexMap,0,len-1,0,len-1);
    }
    /**
     *
     * @param preorder
     * @param inorder
     * @param indexMap
     * @param preLeft
     * @param preRight
     * @param inLeft
     * @param inRight
     * @return
     */
    public TreeNode myBuildTree(int[] preorder,int[] inorder,Map<Integer,Integer> indexMap,int preLeft,int preRight,int inLeft,int inRight){
        if (preLeft>preRight || inLeft>inRight) return null;
        // 前序遍历中的第一个节点就是根节点
        TreeNode root=new TreeNode(preorder[preLeft]); // 根节点
        int inRoot=indexMap.get(preorder[preLeft]);// 中序的根节点下标
        int leftSize=inRoot-inLeft;// 左子树的长度
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left=myBuildTree(preorder,inorder,indexMap,preLeft+1,preLeft+leftSize,inLeft,inRoot-1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right=myBuildTree(preorder,inorder,indexMap,preLeft+leftSize+1,preRight,inRoot+1,inRight);
        return root;
    }
}
