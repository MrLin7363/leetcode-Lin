package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/6
  *@Describe:
 */

import Construct.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import sun.reflect.generics.tree.Tree;

public class P95_Unique_Binary_Search_Trees_II {

    /*
    利用添加规律   94 + 92
     */
    public List<TreeNode> generateTrees2(int n) {
        List<TreeNode> pre=new ArrayList<>();
        if (n==0) return pre;
        pre.add(null);
        for (int i = 1; i <=n ; i++) {
            List<TreeNode> cur=new ArrayList<>();
            // null 时也遍历
            for (TreeNode root:pre){
                TreeNode insert=new TreeNode(i);
                // 插入到根节点
                insert.left=root;
                cur.add(insert);
                // 插入到右孩子结点
                for (int j = 0; j < i; j++) {
                    // 复制当前的树，不然第二次插入右孩子结点的树不一致了
                    TreeNode root_copy=copyTree(root);
                    //找到要插入右孩子的位置
                    TreeNode right=root_copy;
                    int k=0;
                    while (right!=null && k<j){
                        right=right.right;
                        k++;
                    }
                    // 如果right==null , right.right会报错
                    if (null==right){
                        break;
                    }
                    //保存当前右孩子的位置的子树作为插入节点的左孩子
                    TreeNode rightTree = right.right;
                    insert = new TreeNode(i);
                    right.right=insert;//右孩子是插入的节点
                    insert.left=rightTree;//插入节点的左孩子更新为插入位置之前的子树
                    cur.add(root_copy);
                }
            }
            pre=cur;
        }
        return pre;
    }
    public TreeNode copyTree(TreeNode root){
        if (root==null) return null;
        TreeNode node=new TreeNode(root.val);
        node.left=copyTree(root.left);
        node.right=copyTree(root.right);
        return node;
    }

    /*
    DP  94 + 88
     */
    public  List<TreeNode> generateTrees(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<TreeNode>();// 如果left或right为0，就会出现空指针异常。
        if (n == 0) {
            return dp[0];
        }
        // 如果不加null，那么后面当left或right为0时，就不会执行for循环。而一旦left不执行，right也会被跳过。
        dp[0].add(null);
        //长度为 1 到 n
        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<TreeNode>();
            //将不同的数字作为根节点，只需要考虑到 len
            for (int root = 1; root <= len; root++) {
                int left = root - 1;  // root为根节点，left是root左边的节点数。
                int right = len - root; //右子树的长度
                /*    假设n为5，root是3，那么左边有2个节点，所以去dp[2]里面找，右边也有两个节点4,5。所以还去dp[2]里面找。
                    因为只有dp[2]里面都是2个节点的数。但是dp[2]中的数只有1和2，我们要的是4,5。
                    我们不妨将1,2加上root，你会发现正好是4,5。
                    所以得到结论，左子树的值直接找前面节点数一样的dp索引，右子树的值也找前面一样的dp索引,
                    但是你需要加上root才能保证val是你需要的，所以右子树要重新创建，不然会破坏前面的树。
                */
                // 如果dp[left]里有两种可能，dp[right]里有三种可能，那么总共有6种可能。
                for (TreeNode leftTree : dp[left]) {
                    for (TreeNode rightTree : dp[right]) {
                        TreeNode treeRoot = new TreeNode(root);// 这个是每一种可能的root节点。
                        treeRoot.left = leftTree;// 左子树直接连接。
                        //克隆右子树并且加上偏差
                        treeRoot.right = clone(rightTree, root);// 右子树创建一个新的树。
                        dp[len].add(treeRoot);// 将一种可能加入dp中。
                    }
                }
            }
        }
        return dp[n];
    }
    // 重新创建一个新的子树
    private  TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;// 返回回去的这个节点正好跟在你需要的右子树上。
    }

    /*
     递归  9 + 5
      */
    public List<TreeNode> generateTreesR(int n) {
        if (n==0)  return new LinkedList<>();
        return getAns(1,n);
    }
    public List<TreeNode> getAns(int start, int end ){
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if (start>end){
            //此时没有数字，将 null 加入结果中
            ans.add(null);
            return ans;
        }
        //只有一个数字，当前数字作为一棵树加入结果中
        if (start==end){
            TreeNode node=new TreeNode(start);
            ans.add(node);
            return ans;
        }
        //尝试每个数字作为根节点
        for (int i = start; i <= end; i++) {
            //得到所有可能的左子树
            List<TreeNode> leftTrees=getAns(start,i-1);
            //得到所有可能的右子树
            List<TreeNode> rightTrees=getAns(i+1,end);
            //左子树右子树两两组合
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    //加入到最终结果中
                    ans.add(root);
                }
            }
        }
        return ans;
    }
}
