package Tree.mediumNone;

import Construct.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: Junlin Chen
 * @Date: 2021/10/01 11:20
 * @Describe:
 */
public class P103二叉树的锯齿形层序遍历 {

    /*
    BFS 94+10
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ansList = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        if (root == null) {
            return ansList;
        }
        nodeQueue.offer(root);
        boolean isLeft = true;
        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodeQueue.poll();
                if (isLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ansList.add(new LinkedList<>(levelList));
            isLeft = !isLeft;
        }
        return ansList;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 3;
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        zigzagLevelOrder(root);
    }
}
