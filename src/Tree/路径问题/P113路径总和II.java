package Tree.路径问题;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/29
  *@Describe: 根节点到叶子节点=和的所有路径
 */

import Construct.TreeNode;

import java.util.*;

public class P113路径总和II {
    /*
    BFS  9 +63
     */
    // 根据hashmap 寻找父节点，得出当前节点到父节点路径
    Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();

    List<List<Integer>> res = new LinkedList<List<Integer>>();

    public List<List<Integer>> pathSum3(TreeNode root, int sum) {
        if (root == null)
            return res;
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queueNode.offer(root);
        queueSum.offer(0);
        // 注意 循环里面都用cur节点
        while (!queueNode.isEmpty()) {
            TreeNode cur = queueNode.poll();
            int curSum = queueSum.poll() + cur.val; // 当前和
            // 叶子节点
            if (cur.left == null && cur.right == null) {
                if (curSum == sum) {
                    getPath(cur);
                }
            } else {
                if (cur.left != null) {
                    map.put(cur.left, cur);
                    queueNode.offer(cur.left);
                    queueSum.offer(curSum);
                }
                if (cur.right != null) {
                    map.put(cur.right, cur);
                    queueNode.offer(cur.right);
                    queueSum.offer(curSum);
                }
            }
        }
        return res;
    }

    // 添加路径
    public void getPath(TreeNode node) {
        List<Integer> temp = new LinkedList<>();// 每次都会初始化，所以下面可以直接add(temp)
        while (node != null) {
            temp.add(node.val);
            node = map.get(node);// 父节点
        }
        Collections.reverse(temp); // 反转路径
        res.add(temp);
    }

    /*
    官方DFS  - 推荐 99+67
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();
        dfs(paths, root, targetSum, new ArrayDeque<>());
        return paths;
    }

    private void dfs(List<List<Integer>> paths, TreeNode root, int sum, Deque<Integer> path) {
        // 终止条件
        if (root == null) {
            return;
        }
        path.offer(root.val);
        sum -= root.val;
        // 成功条件
        if (root.left == null && root.right == null && sum == 0) {
            paths.add(new ArrayList<>(path));
            // 这里不 return 也行，因为后面那个pollLast都会执行
        }
        dfs(paths, root.left, sum, path);
        dfs(paths, root.right, sum, path);
        path.pollLast();
    }

    /*
    自己写的DFS递归99 + 42
     */
    static List<List<Integer>> ans = new LinkedList<>();

    public static List<List<Integer>> pathSum2(TreeNode root, int sum) {
        if (root == null)
            return new LinkedList();
        buildTree(root, sum, new LinkedList<>());
        return ans;
    }

    private static void buildTree(TreeNode root, int sum, List<Integer> path) {
        if (root.left == null && root.right == null && sum == root.val) {
            path.add(root.val);
            ans.add(new LinkedList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        path.add(root.val);
        // 要加非空判断，否则下层递归的root == null root.val .left会报空指针异常
        if (root.left != null) {
            buildTree(root.left, sum - root.val, path);
        }
        if (root.right != null) {
            buildTree(root.right, sum - root.val, path);
        }
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(4);
        System.out.println(pathSum2(node, 5));
    }
}
