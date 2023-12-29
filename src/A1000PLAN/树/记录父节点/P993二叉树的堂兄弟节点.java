package A1000PLAN.树.记录父节点;

import Construct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/13
 **/
public class P993二叉树的堂兄弟节点 {
    /*
    1.同时记录父节点和深度 变量记录
     */
    private TreeNode xParent;

    private boolean xFound;

    private int xDepth;

    private TreeNode yParent;

    private boolean yFound;

    private int yDepth;

    public boolean isCousins(TreeNode root, int x, int y) {
        xFound = false;
        yFound = false;
        recur(root, 0, null, x, y);
        return xParent != yParent && xDepth == yDepth;
    }

    private void recur(TreeNode node, int level, TreeNode parent, int x, int y) {
        if (node == null) {
            return;
        }
        if (node.val == x) {
            xParent = parent;
            xDepth = level;
            xFound = true;
        }
        if (node.val == y) {
            yParent = parent;
            yDepth = level;
            yFound = true;
        }
        if (xFound && yFound) {
            return;
        }
        recur(node.left, level + 1, node, x, y);
        if (xFound && yFound) {
            return;
        }
        recur(node.right, level + 1, node, x, y);
    }

    /*
    2.同时记录父节点和深度 map记录
     */
    private Map<Integer, Integer> depthMap = new HashMap<>();

    private Map<Integer, TreeNode> parent = new HashMap<>();

    public boolean isCousins2(TreeNode root, int x, int y) {
        dfs(root, 0);
        return parent.get(x) != parent.get(y) && depthMap.get(x) == depthMap.get(y);
    }

    private void dfs(TreeNode node, int level) {
        if (node.left != null) {
            parent.put(node.left.val, node);
            depthMap.put(node.val, level);
            dfs(node.left, level + 1);
        }
        if (node.right != null) {
            parent.put(node.right.val, node);
            depthMap.put(node.val, level);
            dfs(node.right, level + 1);
        }
        if (node.left == null && node.right == null) {
            depthMap.put(node.val, level);
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.right = new TreeNode(4);
        node.right.right = new TreeNode(5);
        System.out.println(new P993二叉树的堂兄弟节点().isCousins(node, 5, 4));
    }
}
