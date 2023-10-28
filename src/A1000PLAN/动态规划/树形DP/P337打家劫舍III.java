
package A1000PLAN.动态规划.树形DP;

import Construct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/9/8
 **/
public class P337打家劫舍III {
    /*
    f(o):当前节点作为偷盗对象，当前节点的子树上被选择的节点的最大权值和
    g(o):当前节点不作为偷盗对象，当前节点的子树上被选择的节点的最大权值和
    选当前节点： f(o)=g(o.left)+g(o.right)+o.value  左不选+右不选+当前值
    不选当前节点： 左右子节点选或者不选的权值和相加  g(o)= Math.max(f(o.left),g(o.left))+Math.max(f(o.right),g(o.right));
    */

    Map<TreeNode, Integer> f = new HashMap<>();

    Map<TreeNode, Integer> g = new HashMap<>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    // 后序遍历
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math
            .max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    /*
    最佳解:使用两个遍历记录左右节点的最大权值和，[0]不选 [1]选
    */
    public int rob2(TreeNode root) {
        int[] arr = postOrder(root);
        return Math.max(arr[0], arr[1]);
    }

    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }
        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);
        // 左不选+右不选+当前值
        int selected = left[0] + right[0] + node.val;
        //  左选或不选+右选或不选
        int noSelecte = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[] {noSelecte, selected};
    }

    /*
    https://leetcode.cn/problems/house-robber-iii/solutions/47828/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
    暴力解法-超时,需要记忆化搜索   选两个孩子节点和四个孙子节点的对比*/
    public int rob3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }

        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }

        //  这里可以记忆化存储  memo.put(root, result);
        return Math.max(money, rob(root.left) + rob(root.right));
    }
}

