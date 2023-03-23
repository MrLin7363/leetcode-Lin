package Tree.路径问题;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/30
  *@Describe: 找出父节点到子节点的路径个数
  前缀和
在同一个路径之下（可以理解成二叉树从root节点出发，到叶子节点的某一条路径），如果两个数的前缀总和是相同的，
那么这些节点之间的元素总和为零。进一步扩展相同的想法，如果前缀总和currSum，在节点A和节点B处相差target，
则位于节点A和节点B之间的元素之和是target。
 */

import Construct.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class P437路径总和III_前缀和 {

    /*
    前缀和  目标和-当前和   99 + 42
     */
    public static int pathSum(TreeNode root, int sum) {
        // 前缀和,前缀和的个数  Long避免溢出
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1); // 因为空路径不经过任何节点，因此它的前缀和为0
        return recursionPathSum(root, prefixSumCount, sum, 0L);
    }

    private static int recursionPathSum(TreeNode node, Map<Long, Integer> prefixSumCount, int target, long curSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        curSum += node.val;
        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径， 因为这里节点可以为负数，所以可能有多个相同的前缀和
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // 重点 currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(curSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(curSum, prefixSumCount.getOrDefault(curSum, 0) + 1);

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, curSum);
        res += recursionPathSum(node.right, prefixSumCount, target, curSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(curSum, prefixSumCount.get(curSum) - 1);
        return res;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(4);
        System.out.println(pathSum(node, 5));
    }

    /*
    递归易懂但时间耗时高，每遍历到一个结点，计算该节点下所有符合路径和的路径数， O n^2  27 + 69
     */
    public int pathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int res = countPath(root, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }

    // 计算该结点为起点，所有符合路径和的路径个数, long是避免溢出问题
    private int countPath(TreeNode root, long sum) {
        if (root == null) {
            return 0;
        }
        sum -= root.val;
        int result = sum == 0 ? 1 : 0;
        return result + countPath(root.left, sum) + countPath(root.right, sum);
    }
}
