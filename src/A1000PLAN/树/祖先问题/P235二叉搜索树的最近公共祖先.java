package A1000PLAN.树.祖先问题;

import Construct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:
 *@author   
 *@since 2023/11/2
 **/
public class P235二叉搜索树的最近公共祖先 {
    /*
    判断大小 - On+O1
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (p.val < node.val && q.val < node.val) {
                node = node.left;
            } else if (p.val > node.val && q.val > node.val) {
                node = node.right;
            } else {
                break;
            }
        }
        return node;
    }

    /*
    两次遍历 On+On
    找根节点到子节点的路径
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = getPath(root, p);
        List<TreeNode> qPath = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < pPath.size() && i < qPath.size(); i++) {
            if (pPath.get(i) == qPath.get(i)) {
                ancestor = pPath.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    private List<TreeNode> getPath(TreeNode root, TreeNode target) {
        TreeNode node = root;
        List<TreeNode> path = new ArrayList<>();
        while (node != target) {
            path.add(node);
            if (node.val < target.val) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        path.add(node);
        return path;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        new P235二叉搜索树的最近公共祖先().lowestCommonAncestor(root, root.left, root.right);
    }
}
