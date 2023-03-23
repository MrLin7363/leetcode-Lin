package Tree.路径问题;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/4
  *@Describe: 自底向上计算每个节点的最大贡献值，如2的最大贡献值为max(4,5)+2=7
       1
     /  \
    2   3
   / \
  4   5
   最大路径和时  5+2+1+3 = 11
   注意不是联通图，路径要一条线
 */

import Construct.TreeNode;

public class P124二叉树中的最大路径和_hard {
    /*
    递归，自底向上 100 + 56
     */
    static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public static int maxGain(TreeNode node) {
        if (node == null)
            return 0;
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点，负数的时候不计算该节点，负数说明该节点，最大贡献值为负
        // 如  -10  子结点 99  那么 -10 的最大贡献值为 89
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;
        // 更新答案，全局最大，判断取哪个结点的最大路径
        maxSum = Math.max(priceNewpath, maxSum);
        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right = new TreeNode(3);
        System.out.println(maxPathSum(node));
    }
}
