package Tree.easy;

import Construct.TreeNode;

import java.util.Random;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/01 20:49
 * @Describe:
 */
public class Convert_Sorted_Array_to_Binary_Search_Tree_108 {
    public TreeNode helper(int[] nums,int left,int right){
        //子树为空
        if (left>right) return null;
        //选择中间位置左边元素
        int mid=(left+right)/2;
        //选择中间位置右边元素,如果left+right下标为奇数，则说明剩下的是偶数数组
//        int mid=(left+right+1)/2;
        //选择左边或右边元素都行,Random().nextInt产生0或1这两个数, [0 , 2) 之间的int整型
//          int mid = (left + right + rand.nextInt(2)) / 2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=helper(nums,left,mid-1);
        root.right=helper(nums,mid+1,right);
        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums,0,nums.length-1);
    }

}
