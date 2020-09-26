package Tree.easy;

import Construct.TreeNode;

import java.util.Random;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/01 20:49
 * @Describe:
 */
public class Convert_Sorted_Array_to_Binary_Search_Tree_108 {
    int[] nums;
    public TreeNode helper(int left,int right){
        //子树为空
        if (left>right) return null;
        //选择中间位置左边元素
        int p=(left+right)/2;
        //选择中间位置右边元素,如果left+right下标为奇数，则说明剩下的是偶数数组
       /* int p=(left+right)/2;
        if ((left+right)%2==1) ++p;*/
       //选择左边或右边元素都行,Random().nextInt产生0或1这两个数
        /*int p=(left+right)/2;
        if ((left+right)%2==1) p+=new Random().nextInt(2);*/

        TreeNode root=new TreeNode(nums[p]);
        root.left=helper(left,p-1);
        root.right=helper(p+1,right);
        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums=nums;
        return helper(0,nums.length-1);
    }
}
