package A1000PLAN.树.二叉搜索树;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/15
 **/
public class P701二叉搜索树中的插入操作 {
    /*
    https://leetcode.cn/problems/insert-into-a-binary-search-tree/solutions/432355/2-de-cha-ru-by-sweetiee/
    1.递归写法
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    /*
    https://leetcode.cn/problems/insert-into-a-binary-search-tree/solutions/432223/er-cha-sou-suo-shu-zhong-de-cha-ru-cao-zuo-by-le-3/
    2.迭代写法
    */
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode pos = root;
        while (pos != null) {
            if (pos.val > val) {
                if (pos.left == null) {
                    pos.left = new TreeNode(val);
                    break;
                } else {
                    pos = pos.left;
                }
            } else {
                if (pos.right == null) {
                    pos.right = new TreeNode(val);
                    break;
                } else {
                    pos = pos.right;
                }
            }
        }
        return root;
    }

    /*
    3.自己写的，可以被替代  找到最接近这个target的结点，判断大小插入
     */
    public TreeNode insertIntoBST3(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode node = find(root, val);
        if (node.val > val) {
            node.left = new TreeNode(val);
        } else {
            node.right = new TreeNode(val);
        }
        return root;
    }

    private TreeNode find(TreeNode node, int target) {
        if (node.val < target) {
            if (node.right == null) {
                return node;
            }
            return find(node.right, target);
        } else {
            if (node.left == null) {
                return node;
            }
            return find(node.left, target);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(20);
        new P701二叉搜索树中的插入操作().insertIntoBST(root, 14);
    }
}
