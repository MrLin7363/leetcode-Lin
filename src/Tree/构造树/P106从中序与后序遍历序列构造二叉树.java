package Tree.构造树;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/6
  *@Describe:
 */

import Construct.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class P106从中序与后序遍历序列构造二叉树 {

    /*
    递归 95 + 26
     */
    Map<Integer, Integer> indexMap = new HashMap<>(); // hashmap 记录中序遍历的下标，好找根节点

    // 根据后序遍历倒序确定根节点 从后往前遍历后序遍历数组的下标标识，因为左右根，所以要先添加add完右子树，才添加左子树
    int index;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i], i);
        }
        index = len - 1;
        return buildTree(postorder, 0, len - 1);
    }

    public TreeNode buildTree(int[] postorder, int inLeft, int inRight) {
        if (inLeft > inRight)
            return null;
        // 选择 index 位置的元素作为当前子树根节点
        int root_val = postorder[index];
        TreeNode root = new TreeNode(root_val);
        // 根据 root 所在位置分成左右两棵子树
        int mid = indexMap.get(root_val);// 中间位置下标
        index--;
        // 先添加右子树
        root.right = buildTree(postorder, mid + 1, inRight);
        root.left = buildTree(postorder, inLeft, mid - 1);
        return root;
    }

    /*
    类似分数组的方法也可以，不过上面的也简单
    左根右  左右根
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        int len = inorder.length;
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode treeNode = buildTree(postorder, indexMap, 0, len - 1, 0, len - 1);
        return treeNode;
    }

    private TreeNode buildTree(int[] postorder, Map<Integer, Integer> indexMap, int inLeft, int inRight, int postLeft,
        int postRight) {
        if (inLeft > inRight || postLeft > postRight) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postRight]);
        int inRoot = indexMap.get(postorder[postRight]);
        int rightSize = inRight - inRoot;
        root.left = buildTree(postorder, indexMap, inLeft, inRoot - 1, postLeft, postRight - rightSize - 1);
        root.right = buildTree(postorder, indexMap, inRoot + 1, inRight, postRight - rightSize, postRight - 1);
        return root;
    }
}
