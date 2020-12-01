package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/1
  *@Describe: 求根到叶子节点数字之和
  结点0-9
 */

import Construct.TreeNode;

public class P129Sum_Root_Leaf_Numbers {
    /*
    递归
    到达叶子节点一
     */

    public int sumNumbers(TreeNode root) {
        if (root==null || root.val ==0 ) return 0;
        return countPath(root, new StringBuilder(),0);
    }
    public int countPath(TreeNode node, StringBuilder sb, int sum){
        if (node==null ) return 0;
        sb.append(node.val);
        if (node.left==null && node.right==null){
            sum+=Integer.parseInt(sb.toString());
            return sum;
        }
        sum+=countPath(node.left,sb,sum);
        sum+=countPath(node.right,sb,sum);
        sb.deleteCharAt(sb.length()-1);
        return sum;
    }
}
