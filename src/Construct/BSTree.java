package Construct;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/18
  *@Describe: 自定义的BST，用于设计题 705
  搜索BST，添加BST ，删除BST  P700  P701  P450 leetcode  简单不用做 这里全部复现
 */
public class BSTree {
    public TreeNode root=null;

    public TreeNode getRoot() {
        return root;
    }

    // 搜索
    public TreeNode searchBST(TreeNode root, int val) {
        if (root==null || val==root.val){
            return root;
        }
        return root.val>val ? searchBST(root.left,val) : searchBST(root.right,val);
    }
    // 添加 无重复元素 100 + 38
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root==null){
            return new TreeNode(val); // 添加的末尾叶子结点
        }
        if (root.val>val){
            root.left=insertIntoBST(root.left,val);
        }else if (root.val<val){
            root.right=insertIntoBST(root.right,val);
        }
        // 相等的情况下不处理
        return root;
    }
    // 删除操作是将值改变
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root==null) return null;
        // delete from the right subtree
        if (key<root.val){
            root.left=deleteNode(root.left,key);
        }else if (key>root.val){
            root.right=deleteNode(root.right,key);
            // delete the current node
        }else{
            // the node is a leaf
            if (root.left == null && root.right == null) root = null;
                // the node is not a leaf and has a right child
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val); // 往下将后继叶子结点删除
            }
            // the node is not a leaf, has no right child, and has a left child
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);// 把前驱叶子结点删除
            }
        }
        return root;
    }
    /*
     * One step right and then always left
     * Successor 代表的是中序遍历序列的下一个节点。
     * 即比当前节点大的最小节点，简称后继节点。
     */
    public int successor(TreeNode root) {
        root=root.right;
        while (root.left!=null){
            root=root.left;
        }
        return root.val;
    }

    /*
     * One step left and then always right
     * Predecessor 代表的是中序遍历序列的前一个节点。
     * 即比当前节点小的最大节点，简称前驱节点。
     */
    public int predecessor(TreeNode root) {
        root=root.left;
        while (root.right!=null){
            root=root.right;
        }
        return root.val;
    }
}
