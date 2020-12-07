package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/7
  *@Describe:
 */

import Construct.TreeNode;

public class P988_Smallest_String_Starting_From_Leaf {
    // 47 + 5
    String ans="~"; // 第一次要大于z 相当于Integer.MaxValue ，用于下面的第一次比较
    public String smallestFromLeaf(TreeNode root) {
        dfs(root,new StringBuilder());
        return ans;
    }
    public void dfs(TreeNode node,StringBuilder sb){
        if (node==null) return;
        // a转化为ASCALL码 97   如 97 +1 = 98  再转回为char = b
        // 'a' + 1 = 98   注意 char 后面整体要加 ()
        sb.append((char)('a'+node.val));
        //  反转字符串，和结果字符串比较
        if (node.right==null&&node.left==null) {
            sb.reverse();
            String now = sb.toString();
            if (now.compareTo(ans) < 0) {
                ans = now;
            }
            sb.reverse();
        }
        dfs(node.left,sb);
        dfs(node.right,sb);
        sb.deleteCharAt(sb.length()-1);
    }

    public static void main(String[] args) {
        System.out.println('a'+1);
    }

}
