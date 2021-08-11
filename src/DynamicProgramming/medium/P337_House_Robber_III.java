package DynamicProgramming.medium;

import Construct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: chenjunlin
 * @since: 2021/08/11
 * @descripe:
 *     f:当前节点作为偷盗对象
 *     g:当前节点不作为偷盗对象
 *     f(o) = g(l) + g(r) , g(o） = Math.max( g(l) + f(r) ) + Math.max( f(r) + g(l) )
 */
public class P337_House_Robber_III {

    /*
    DP
     */
    Map<TreeNode,Integer> f = new HashMap<>();
    Map<TreeNode,Integer> g = new HashMap<>();
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root,0),g.getOrDefault(root,0));
    }

    private void dfs(TreeNode node){
        if (node==null){
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node,node.val+g.getOrDefault(node.left,0)+g.getOrDefault(node.right,0));
        g.put(node,Math.max(g.getOrDefault(node.left,0),f.getOrDefault(node.left,0)) // 左子节点偷或不偷的最大值
                +Math.max(g.getOrDefault(node.right,0),f.getOrDefault(node.right,0))); // 右子节点偷或不偷最大值
    }

    /*
    DP优化版，只关注 节点的两个子节点的值就可以
    100 + 24
     */
    public int rob2(TreeNode root) {
        int[] rootValue =dfsArray(root);
        return Math.max(rootValue[0],rootValue[1]);
    }
    private int[] dfsArray(TreeNode node){
        if (node==null){
            return new int[]{0,0};
        }
        int[] l = dfsArray(node.left);
        int[] r = dfsArray(node.right);
        int select = node.val+l[1]+r[1]; // 选择当前节点，不选子节点
        int notSelect = Math.max(l[0],l[1]) + Math.max(r[0],r[1]); // 0 1 区分选不选
        return new int[]{select,notSelect};
    }
}
