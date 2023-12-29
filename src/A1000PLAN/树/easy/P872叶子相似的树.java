package A1000PLAN.树.easy;

import Construct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/14
 **/
public class P872叶子相似的树 {
    /*
    记录叶子结点,再比较;不能用String因为值可能拼起来一样
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> seq1 = new ArrayList<Integer>();
        if (root1 != null) {
            dfs(root1, seq1);
        }

        List<Integer> seq2 = new ArrayList<Integer>();
        if (root2 != null) {
            dfs(root2, seq2);
        }

        return seq1.equals(seq2);
    }

    public void dfs(TreeNode node, List<Integer> seq) {
        if (node.left == null && node.right == null) {
            seq.add(node.val);
        } else {
            if (node.left != null) {
                dfs(node.left, seq);
            }
            if (node.right != null) {
                dfs(node.right, seq);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(5);
        node.right = new TreeNode(1);
        node.left.left = new TreeNode(6);
        node.left.right = new TreeNode(2);
        node.left.right.left = new TreeNode(7);
        node.left.right.right = new TreeNode(4);
        node.right.left = new TreeNode(9);
        node.right.right = new TreeNode(8);
        TreeNode node2 = new TreeNode(3);
        node2.left = new TreeNode(5);
        node2.right = new TreeNode(1);
        node2.left.left = new TreeNode(6);
        node2.left.right = new TreeNode(7);
        node2.right.left = new TreeNode(4);
        node2.right.right = new TreeNode(2);
        node2.right.right.left = new TreeNode(9);
        node2.right.right.right = new TreeNode(8);
        System.out.println(new P872叶子相似的树().leafSimilar(node, node2));
    }
}
