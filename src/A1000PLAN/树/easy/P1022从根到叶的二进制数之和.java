package A1000PLAN.树.easy;

import Construct.TreeNode;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/14
 **/
public class P1022从根到叶的二进制数之和 {
    /*
    二进制运算代替 String -推荐
     */
    public int sumRootToLeaf2(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        // int << | 都是二进制运算符，直接使用是按二进制来算的，最后结果出来还是int 十进制
        // 1011-> 10111 左移1位给新值填充
        val = (val << 1) | root.val;
        if (root.left == null && root.right == null) {
            return val;
        }
        return dfs(root.left, val) + dfs(root.right, val);
    }


    /*
    String
     */
    private int sum;

    public int sumRootToLeaf(TreeNode root) {
        sum = 0;
        dfs(root, "");
        return sum;
    }

    private void dfs(TreeNode node, String s) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            s += node.val;
            // 二进制字符串转十进制
            sum += Integer.parseUnsignedInt(s, 2);
            return;
        }
        s += node.val;
        dfs(node.left, s);
        dfs(node.right, s);
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        System.out.println(new P1022从根到叶的二进制数之和().sumRootToLeaf(root));
        System.out.println(new P1022从根到叶的二进制数之和().sumRootToLeaf2(root));
    }
}
