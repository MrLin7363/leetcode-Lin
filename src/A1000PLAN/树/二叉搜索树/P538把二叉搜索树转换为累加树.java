package A1000PLAN.树.二叉搜索树;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/14
 **/
public class P538把二叉搜索树转换为累加树 {
    /*
    >=就是右子树的结点总和+父节点右子树的结点总和
    反序中序遍历， 右根左
     */
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
