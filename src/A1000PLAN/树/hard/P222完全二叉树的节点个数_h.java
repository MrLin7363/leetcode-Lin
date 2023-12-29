package A1000PLAN.树.hard;

import Construct.TreeNode;

/**
 *desc: https://leetcode.cn/problems/count-complete-tree-nodes/solutions/495655/wan-quan-er-cha-shu-de-jie-dian-ge-shu-by-leetco-2/
 *@author
 *@since 2023/11/2
 **/
public class P222完全二叉树的节点个数_h {
    /*
    二分+位运算
    计算完全二叉树的最大深度=最左结点深度
    level [0,...]
    完全二叉树最小个数= (level)^2
    完全二叉树最大个数= (level+1)^2 -1
    对于最大层数为 hhh 的完全二叉树，节点个数一定在 索引[2^h,2^(h+1)−1] 的范围内
    索引就是个数：从1开始

    步骤：1.计算最大深度
         2.二分查找最后一层的最后的结点
         3.二分查找过程借助位运算
             - 举个例子高度从0开始：
            - 第二层的所有节点索引为 4、5、6、7，对应的二进制值为 100、101、110、111
            - 第三层的所有节点索引为 8、9、10、11、12、13、14、15，对应的二进制值为 1000、1001、1010、1011、1100、1101、1110、1111
            - 不难看出一个规律：去掉最高位 1 后，根据二进制中 1 和 0 可以判断该索引在树中的位置( 0 代表在左子树中， 1 代表在右子树中)：
     */
    public int countNodes(TreeNode root) {
        int level = -1;
        TreeNode node = root;
        while (node != null) {
            node = node.left;
            level++;
        }
        // 求最后一层结点索引范围
        int left = (int) Math.pow(2, level);
        int right = (int) Math.pow(2, level + 1) - 1;

        // 范围内求最后一个结点
        while (left < right) {
            int mid = left + (right - left + 1) / 2; // 取偏右，从右往前搜索
            String binaryString = Integer.toBinaryString(mid);
            node = root;
            for (int i = 1; i < binaryString.length(); i++) {
                if (binaryString.charAt(i) == '0') {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            if (node == null) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        // root.right = new TreeNode(3);
        // root.left.left = new TreeNode(4);
        // root.left.right = new TreeNode(5);
        // root.right.left = new TreeNode(6);
        new P222完全二叉树的节点个数_h().countNodes(root);
    }



    /*
    function countNodes(root: TreeNode | null): number {
    if (!root) return 0;
    // 找到最大深度 h
    let h = 0;
    let left = root?.left;
    while (left) {
        h++;
        left = left.left;
    }
    // 最底层的索引取值范围为 2^h 至 2^(h+1)-1
    let min = Math.pow(2, h), max = Math.pow(2, h + 1) - 1;
    /* 二分求解
    - 举个例子高度从0开始：
      - 第二层的所有节点索引为 4、5、6、7，对应的二进制值为 100、101、110、111
      - 第三层的所有节点索引为 8、9、10、11、12、13、14、15，对应的二进制值为 1000、1001、1010、1011、1100、1101、1110、1111
    - 不难看出一个规律：去掉最高位 1 后，根据二进制中 1 和 0 可以判断该索引在树中的位置( 0 代表在左子树中， 1 代表在右子树中)：
     - 4的位置 00 => 左子树的左子树根节点索引；
     - 11的位置 011 => 左子树的右子树的右子树根节点索引；
    所以只需要根据这个路径推断看这个节点在树中是否存在即可。
    while (min < max) {
        let mid = Math.ceil((min + max) / 2); // 此处用进一法向上取值
        // 查找 mid 索引的节点是否存在
        let node = root;
        let binaryStr = mid.toString(2);
        for (let i = 1; i < binaryStr.length; i++) {
            if (!node) break;
            if (binaryStr[i] === '0') node = node.left;
            else node = node.right;
        }
        if (node) min = mid;
        else max = mid - 1;
    }
    return min;
};
     */
}
