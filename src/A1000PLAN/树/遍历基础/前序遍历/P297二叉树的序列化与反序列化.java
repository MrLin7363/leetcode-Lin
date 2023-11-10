package A1000PLAN.树.遍历基础.前序遍历;

import Construct.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *desc: 先序遍历
 *
 *@author
 *@since 2023/11/7
 **/
public class P297二叉树的序列化与反序列化 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        String serialize = new P297二叉树的序列化与反序列化().serialize(root);
        System.out.println(serialize);
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    private String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "none,";
        } else {
            // 根左右
            str += root.val + ","; // 这里不能用char否则回类型上升为 int
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        // linkedList才能remove要不就用队列
        List<String> list = new LinkedList<>(Arrays.asList(nodes));
        return rede(list);
    }

    private TreeNode rede(List<String> list) {
        if (list.get(0).equals("none")) {
            list.remove(0);
            return null;
        }
        // 根左右
        TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        root.left = rede(list);
        root.right = rede(list);
        return root;
    }
}
