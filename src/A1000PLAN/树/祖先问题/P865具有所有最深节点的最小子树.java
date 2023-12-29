package A1000PLAN.树.祖先问题;

import Construct.TreeNode;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * desc: 与力扣 1123 重复：https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves
 *
 * @author Lin
 * @since 2023/12/20
 **/
public class P865具有所有最深节点的最小子树 {
    /*
    可以理解为找  最深叶节点的最近公共祖先P1123; 如果有多个叶子节点则是最近公共祖先，如果是单个最深叶子结点，则是自身
    1.未优化： 先DFS一次找到所有结点的深度； 再DFS一次回溯找最近公共祖先
     */
    private Map<TreeNode, Integer> depths = new HashMap<>();

    private int maxDepth = -1;

    public TreeNode subtreeWithAllDeepest2(TreeNode root) {
        dfs(root, 0);
        for (Integer depth : depths.values()) {
            maxDepth = Math.max(maxDepth, depth);
        }
        return find(root);
    }

    private void dfs(TreeNode root, int depth) {
        if (root != null) {
            depths.put(root, depth);
            dfs(root.left, depth + 1);
            dfs(root.right, depth + 1);
        }
    }

    // 自底向上，回溯
    public TreeNode find(TreeNode node) {
        if (node == null || depths.get(node) == maxDepth) {
            return node;
        }
        TreeNode left = find(node.left);
        TreeNode right = find(node.right);
        // 左右子树都包含最深结点
        if (left != null && right != null) {
            // 返回根结点
            return node;
        } else if (right == null) {
            // 只有右子树有，返回右子结点
            return left;
        } else if (left == null) {
            return right;
        } else {
            // 都不包含
            return null;
        }
    }

    /*
    2.优化版本 ------------------------------------------
        一趟DFS，过程中记录深度及lca结点
        如果当前节点为空，我们返回深度 000 和空节点。在每次搜索中，我们递归地搜索左子树和右子树，然后比较左右子树的深度：
    如果左子树更深，最深叶节点在左子树中，我们返回 {左子树深度 + 1，左子树的 lca 节点}
    如果右子树更深，最深叶节点在右子树中，我们返回 {右子树深度 + 1，右子树的 lca 节点}
    如果左右子树一样深，左右子树都有最深叶节点，我们返回 {左子树深度 + 1，当前节点}
    https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves/solutions/2421007/zui-shen-xie-jie-dian-de-zui-jin-gong-go-cjzv/
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Pair<TreeNode, Integer> pair = dfs(root);
        return pair.getKey();
    }

    private Pair<TreeNode, Integer> dfs(TreeNode node) {
        if (node == null) {
            return new Pair<>(null, 0);
        }
        Pair<TreeNode, Integer> left = dfs(node.left);
        Pair<TreeNode, Integer> right = dfs(node.right);
        // 右子树深度更深
        if (left.getValue() < right.getValue()) {
            return new Pair<>(right.getKey(), right.getValue() + 1);
        }
        if (left.getValue() > right.getValue()) {
            return new Pair<>(left.getKey(), left.getValue() + 1);
        }
        // 左右结点一样深，返回当前结点，
        return new Pair<>(node, left.getValue() + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        new P865具有所有最深节点的最小子树().subtreeWithAllDeepest(root);
    }
}
