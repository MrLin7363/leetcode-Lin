package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/30
  *@Describe: 找出父节点到子节点的路径个数
  前缀和
  root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11

 */


import Construct.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class P437Path_Sum_III {

    /*
    前缀和  目标和-当前和   99 + 42
     */
    public static int pathSum(TreeNode root, int sum) {
        Map<Integer,Integer> prefixSumCount=new HashMap<>();
        prefixSumCount.put(0,1);
        return recursionPathSum(root,prefixSumCount,sum,0);
    }
    private static int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int curSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        curSum += node.val;
        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
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
        TreeNode node=new TreeNode(1);
        node.left=new TreeNode(2);
        node.right=new TreeNode(4);
        System.out.println(pathSum(node,5));
    }
    /*
    递归易懂但时间耗时高，每遍历到一个结点，计算该节点下所有符合路径和的路径数， O n^2  27 + 69
     */
    public int pathSum2(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }
        int result = countPath(root,sum);
        int a = pathSum2(root.left,sum);
        int b = pathSum2(root.right,sum);
        return result+a+b;

    }
    public int countPath(TreeNode root,int sum){
        if(root == null){
            return 0;
        }
        sum = sum - root.val;
        int result = sum == 0 ? 1:0;
        return result + countPath(root.left,sum) + countPath(root.right,sum);
    }


}
