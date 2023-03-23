package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/5
  *@Describe: 二叉搜索树的最近公共祖先
 */

import Construct.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class P235二叉搜索树的最近公共祖先 {

    /*
    递归一次遍历  100 + 37
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val>root.val && q.val>root.val){
            return lowestCommonAncestor(root.right,p,q);
        }else if (p.val<root.val&&q.val<root.val){
            return lowestCommonAncestor(root.left,p,q);
        }else{
            return root;
        }
    }
    /*
    循环一次遍历  100 + 80
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val > root.val && q.val > root.val) {
                root=root.right;
            }else if (p.val<root.val&&q.val<root.val){
                root=root.left;
            }else{
                return root;
            }
        }
        return root;
    }
    /*
    两次遍历，分别先求出两个点到根节点的路径，再比较两个路径，求深度最大的分岔结点
    43 + 50
    */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path_p=getPath(root,p);
        List<TreeNode> path_q=getPath(root,q);
        TreeNode ancestor=null;
        for (int i = 0; i < path_p.size() && i < path_q.size(); i++) {
            if (path_p.get(i)==path_q.get(i)){
                ancestor=path_p.get(i);
            }else{
                break;
            }
        }
        return ancestor;
    }
    public List<TreeNode> getPath(TreeNode root,TreeNode target){
        List<TreeNode> path=new ArrayList<>();
        TreeNode node=root;
        while (node!=target){
            path.add(node);
            if (target.val>node.val){
                node=node.right;
            }else{
                node=node.left;
            }
        }
        path.add(node);
        return path;
    }
}
