package Tree.构造树;

import Construct.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class P105从前序与中序遍历序列构造二叉树 {
    /*
    递归97 + 37  两个数组的长度是一样的
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 结点值 -> 结点下标
        Map<Integer, Integer> indexMap = new HashMap<>();
        int len = preorder.length;
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, indexMap, 0, len - 1, 0, len - 1);
    }

    public TreeNode myBuildTree(int[] preorder, Map<Integer, Integer> indexMap, int preLeft, int preRight,
        int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight)
            return null;
        // 前序遍历中的第一个节点就是根节点
        TreeNode root = new TreeNode(preorder[preLeft]); // 根节点
        int inRoot = indexMap.get(preorder[preLeft]);// 中序的根节点下标
        int leftSize = inRoot - inLeft;// 左子树的长度
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, indexMap, preLeft + 1, preLeft + leftSize, inLeft, inRoot - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, indexMap, preLeft + leftSize + 1, preRight, inRoot + 1, inRight);
        return root;
    }

    /**
     * 根据前序遍历顺序确定根节点
     * 第二种方法，用一个index记录从左到右 前序遍历的位置, 一个根一个根地添加，先左子树再右子树
     */
    private int index = 0;

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        // 记录头节点
        int len = preorder.length;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(preorder, indexMap, 0, len - 1);
    }

    private TreeNode buildTree(int[] preorder, Map<Integer, Integer> indexMap, int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[index]);
        int mid = indexMap.get(preorder[index]);
        index++;
        root.left = buildTree(preorder, indexMap, inLeft, mid - 1);
        root.right = buildTree(preorder, indexMap, mid + 1, inRight);
        return root;
    }
}
