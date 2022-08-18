package hw.案例.树形结构;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 节点后续最大深度   +  二分查找
 */
public class 二叉树最大深度 {
    private static class BinaryTreeNode {
        int val;

        BinaryTreeNode left;

        BinaryTreeNode right;

        BinaryTreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        二叉树最大深度 ss = new 二叉树最大深度();
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(3);
        root.right = new BinaryTreeNode(7);
        root.left.left = new BinaryTreeNode(1);
        root.left.right = new BinaryTreeNode(5);
        int[] ints = ss.valueDepth(new int[]{2, 7}, root);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        System.out.println("----------------------");
        BinaryTreeNode root2 = new BinaryTreeNode(3);
        root2.left = new BinaryTreeNode(4);
        root2.right = new BinaryTreeNode(11);
        root2.left.left = new BinaryTreeNode(3);
        root2.right.right = new BinaryTreeNode(8);
        root2.left.left.left = new BinaryTreeNode(7);
        root2.right.right.left = new BinaryTreeNode(5);
        int[] int2 = ss.valueDepth(new int[]{6, 2, 9, 7, 9}, root2);
        for (int i = 0; i < int2.length; i++) {
            System.out.println(int2[i]);
        }
    }

    /**
     * 1.bfs/dfs 求节点的最大深度, treeMap (节点值，节点最大深度)  按节点值降序
     * 2.倒序求节点的后续最大深度，按节点值升序  或者使用单调递减栈
     * 3.二分查找第一个比target大的节点值
     */
    Map<Integer, Integer> valueMap;

    Map<Integer, Integer> maxValueMap;

    public int[] valueDepth(int[] target, BinaryTreeNode root) {
        valueMap = new TreeMap<>((o1, o2) -> o2 - o1);
        maxValueMap = new TreeMap<>();
        dfs(root, 1);
        getMaxDeep();
        int[] res = new int[target.length];
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(maxValueMap.entrySet());
        for (int i = 0; i < target.length; i++) {
            int tar = target[i];
            if (list.get(list.size() - 1).getKey() <= tar) {
                res[i] = -1;
            } else {
                int cur = binarySearch(list, tar);
                if (cur + 1 == list.size()) {
                    res[i] = -1;
                } else {
                    res[i] = list.get(cur + 1).getValue();
                }
            }
        }
        return res;
    }

    private void dfs(BinaryTreeNode root, int deep) {
        if (root == null) {
            return;
        }
        valueMap.put(root.val, Math.max(valueMap.getOrDefault(root.val, 0), deep));
        dfs(root.left, deep + 1);
        dfs(root.right, deep + 1);
    }

    // 如 5 1 |  7 2  -->  5 2 | 7 2 节点值小的深度可以是更大节点的深度
    // map[i] 的后续最大深度总是大于等于map[i + 1] 的后续最大深度
    private void getMaxDeep() {
        int max = Integer.MIN_VALUE;
        for (Map.Entry entry : valueMap.entrySet()) {
            max = Math.max(max, (Integer) entry.getValue());
            maxValueMap.put((Integer) entry.getKey(), max);
        }
    }

    /*
    查找第一个大于target的元素
    利用右边界模板：查找最后一个大于等于target的元素   最后默认索引是最后一个 比target小的元素，或者等于target的元素
    索引加+1就是第一个 大于target的元素
     */

    private int binarySearch(List<Map.Entry<Integer, Integer>> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int mid = left + ((right - left) / 2) + 1;
            if (list.get(mid).getKey() > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return right;
    }

}
