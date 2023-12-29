package A1000PLAN.树.直径路径问题.上下递归;

import Construct.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc: 父节点回溯
 *
 * @author Lin
 * @since 2023/12/20
 **/
public class P863二叉树中所有距离为K的结点 {
    /*
    1.记录每个结点的父节点
    2.以target为根节点，分别搜索左孩子，右孩子，父节点的距离为K的结点
    注意：未避免父节点向上搜索时，又向下搜索，采用前驱结点from和目标结点比较是否相同，相同则是重复的，不递归
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs(root);
        dfs(target, parent.get(target), 0, k);
        return ans;
    }

    private Map<Integer, TreeNode> parent = new HashMap<>();

    private List<Integer> ans = new ArrayList<>();

    private void dfs(TreeNode node) {
        if (node.left != null) {
            parent.put(node.left.val, node);
            dfs(node.left);
        }
        if (node.right != null) {
            parent.put(node.right.val, node);
            dfs(node.right);
        }
    }

    private void dfs(TreeNode node, TreeNode from, int gap, int k) {
        if (node == null) {
            return;
        }
        if (gap == k) {
            ans.add(node.val);
            return;
        }
        if (node.left != from) {
            dfs(node.left, node, gap + 1, k);
        }
        if (node.right != from) {
            dfs(node.right, node, gap + 1, k);
        }
        if (parent.get(node.val) != from) {
            dfs(parent.get(node.val), node, gap + 1, k);
        }
    }
}
