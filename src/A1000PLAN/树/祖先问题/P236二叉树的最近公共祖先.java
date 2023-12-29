package A1000PLAN.树.祖先问题;

import Construct.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *desc:
 *@author   
 *@since 2023/11/2
 **/
public class P236二叉树的最近公共祖先 {
    /*
    分别记录每个结点的的父结点，自底向上路径遇到相同的就返回
     */
    private Map<TreeNode, TreeNode> parent = new HashMap<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Set<TreeNode> visited = new HashSet<>();
        dfs(root);
        while (p != null) {
            visited.add(p);
            p = parent.get(p);
        }
        while (q != null) {
            if (visited.contains(q)) {
                return q;
            }
            q = parent.get(q);
        }
        return null;
    }

    private void dfs(TreeNode node) {
        if (node.left != null) {
            parent.put(node.left, node);
            dfs(node.left);
        }
        if (node.right != null) {
            parent.put(node.right, node);
            dfs(node.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        new P236二叉树的最近公共祖先().lowestCommonAncestor(root, root.left, root.right);
    }
}
