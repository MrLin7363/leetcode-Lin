package A1000PLAN.树.序列化;

import Construct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *desc: 可以用 297二叉树采用先序遍历的方式序列化 - 但是先序遍历没考虑优化，代码直接复制都行 - 推荐一起记了
 *   也可以利用其特征优化性能 后序遍历
 *@author
 *@since 2023/11/7
 **/
public class P449序列化和反序列化二叉搜索树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        final String serialize = new P449序列化和反序列化二叉搜索树().serialize(root);
        new P449序列化和反序列化二叉搜索树().deserialize("1, 3, 2");
    }

    public String serialize(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        postorder(root, path);
        String string = path.toString(); // [2, 3, 1]
        return string.substring(1, string.length() - 1);
    }

    private void postorder(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }
        postorder(root.left, path);
        postorder(root.right, path);
        path.add(root.val);
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        // 注意空格，list.toString() -> [1, 2, 3] 带着空格
        String[] split = data.split(", ");
        Deque<Integer> deque = new ArrayDeque<>();
        // 左右根，用栈能先出根，再根据左右大小确定子节点在哪里
        for (String s : split) {
            deque.push(Integer.valueOf(s));
        }
        final TreeNode construct = construct(deque, Integer.MAX_VALUE, Integer.MIN_VALUE);
        return construct;
    }

    // 因为要维持有序性，所以需要范围判断
    private TreeNode construct(Deque<Integer> deque, int high, int low) {
        if (deque.isEmpty() || deque.peek() > high || deque.peek() < low) {
            return null;
        }
        Integer cur = deque.pop();
        // 根 -》右 -》 左
        TreeNode root = new TreeNode(cur);
        root.right = construct(deque, high, root.val);
        root.left = construct(deque, root.val, low);
        return root;
    }
}
