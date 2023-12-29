package A1000PLAN.树.结点下标相关;

import Construct.TreeNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/19
 **/
public class P662二叉树最大宽度 {
    /*
    BFS:一个编号为 index 的左子节点的编号记为 2×index，右子节点的编号记为 2×index+1
    计算每层宽度时，用每层节点的最大编号减去最小编号再加 1 即为宽度
     */
    public int widthOfBinaryTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, 1);
        int ans = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int begin = -1;
            int end = -1;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                Integer index = map.get(node);
                if (i == 0) {
                    begin = index;
                }
                if (i == size - 1) {
                    end = index;
                }
                if (node.left != null) {
                    queue.add(node.left);
                    map.put(node.left, index * 2);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    map.put(node.right, index * 2 + 1);
                }
            }
            ans = Math.max(ans, end - begin + 1);
        }
        return ans;
    }

    /*
    DFS: 每层第一次进入的都是最左边的元素
     */
    private Map<Integer, Integer> leftMin = new HashMap<>(); // depth,leftIndex

    public int widthOfBinaryTree2(TreeNode root) {
        return dfs(root, 1, 1);
    }

    private int dfs(TreeNode root, int depth, int index) {
        if (root == null) {
            return 0;
        }
        leftMin.putIfAbsent(depth, index);
        return Math.max(index - leftMin.get(depth) + 1,
            Math.max(dfs(root.left, depth + 1, index * 2),
                dfs(root.right, depth + 1, index * 2 + 1)));
    }
}
