package Tree.构造树;

import Construct.TreeNode;

/**
 * @author: lin
 * @Date: 2020/07/01 20:49
 * @Describe: 二叉搜索树的中序遍历左根右是升序数组, 这里的建树能够保证是平衡的二叉搜索树BST，P1382是证明平衡
 * 升序数组是中序遍历
 * 构建过程是前序遍历
 */
public class P108将有序数组转换为二叉搜索树 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return inOrder(nums, 0, nums.length - 1);
    }

    /**
     * 根中间节点就是根节点，根左右的前序遍历方式确定
     */
    public TreeNode helper(int[] nums, int left, int right) {
        //子树为空
        if (left > right)
            return null;
        //选择中间位置左边元素
        int mid = (left + right) / 2;
        //选择中间位置右边元素
        //        int mid=(left+right+1)/2;
        //随机选择左边或右边元素,Random().nextInt产生0或1这两个数, [0 , 2) 之间的int整型
        //          int mid = (left + right + rand.nextInt(2)) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /**
     * 第二种方法： 左根右的中序遍历方式确定
     */
    private int index=0;

    public TreeNode inOrder(int[] nums, int left, int right) {
        //子树为空
        if (left > right)
            return null;
        //选择中间位置左边元素
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode();
        root.left = inOrder(nums, left, mid - 1);
        root.val = nums[index++];
        root.right = inOrder(nums, mid + 1, right);
        return root;
    }
}
