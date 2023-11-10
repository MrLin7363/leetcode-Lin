package A1000PLAN.前缀和.二维;

/**
 *desc:
 *@author lin
 *@since 2023/11/8
 **/
public class P303区域和检索数组不可变 {
    /*
    [i,j] 的和： presum[j]-presum[i]
     */
    class NumArray {
        // 从1开始,方便计算子数组和，不需要对列=0进行特殊处理
        private int[] presum;

        public NumArray(int[] nums) {
            this.presum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                presum[i + 1] = presum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return presum[right + 1] - presum[left];
        }
    }
}
