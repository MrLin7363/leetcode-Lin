package A1000PLAN.树.easy;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/14
 **/
public class P897递增顺序搜索树 {
    /*
    1.中序遍历添加到列表->然后列表出构建树 - 太简单不展示
    2.中序遍历过程中 当我们遍历到一个节点时，把它的左孩子设为空，并将其本身作为上一个遍历到的节点的右孩子
     */
    private TreeNode resNode;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode sentinelNode = new TreeNode(-1);
        resNode = sentinelNode;
        inoreder(root);
        return sentinelNode.right;
    }

    private void inoreder(TreeNode node) {
        if (node == null) {
            return;
        }
        inoreder(node.left);
        resNode.right = node;
        node.left = null;
        resNode = node;
        inoreder(node.right);
    }
}
