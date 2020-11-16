package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/15
  *@Describe:  规律题
  标准的“下一个排列”算法可以描述为：

从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
将 A[i] 与 A[k] 交换
可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
 */

public class P31_Next_Permutation {
    public void nextPermutation(int[] nums) {
        int i=nums.length-2;
        // 找第一个相邻元素
        while (i>=0 && nums[i]>=nums[i+1]){
            i--;
        }
        // 找第一个大于i的元素
        if (i>=0){
            int j=nums.length-1;
            while (nums[i]>=nums[j] && j>=0){
                j--;
            }
            // 交换两个元素
            swap(nums,i,j);
        }
        // 反转i后面的数组
        reverse(nums,i+1);
    }
    private static void swap(int[] nums, int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    private static void reverse(int[] nums,int start){
        int left=start,right=nums.length-1;
        while (left<right){
            swap(nums,left,right);
            left++;
            right--;
        }
    }
}
